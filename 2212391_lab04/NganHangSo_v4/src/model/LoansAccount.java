
package model;

import java.io.FileWriter;
import java.text.NumberFormat;
import java.util.Locale;
import java.lang.String;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoansAccount extends Account implements ReportService, Withdraw {
    
    public static final float LOAN_ACCOUNT_WITHDRAW_FEE = 0.05f;
    public static final float LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01f;
    public static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;
    
    private double balance; // số dư còn lại sau khi rút tiền
    private Transaction trans;
    private double fee; // phí rút tiền

    @Override
    public void log(double amount) {
        // Định dạng kiểu xuất của các biến biểu diễn tiền có kiểu double
        NumberFormat currency_format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formatted_balance = currency_format.format(balance).replace("₫", "").trim() + "₫";
        String formatted_fee = currency_format.format(fee).replace("₫", "").trim() + "₫";
        String formatted_amount = currency_format.format(amount).replace("₫", "").trim() + "₫";
        
        String logMessage = String.format("""
                                          +----------+-------------------------+----------+
                                                      TRANSACTION RECEIPT LOANS
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
                "Fee + VAT", formatted_fee);
        
        try (FileWriter writer = new FileWriter("transaction_log.txt", true)) {
            writer.write(logMessage);
        } catch (Exception e) {
            Logger.getLogger(LoansAccount.class.getName()).log(Level.SEVERE, null, e);
        }
         
    }

    @Override
    public boolean withdraw(double amount) {
        
        if (isPremium())
            fee = amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE;
        else
            fee = amount * LOAN_ACCOUNT_WITHDRAW_FEE;
        
        double total = amount + fee; // Tổng số tiền dùng để giao dịch
        
        boolean status = isAccepted(total);
        
        if (status) {
            balance = getBalance() - total;
            
            trans = new Transaction(getAccountNumber(), amount, status);
            addTransaction(trans);
        
            log(amount);
        }
        
        return status;
    }

    @Override
    public boolean isAccepted(double amount) {
        return amount <= LOAN_ACCOUNT_MAX_BALANCE &&
               getBalance() - amount >= SavingsAccount.MIN_BALANCE;
    }
    
    public LoansAccount () {}
    
    public LoansAccount (String firstName, String lastName, String password, String accountNumber, double balance) {
        super(firstName, lastName, password, accountNumber, balance);
    }

    @Override
    public String getAccountType() {
        return "Loans";
    }
}
