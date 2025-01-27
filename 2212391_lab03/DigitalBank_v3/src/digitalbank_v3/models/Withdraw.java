/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package digitalbank_v3.models;

/**
 *
 * @author Admin
 */
public interface Withdraw {
    
    boolean withdraw (double amount);
    
    boolean isAccepted (double amount);
    
}
