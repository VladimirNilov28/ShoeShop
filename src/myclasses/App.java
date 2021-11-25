/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Income;
import entity.Purchase;
import entity.Shoe;
import entity.User;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import tools.SaverToBase;
//import tools.SaverToFiles;

/**
 *
 * @author ArTIK
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private List<Shoe> shoes = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Purchase> purchases = new ArrayList<>();
    private List<Income> incomes = new ArrayList<>();
    private Keeping keeper = new SaverToBase();
//    private final SaverToFiles saverToFiles = new SaverToFiles();
    Income income = new Income();
    
     public App(){
        shoes = keeper.loadShoes();
        incomes = keeper.loadIncome();
        users = keeper.loadUsers();
        purchases = keeper.loadPurchases();
    }
    
    public void run(){
       int exit = 0;
       do{
           System.out.println("Выберите номер задачи: ");
           System.out.println("0: Закрыть программу");
           System.out.println("1: Добавить пользователя");
           System.out.println("2: Добавить модель обуви");
           System.out.println("3: Список обуви");
           System.out.println("4: Купить обувь");
           System.out.println("5: Список пользователей");
           System.out.println("6: Доход магазина");
           System.out.println("7: Редактировать пользователя");
           System.out.println("8: Редактировать кроссовки");
           System.out.println("9: Доход за определенный месяц");
           int task = getNumber();
           switch (task) {
               case 0:
                   exit++;
                   break;
               case 1:
                   addUser();
                   break;
               case 2:
                   addShoes();
                   break;
               case 3:
                   printListShoes();
                   break;
               case 4:
                   purchaseShoes();
                   break;
               case 5:
                   printListUsers();
                   break;
               case 6:
                   incomes();
                   break;
               case 7:
                   changeUsers();
                   break;
               case 8:
                   changeShoes();
                   break;
               case 9:
                   IncomePerMonth();
                   break;
               default:
                   System.out.println("Выберите номер из списка!");
           }
            
        }while(exit==0);
        System.out.println("Выход.  ");
    }
    // -------------------------------------------------------------------------   
    private void addUser() {
        User user = new User();
        System.out.print("Введите имя пользователя: ");
        user.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию пользователя: ");
        user.setLastname(scanner.nextLine());
        System.out.print("Введите телефон пользователя: ");
        user.setPhone(scanner.nextLine());
        System.out.print("Введите баланс пользователя: ");
        user.setBalance(scanner.nextDouble());
        System.out.println("Пользователь создан: "+user.toString());
        users.add(user);
        keeper.saveUsers(users);
    }
    private void addShoes() { 
    System.out.println("--- Добавление модели обуви ---");
    Shoe shoe= new Shoe();
    System.out.print("Введите количество кроссовок для добавления: ");
    shoe.setQuntity(scanner.nextInt());scanner.nextLine();
    shoe.setCount(shoe.getQuntity());
    System.out.print("Введите фирму модели: ");
    shoe.setShoeFirm(scanner.nextLine()); 
    System.out.print("Введите название модели: ");
    shoe.setShoeName(scanner.nextLine());
    System.out.print("Введите цену кроссовка: ");
    shoe.setCost(scanner.nextInt()); scanner.nextLine();
    System.out.println("Вы добавили "+shoe.toString());
    shoes.add(shoe);
    shoes.add(shoe);
    keeper.saveShoes(shoes);
    }

    private Set <Integer> printListShoes() {
        Set<Integer> setNumbersShoes = new HashSet<>();
        System.out.println("Список обуви: ");
        for (int i = 0; i < shoes.size(); i++) {
            if(shoes.get(i) != null 
                    && shoes.get(i).getCount() > 0
                    && shoes.get(i).getCount() < shoes.get(i).getQuntity()+ 1){
                System.out.printf("%d %s цена: %s колличество: %d %n"
                       ,i+1
                       ,shoes.get(i).getShoeName()
                       ,shoes.get(i).getCost()
                       ,shoes.get(i).getCount()
               );
                setNumbersShoes.add(i+1);
           }

       }
        return setNumbersShoes;
    }
    
    private void purchaseShoes() {
        
        System.out.println("---- Купить обувь ----");
       System.out.println("Список обуви: ");
        printListShoes();
        System.out.print("Выберите нужную модель обуви:");
        int shoeNum= scanner.nextInt(); scanner.nextLine();
        System.out.println("-----------------------------");
        printListUsers();
        System.out.print("Выберите нужного покупателя: ");
        int userNum= scanner.nextInt(); scanner.nextLine();
        int n = 0;
        while (n==0){
        Purchase purchase= new Purchase();
        purchase.setShoe(shoes.get(shoeNum-1));
        purchase.setUser(users.get(userNum-1));
        Calendar c = new GregorianCalendar();
        purchase.setPurchaseShoe(c.getTime());
        if(purchase.getUser().getBalance()>=purchase.getShoe().getCost()&& purchase.getShoe().getQuntity()!=0){
            System.out.println("-----------------------------");
            System.out.printf("Кроссовки %s  купил %s %s за %s евро %s%n",
            purchase.getShoe().getShoeFirm(),
            purchase.getUser().getFirstname(),
            purchase.getUser().getLastname(),
            purchase.getShoe().getCost(),
            purchase.getPurchaseShoe()
            );
            purchase.getUser().setBalance(purchase.getUser().getBalance()-purchase.getShoe().getCost());
            income.setGeneralMoney(income.getGeneralMoney()+purchase.getShoe().getCost());
            purchase.getShoe().setCount(purchase.getShoe().getCount()-1);
            incomes.add(income);
            purchases.add(purchase);
            keeper.saveShoes(shoes);
            keeper.saveUsers(users);
            keeper.savePurchases(purchases);
            keeper.saveIncomes(incomes);
            n++;
        }else if(purchase.getUser().getBalance()<purchase.getShoe().getCost()){
            System.out.println("Этот пользователь не может совершить покупку, так как у него не хватает денег.");   
        }else if(purchase.getShoe().getQuntity()==0){
            System.out.println("Этого товара нет в наличии, выберите другой!");
        }
        }
    }
    
    private void incomes() {
        System.out.println("---Доход магазина---");
        int sum=0;
        for (int i = 0; i < incomes.size(); i++) {
            sum +=incomes.get(i).getGeneralMoney();

          }
        System.out.printf("Выручка магазина составляет: %s Евро ",sum);
    }
    private Set <Integer> printListUsers() {
        System.out.println("----- Список пользователей -----");
        Set<Integer> setNumbersUsers = new HashSet<>();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i)!=null){
                System.out.printf("%d. %s %s, номер телефона: %s, доступные деньги: %s.евро%n",
                i+1,
                users.get(i).getFirstname(),
                users.get(i).getLastname(),
                users.get(i).getPhone(),
                users.get(i).getBalance()
               );
                    setNumbersUsers.add(i+1);
                }
            }
            if(setNumbersUsers.isEmpty()){
                System.out.println("Список пользователей пуст.");
            }
        return setNumbersUsers;
    }
    
    private int getNumber() {
        int number;
        do{
           String strNumber = scanner.nextLine();
           try {
               number = Integer.parseInt(strNumber);
               return number;
           } catch (NumberFormatException e) {
               System.out.println("Введено \""+strNumber+"\". Выбирайте номер ");
           }
       }while(true);
    }

    private int insertNumber(Set<Integer> setNumbers) {
        int number=0;
        do{
           number = getNumber();
           if(setNumbers.contains(number)){
               break;
           }
           System.out.println("Попробуй еще раз: ");
       }while(true);
       return number; 
    }
    
    private Set <Integer> changeUsers() {        
        Set<Integer> changeNumber = new HashSet<>();
        changeNumber.add(1);
        changeNumber.add(2);
        Set<Integer> setNumbersUsers = printListUsers();
        if(setNumbersUsers.isEmpty()){
        }
        System.out.println("Выберите номер пользователя: ");
        int numberUser = insertNumber(setNumbersUsers);
        System.out.println("Имя пользователя: "+users.get(numberUser - 1).getFirstname());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        int change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое имя пользователя: ");
            users.get(numberUser - 1).setFirstname(scanner.nextLine());
        }
        System.out.println("Фамилия пользователя: "+users.get(numberUser - 1).getLastname());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новую фамилию пользователя: ");
            users.get(numberUser - 1).setLastname(scanner.nextLine());
        }
        System.out.println("Телефон пользователя: "+users.get(numberUser - 1).getPhone());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новый телефон пользователя: ");
            users.get(numberUser - 1).setPhone(scanner.nextLine());
            keeper.saveUsers(users);
        }
        return setNumbersUsers;
    }
    private Set<Integer> changeShoes() {
        Set<Integer> changeNumber = new HashSet<>();
        changeNumber.add(1);
        changeNumber.add(2);
        Set<Integer> setNumbersShoes = printListShoes();
        if(setNumbersShoes.isEmpty()){
            System.out.println("В базе нет ботинок");
            
        }
        System.out.println("Выберите номер ботинок: ");
        int numberShoe = insertNumber(setNumbersShoes);
        System.out.println("Название ботинок: "+shoes.get(numberShoe - 1).getShoeName());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        int change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое название ботинок: ");
            shoes.get(numberShoe - 1).setShoeName(scanner.nextLine());
        }
        System.out.println("Количество экземпляров ботинок: "+shoes.get(numberShoe - 1).getQuntity());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое количество экземпляров ботинок: ");
            int newQuantity = getNumber();
            int oldQuantity = shoes.get(numberShoe - 1).getQuntity();
            int oldCount = shoes.get(numberShoe - 1).getCount();
            int newCount = oldCount + (newQuantity - oldQuantity);
            shoes.get(numberShoe - 1).setQuntity(newQuantity);
            shoes.get(numberShoe - 1).setCount(newCount);
            keeper.saveShoes(shoes);
        }
        return setNumbersShoes;
       }
    
    private void IncomePerMonth(){
        double income = 0;
        System.out.println("Доход за определенный месяц.");
        System.out.println("1 - Январь");
        System.out.println("2 - Февраль");
        System.out.println("3 - Март");
        System.out.println("4 - Апрель");
        System.out.println("5 - Май");
        System.out.println("6 - Июнь");
        System.out.println("7 - Июль");
        System.out.println("8 - Август");
        System.out.println("9 - Сентябрь");
        System.out.println("10 - Октябрь");
        System.out.println("11 - Ноябрь");
        System.out.println("12 - Декабрь");
        System.out.print("Введите нужный месяц: ");
        int choicemonth=getNumber()-1;
        System.out.print("Введите год: ");
        int years=getNumber();
        for (int i = 0; i < purchases.size(); i++) {
            Date date = purchases.get(i).getPurchaseShoe();
            boolean toSum = summator(date, choicemonth, years);
            if (purchases.get(i)!=null & toSum) {
               income+=purchases.get(i).getShoe().getCost();
            }

        }
        System.out.printf("Доход за выбранный месяц: %s евро%n", income);
    }
    private boolean summator(Date date, int choicemonth, int years) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            if(month==choicemonth && year==years){
                return true;
        }
            return false;
    }
}


