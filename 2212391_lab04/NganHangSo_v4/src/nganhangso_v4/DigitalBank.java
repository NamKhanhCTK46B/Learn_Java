
package nganhangso_v4;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Account;
import model.Customer;
import model.DigitalCustomer;
import model.LoansAccount;
import model.SavingsAccount;


public class DigitalBank {
    
    List<Customer> customers;
    
    public DigitalBank () {
        customers = new ArrayList<>();
        
        addSampleData();
    }
    
    public List<Customer> getCustomers () { return  customers; }
    
    public boolean isCustomerExisted (String cus_id) {
        for (Customer cus: customers) 
            if (cus.getCustomerId().equals(cus_id))
                return true;
        return false;
    }
    
    public void addCustomer (Customer newCustomer ) {
        customers.add(newCustomer);
    }
    
    public Customer getCustomerById (String customerId) {
        for (Customer customer : getCustomers()) {
            if (customer.getCustomerId().equals(customerId))
                return customer;
        }
        return null;
    }
    
    public boolean isAccountExisted (String accountNumber) {
        
        boolean result = false;
        
        for (Customer cus: customers) {
            
            if (cus.getAccount() == null)
                result = false;
            
            for (Account acc: cus.getAccount()) {
                if (acc.getAccountNumber().equals(accountNumber.trim()))
                    result = true;
            }
        }
        
        return result;
    }
    
    private String generateAccountNumber() {
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", ""); // Chỉ giữ số
        return uuid.substring(0, 10); // Lấy 10 số đầu tiên
    } 
    
    public void addNewAccount (String customer_id, String firstName, String lastName, String password, double balance, String acc_type) {
        
        String account_number = generateAccountNumber();
        
        Customer customer = getCustomerById(customer_id);
        
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }
        
        Account new_acc;
        switch (acc_type.toLowerCase()) {
            case "savings" -> // Thêm tài khoản saving
                
                new_acc = new SavingsAccount(firstName, lastName, password, account_number, balance) {};
                
            case "loans" -> // Thêm tài khoản loan
                
                new_acc = new LoansAccount(firstName, lastName, password, account_number, balance) {};
            default -> throw new IllegalAccessError("Invalid account type. Use 'savings' or 'loan'.");              
        }
        
        customer.addAccount(new_acc);
        
    }
    
    private void addSampleData () {
        
        addCustomer(new DigitalCustomer ("037456789112", "Evan") {});
        addCustomer(new DigitalCustomer ("037456789111", "David") {});
        
        addNewAccount("037456789112", "Evan", "Smith", "123", 700000.0, "savings");
        addNewAccount("037456789112", "Evan", "Smith", "123", 1500000.0, "loans");
        
        addNewAccount("037456789111", "David", "Johnson", "56", 2000000.0, "savings");
    }
    
    public void removeAccount (String customerId, String accountNumber) {
        Customer customer = getCustomerById(customerId);
        
        customer.removeAccount(accountNumber);
    }
    
    public Account getAccountByAccNumber (String customerId, String accountNumber) {
        Customer customer = getCustomerById(customerId);
        return customer.getAccByAcountNumber(accountNumber);
    }
    
    public void updateAccountBlance (String customerId, String accountNumber, double newBalance) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId))
                customer.updateAccountBalance(accountNumber, newBalance);
        }
    }
    
    public boolean withdraw (String customerId, String account_number, double amount) {
        // Tìm khách hàng theo CCCD
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            //System.out.println("Customer not found");
            return false;
        }
        
        // Thực hiện rút tiền
        if (customer instanceof DigitalCustomer) {
            return ((DigitalCustomer) customer).withdraw(account_number, amount);
        }
        else {
            //System.out.println("Customer is not eligible for this operation.");
            return true;
        }
    }
}
