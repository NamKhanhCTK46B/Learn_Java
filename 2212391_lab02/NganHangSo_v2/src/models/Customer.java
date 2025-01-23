/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
 
/**
 *
 * @author Admin
 */
public abstract class Customer extends User{
    
    private ArrayList<Account> accounts;
    
    public Customer () {
        accounts = new ArrayList<>();
    }
    
    public Customer (String customerId, String name, ArrayList<Account> accounts) {
        super(customerId, name);
        this.accounts = accounts;
    }
    
    public ArrayList<Account> getAccount () {
        return accounts;
    }
    
    public boolean isPremium () {
        for (Account acc: accounts) {
            if (acc.isPremium())
                return true;
        }
        return false;
    }
    
    public void addAccount (Account acc) {
        accounts.add(acc);
    }
    
    public double getBlance () {
        double total = 0;
        
        for (Account acc: accounts) {
            total += acc.getBalance();
        }
        
        return total;
    }
    
    public void displayInformation () {
        String customerId = getCustomerId();
        String name = getName();
        String type = "Normal";
        double balance = 0;
        int count = 1;
        
        if (isPremium())
            type = "Premium";
        
        for (Account acc: accounts) {
            balance += acc.getBalance();
        }
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedBalance = currencyFormat.format(balance).replace("₫", "").trim() + "₫";
        
        System.out.printf("|%15s| %30s| %15s| %15s|\n", customerId, name, type, formattedBalance);
        for (Account acc: accounts) {
            System.out.printf("|%-7d %s\n", count,acc);
            
            count += 1; 
        }
    }
}
