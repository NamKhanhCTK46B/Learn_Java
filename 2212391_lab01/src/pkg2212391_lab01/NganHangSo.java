/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2212391_lab01;

/**
 *
 * @author Admin
 */
import java.util.Scanner;

public class NganHangSo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        
        int soMenu = 2, chon;
        menu.XuatMenu();
        do {
            chon = menu.NhapMenu(soMenu);
            menu.XuLy(chon);
        } while (chon != 0);
        
    }
    
}
