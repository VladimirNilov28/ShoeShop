/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Income;
import entity.Purchase;
import entity.Shoe;
import entity.User;
import java.util.List;

/**
 *
 * @author ArTIK
 */
public interface Keeping {
    public void saveShoes(List<Shoe> shoes);
    public List<Shoe> loadShoes();
    public void saveUsers(List<User> users);
    public List<User> loadUsers();
    public void savePurchases(List<Purchase> purchases);
    public List<Purchase> loadPurchases();
    public void saveIncomes(List<Income> incomes);
    public List<Income> loadIncome();
    
}
