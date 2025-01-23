package pkg2212391_lab01;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2212391_lab01;

import java.util.Scanner;
import pkg2212391_lab01.ThuVien;

/**
 *
 * @author Admin
 */
public class Menu {
    ThuVien thuVien = new ThuVien();
    
    public void XuatMenu (){
        String tieuDe = "NGAN HANG SO";
        String tacGia = "2212391";
        String phienBan = "1.0.0";
        
        System.out.println("+----------+--------------------+----------+");
        System.out.printf ("| %-40s |\n",tieuDe + "|" + tacGia + "@" + phienBan);
        System.out.println("+----------+--------------------+----------+");
        System.out.println("| 1. Nhap CCCD                             |");
        System.out.println("| 0. Thoat                                 |");
        System.out.println("+----------+--------------------+----------+");
    }
    
    public int NhapMenu (int soMenu){
        System.out.print("Chuc nang: ");
        int chon = -1;
        while (true) {            
            Scanner cnChon = new Scanner(System.in);
            chon = cnChon.nextInt();
            
            if (chon >= 0 && chon <= 1)
                break;
        }
        
        return chon;
    }
    
    public void XuLy (int chon) {
        switch (chon) {
            case 0:
                System.out.println("0. Thoat");
                System.exit(chon);
                break;
            case 1:
                System.out.println("1. Nhap CCCD");
                    
                //Nhap ma xac thuc
                thuVien.NhapMaXacThuc();
                System.out.println("Nhap cccd");
                    
                break;
                default:
            }
        }
    }
    
    // Menu cho chuc nang 1
    public void XuatMenu_CN1 () {
        System.out.println("\t| 1. Kiem tra noi sinh");
        System.out.println("\t| 2. Kiem tra tuoi, gioi tinh");
        System.out.println("\t| 3. Kiem tra so ngau nhien");
        System.out.println("\t| 0. Thoat");
    }
}
