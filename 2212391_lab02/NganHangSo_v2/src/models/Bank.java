/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class Bank {
    
    private String id = "";
    private ArrayList<Customer> customers;
    
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
        for (Customer cus: customers) {
            for (Account acc: cus.getAccount()) {
                if (acc.getAccountNumber().equals(accountNumber.trim()))
                    return true;
            }
        }
        
        return false;
    }
    
    public ArrayList<Customer> getCustomers() { return customers; }
    
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
