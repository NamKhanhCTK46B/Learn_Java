/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nganhangso_v2;

import java.util.Scanner;
import models.Customer;

/**
 *
 * @author Admin
 */
public class Menu {
    
    Handle handle = new Handle();
    
    private void xuatMenu () {
        
        String tieuDe = " NGÂN HÀNG SỐ";
        String sv = "2212391";
        String phienBan = "@v2.0.0";
        
        System.out.println("+--------------+--------------------+-------------+");
        System.out.printf("|%-48s | \n", tieuDe + " | " + sv + phienBan);
        System.out.println("+--------------+--------------------+-------------+");
        System.out.printf("|%-48s | \n", "1. Thêm khách hàng");
        System.out.printf("|%-48s | \n", "2. Thêm tài khoản cho khách hàng");
        System.out.printf("|%-48s | \n", "3. Hiển thị danh sách khách hàng");
        System.out.printf("|%-48s | \n", "4. Tìm theo CCCD");
        System.out.printf("|%-48s | \n", "5. Tìm theo tên khách hàng");
        System.out.printf("|%-48s | \n", "6. Nhập khách hàng cố định");
        System.out.printf("|%-48s | \n", "0. Thoát");
        System.out.println("+--------------+--------------------+-------------+");
    }
    
    private int chonMenu (int soMenu) {
        int chon;
        
        for (;;) {
            System.out.print("Chức năng: ");
            Scanner scan = new Scanner(System.in);
            chon = scan.nextInt();
            
            if (chon >= 0 && chon <= soMenu)
                break;
            else
                System.out.println("Không có chức năng mà bạn chọn. Vui lòng chọn lại");
        }
        
        return chon;
    }
    
    private void xuLy (int chon) {
        Customer customer;
        switch (chon) {
            case 0:
                System.out.println("0. Thoát");
                System.out.println("+--------------+--------------------+-------------+");
                System.exit(chon);
                
                break; 
            case 1:
                System.out.println("1. Thêm khách hàng");
                
                handle.addCustomer();
                
                System.out.println("+--------------+--------------------+-------------+");
                break;
            case 2:
                System.out.println("2. Thêm tài khoản cho khách hàng");
                
                handle.addAcount();
                
                System.out.println("+--------------+--------------------+-------------+");
                break;
            case 3:
                System.out.println("3. Hiển thị danh sách khách hàng");
                
                handle.showCustomers();
                
                System.out.println("+--------------+--------------------+-------------+");
                break;
            case 4:
                System.out.println("4. Tìm theo CCCD");
                
                handle.showCustomers();
                customer = handle.findCustomerById();
                handle.showFindedCustomer(customer);
                
                System.out.println("+--------------+--------------------+-------------+");
                break;
            case 5:
                System.out.println("5. Tìm theo tên khách hàng");
                
                handle.showCustomers();
                customer = handle.findCustomerByName();
                handle.showFindedCustomer(customer);
                
                System.out.println("+--------------+--------------------+-------------+");
                break;
            case 6:
                System.out.println("6. Nhập khách hàng cố định");
                
                handle.enterStaticCustomer();
                System.out.println("Thêm khách hàng thành công");
                
                System.out.println("+--------------+--------------------+-------------+");
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public void runProgram () {
        int soMenu = 6, chon;
        
        xuatMenu();
        do {            
            chon = chonMenu(soMenu);
            xuLy(chon);
        } while (chon != 0);
    }           
}
