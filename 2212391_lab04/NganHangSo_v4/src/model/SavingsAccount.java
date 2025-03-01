
package model;


import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SavingsAccount extends Account implements ReportService, Withdraw {
    
    public static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    public static final double MIN_BALANCE = 50000;
    
    private Transaction trans ;
    private double balance;
    
    @Override
    public void log(double amount) {
        
        NumberFormat currency_format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formatted_balance = currency_format.format(balance).replace("₫", "").trim() + "₫";
        String formatted_amount = currency_format.format(amount).replace("₫", "").trim() + "₫";  
        
        String logMessage = String.format("""
                                          +----------+-------------------------+----------+
                                                     TRANSACTION RECEIPT SAVINGS
                                          +----------+-------------------------+----------+
                                          \t%-12s %24s
                                          \t%-12s %24s
                                          \t%-12s %24s
                                          \t%-12s %24s
                                          \t%-12s %24s
                                          \t%-12s %24s
                                          +----------+-------------------------+----------+""",
                "Time",trans.getTime(),
                "ID",trans.getId(),
                "Account",getAccountNumber(),
                "Amount", formatted_amount,
                "Balance", formatted_balance,
                "Fee + VAT", "0đ");
        
       try (FileWriter writer = new FileWriter("transaction_log.txt", true)) {
            writer.write(logMessage);
        } 
        catch (IOException ex) {
            Logger.getLogger(LoansAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override // Phương thức dùng để thực hiện hoạt động rút tiền
    public boolean withdraw(double amount) {
        
        boolean status = isAccepted(amount);
        
        if (status) {
            balance = getBalance() - amount;
            trans = new Transaction(getAccountNumber(), amount, status);
            addTransaction(trans);
        
            log(amount);
        }
         
        return status;
    }

    @Override
    public boolean isAccepted(double amount) {
        
        return amount >= MIN_BALANCE && // Số tiền rút >= 50.000đ
               (amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW || isPremium()) && // Số tiền rút 1 lần <= 5.000.000 hoặc ko giới hạn với tk premium
               amount % 10000 == 0 && // Số tiền rút phải là bội số của 10.000đ
               getBalance() - amount >= MIN_BALANCE; //Số dư còn lại sau khi rút phải lớn hơn hoặc bằng 50.000đ

    }
    
    public SavingsAccount () {}
    
    public SavingsAccount (String firstName, String lastName, String password, String accountNumber, double balance) {
        super(firstName, lastName, password, accountNumber, balance);
    }
    
    @Override
    public String getAccountType () {
        return "Savings";
    }
}
