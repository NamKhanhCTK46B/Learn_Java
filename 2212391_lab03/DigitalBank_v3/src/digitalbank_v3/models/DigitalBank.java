
package digitalbank_v3.models;

import digitalbank_v3.models_lab2.Account;
import digitalbank_v3.models_lab2.Bank;
import digitalbank_v3.models_lab2.Customer;

public class DigitalBank extends Bank {
    
    // Tìm khách hàng theo CCCD
    public Customer getCustomerById (String customerId) {
        for (Customer customer : getCustomers()) {
            if (customer.getCustomerId().equals(customerId))
                return customer;
        }
        return null;
    }
    
    // Thêm khách hàng
    public boolean addNewCustomer (String customerId, String name) {
        if (isCustomerExisted(customerId)) {
            System.out.println("Customer already exists");
            return false;
        }
        Customer new_customer = new DigitalCustomer(customerId, name) {};
        return addCustomer(new_customer);
    }
    
    // Thực hiện rút tiền từ tài khoản của khách hàng
    public void withdraw (String customerId, String account_number, double amount) {
        // Tìm khách hàng theo CCCD
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }
        
        // Thực hiện rút tiền
        if (customer instanceof DigitalCustomer) {
            ((DigitalCustomer) customer).withdraw(account_number, amount);
        }
        else {
            System.out.println("Customer is not eligible for this operation.");
        }
    }
    
    // Hiển thị thông tin của khách hàng
    public void displayCustomerInfor (String customer_id) {
        Customer customer = getCustomerById(customer_id);
        if (customer == null) 
            System.out.println("Customer not found");
        else
            customer.displayInformation();
    }
    
    // Thêm tài khoản cho khách hàng
    public void addNewAccount (String customer_id, String account_number, double balance, String acc_type) {
        if (isAccountExisted(account_number)) {
            System.out.println("Account number already exists");
        }
        
        Customer customer = getCustomerById(customer_id);
        
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }
        
        Account new_acc;
        switch (acc_type.toLowerCase()) {
            case "saving": // Thêm tài khoản saving
                
                new_acc = new SavingsAccount(account_number, balance) {};
                
                break;
                
            case "loan": // Thêm tài khoản loan
                
                new_acc = new LoansAccount(account_number, balance) {};
                
                break;
            default:
                System.out.println("Invalid account type. Use 'savings' or 'loan'.");
                return;
        }
        
        addAccount(customer_id, new_acc);
        System.out.printf("%s account added successfully.%n", acc_type.substring(0, 1).toUpperCase() + acc_type.substring(1));
    }
    
    // Hiển thị lịch sử rút tiền của khách hàng
    public void displayTranactionHistory (String customer_id){
        
        Customer customer = findCustomerById(customer_id);
        
        if (customer == null)
            System.out.println("Customer not found");
        
        customer.displayInformation();
        
        System.out.println("|" + "-".repeat(76) + "|");
        System.out.printf("|%76s|\n","TRANSACTION HISTORY");
        System.out.println("|" + "-".repeat(76) + "|");
        customer.displayTransactionHistory();
        System.out.println("|" + "-".repeat(76) + "|");      
    }
}
