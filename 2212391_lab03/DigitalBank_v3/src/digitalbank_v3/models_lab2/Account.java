
package digitalbank_v3.models_lab2;

import digitalbank_v3.models.Transaction;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public abstract class Account {
    
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;
    
    public Account() {
        transactions = new ArrayList<>();
    }
    
    public Account (String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        transactions = new ArrayList<>();
    }
    
    public double getBalance () {return balance;}
    public void setBlance (double balance) {
        if (balance < 0)
            System.out.println("Không được nhập số tiền âm");
        else
            this.balance = balance;
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
        
        return String.format("%-7s| %30s| %32s|", accountNumber, getAccountType(), formattedBalance);
    }
    
    public void addTransaction (Transaction tran) {
        transactions.add(tran);
    }
    
    public List<Transaction> getTransactions () { return transactions; }
    
    public void displayTransaction () {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
    
    public abstract String getAccountType ();

    public Account(String accountNumber, double balance, List<Transaction> transactions) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = transactions;
    }
    
}