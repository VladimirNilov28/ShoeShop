/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Income;
import entity.Purchase;
import entity.Shoe;
import entity.User;
import interfaces.Keeping;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ArTIK
 */
public class SaverToFiles implements Keeping{
    
    @Override
    public void saveShoes(List<Shoe> shoes) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("shoes");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(shoes);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет файла shoes", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<Shoe> loadShoes() {
        List<Shoe> shoes = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("shoes");
            ois = new ObjectInputStream(fis);
            shoes = (List<Shoe>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "shoes еше не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка чтения", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        }
        return shoes;
    }

    @Override
    public void saveUsers(List<User> users) {
       FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("users");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет файла users", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("Users");
            ois = new ObjectInputStream(fis);
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "users еше не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка чтения", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        }
        return users;
    }

    @Override
    public void savePurchases(List<Purchase> purchases) {
         FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("purchases");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(purchases);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет файла purchases", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<Purchase> loadPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("purchases");
            ois = new ObjectInputStream(fis);
            purchases = (List<Purchase>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "purhcases еше не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка чтения", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        }
        return purchases;
    }

    @Override
    public void saveIncomes(List<Income> incomes) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("income");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(incomes);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Income> loadIncome() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Income> incomes = new ArrayList<>();
        try {
            fis = new FileInputStream("income");
            ois = new ObjectInputStream(fis);
            incomes = (List<Income>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "income еше не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка чтения", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        }
        return incomes;
    }
}