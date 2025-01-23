/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Admin
 */
public abstract class User {
    
    private String name;
    private String customerId;
    
    public User () {}
    
    public User (String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }
    
    public String getName() { return name; }
    
    public void setName(String name) {this.name = name;}
    
    public String getCustomerId () { return customerId; }
    
    public void setCustomerId (String customerId) {
            this.customerId = customerId;
    }
    
}
    
    