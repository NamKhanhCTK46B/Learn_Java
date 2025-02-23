
package model;

import java.util.List;


public abstract class DigitalCustomer extends Customer{
    
    public DigitalCustomer () {}
    
    public DigitalCustomer (String customerId, String name) {
        super(customerId, name);
    }
    
    public DigitalCustomer (String customerId, String name, List<Account> accounts) {
        super(customerId, name, accounts);
    }
    
    public boolean withdraw (String accountNumber, double amount) {
        for (Account account : getAccount()) {
            if (account.getAccountNumber().equals(accountNumber) && account instanceof Withdraw) {
                boolean result = ((Withdraw)account).withdraw(amount);
                //System.out.println(result ? "Withdrawal successful" : "Withdrawal failed");
                return result;
            }
        }
        
        return false;
        //System.out.println("Account not find or cannot withdraw");
    }
    
    
}
