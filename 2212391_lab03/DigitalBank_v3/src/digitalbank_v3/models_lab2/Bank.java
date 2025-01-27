
package digitalbank_v3.models_lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    
    private String id = "";
    private List<Customer> customers;
    
    public Bank() {
        customers = new ArrayList<>();
        this.id = String.valueOf(UUID.randomUUID());
    }
    
    public String getId() {return id;}
    
    public boolean addCustomer (Customer newCustomer ) {
        if (isCustomerExisted(newCustomer.getCustomerId())) {
            return false;
        }  
        else
            customers.add(newCustomer);
        return true;
    }
    
    public void addAccount (String customerId, Account acc) {
        for (Customer cus: customers) {
            if (cus.getCustomerId().equals(customerId)) {
                cus.addAccount(acc);
                break;
            }
        }
    }
    
    public boolean isCustomerExisted (String customerId) {
        for (Customer cus: customers) {
            if (cus.getCustomerId().equals(customerId))
                return true;
        }
        return false;
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
    
    public List<Customer> getCustomers() { return customers; }
    
    public Customer findCustomerById (String customerId) {
        Customer result = new Customer() {};
        
        for (Customer customer: customers) {
            if (customer.getCustomerId().equals(customerId.trim())) {
                result = customer;
            }
        }
        
        return result;
    }
    
    public Customer findCustomerByName (String name) {
        Customer result = new Customer() {};
        
        for (Customer customer: customers) {
            if (customer.getName().equals(name.trim())) {
                result = customer;
            }
        }
        
        return result;
    }

}
