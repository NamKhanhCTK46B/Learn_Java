/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Admin
 */
public abstract class Account {
    
    private String accountNumber;
    private double balance;
    
    public Account() {}
    
    public Account (String accountNumber, double blance) {
        this.accountNumber = accountNumber;
        this.balance = blance;
    }
    
    public double getBalance () {return balance;}
    public void setBlance (double blance) {
        if (blance < 0)
            System.out.println("Không được nhập số tiền âm");
        else
            this.balance = blance;
    }
    
    public String getAccountNumber () { return accountNumber; }
    public void setAccountNumber (String accountNumber) { this.accountNumber = accountNumber; }
    
    public boolean isPremium () {
        return balance >= 10000000;
    }
    
    @Override
    public String toString () {
       NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedBalance = currencyFormat.format(balance).replace("₫", "").trim() + "₫";
        
        return String.format("%-6s | %64s|\n", accountNumber, formattedBalance);
    }
}
