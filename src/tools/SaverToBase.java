/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.User;
import entity.Income;
import entity.Purchase;
import entity.Shoe;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
    public class SaverToBase implements Keeping{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShoeStorePU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void saveShoes(List<Shoe> shoes) {
        tx.begin();
            for (int i = 0; i < shoes.size(); i++) {
                if(shoes.get(i).getId() == null){
                    em.persist(shoes.get(i));
                }else{
                    em.merge(shoes.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Shoe> loadShoes() {
        List<Shoe> shoes=null;
        try {
            shoes = em.createQuery("SELECT shoes FROM Shoes shoes")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return shoes;
    }
    
    @Override
    public void saveUsers(List<User> users) {
        tx.begin();
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i).getId() == null){
                    em.persist(users.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<User> loadUsers() {
         List<User> users=null;
        try {
            users = em.createQuery("SELECT customer FROM Customer customer")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return users;
    }

    @Override
    public void savePurchases(List<Purchase> purchases) {
        tx.begin();
            for (int i = 0; i < purchases.size(); i++) {
                if(purchases.get(i).getId() == null){
                    em.persist(purchases.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Purchase> loadPurchases() {
         List<Purchase> purchases=null;
        try {
            purchases = em.createQuery("SELECT purchase FROM Purchase purchase")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return purchases;
    }

    @Override
    public void saveIncomes(List<Income> inomes) {
    }

    @Override
    public List<Income> loadIncome() {
     return new ArrayList<>();
    }

    
}
