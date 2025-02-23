
package model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


public class Transaction {
    
    private String id;
    private String account_number;
    private double amount;
    private String time;
    private boolean status;
    
    public Transaction () {}
    
    public Transaction (String accountNumber, double amount, boolean status) {
        this.id = UUID.randomUUID().toString().split("-")[4];
        this.account_number = accountNumber;
        this.amount = amount;
        this.time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        this.status = status; 
    }
    
    public String getTime () { return time; }
    
    public boolean getStatus () { return status; }
    
    public String getId () { return id; }
    
    public double getAmount () { return amount; }
    
    public String getAccountNumber () { return account_number; }
    
    // Định dạng hiển thị của tiền
    
    
    @Override
    public String toString () {
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formatted_amount = currencyFormat.format(amount).replace("₫", "").trim() + "₫";
        
        return String.format("|%-15s| %-8s| %-15s| %-20s| %-10s|",
                id, account_number, formatted_amount, time, status ? "Sucess" : "Fuilure");
    }
}
