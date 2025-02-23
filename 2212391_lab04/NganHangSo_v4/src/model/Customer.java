
package model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public abstract class Customer extends User{
    
    private List<Account> accounts;
    
    public Customer () {
        accounts = new ArrayList<>();
    }
    
    public Customer (String customerId, String name) {
        super(customerId, name);
        accounts = new  ArrayList<>();
    }
    
    public Customer (String customerId, String name, List<Account> accounts) {
        super(customerId, name);
        this.accounts = accounts;
    }
    
    public List<Account> getAccount () {
        return accounts;
    }
    
    public Account getAccByAcountNumber (String accountNumber) {
        Account result = null;
        for (Account acc: accounts) {
            if (acc.getAccountNumber().trim().equals(accountNumber.trim())) {
                result = acc;
                break;
            }
        }
        return result;
    }
    
    public void updateAccountBalance (String accountNumber, double newBalance) {
        for (Account account : accounts) {
            if (account.getAccountNumber().trim().equals(accountNumber.trim())) {
                account.setBlance(newBalance);
            }
        }
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
    
    public void removeAccount (String accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber().equals(accountNumber)) {
                accounts.remove(i);
                break;
            }
        }
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
    
    public void displayTransactionHistory () {
        System.out.printf("|%-15s| %-8s| %-15s| %-20s| %-10s|\n",
                "ID", "Account", "Amount", "Time", "Status");
        
        for (Account acc : accounts) {
            acc.displayTransaction();
        }
    }
    
}
