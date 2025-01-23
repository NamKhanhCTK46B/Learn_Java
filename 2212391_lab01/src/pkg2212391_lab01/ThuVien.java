/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2212391_lab01;

import java.util.Random;
import java.util.Scanner;

public class ThuVien {
    
    public Scanner scanner = new Scanner(System.in);
    
    public int TaoMaXacThuc (){
        Random ran = new Random();
        return ran.nextInt(900) + 100;
    }
    
    public boolean KiemTraMaXacThuc (int tao, int nhap){ 
        return tao == nhap;
    }
    
    public void NhapMaXacThuc () {
        for(;;) {
            int maXT = TaoMaXacThuc();
            System.out.println("Ma xac thuc: " + maXT );
            System.out.print("Nhap ma xac thuc: " );
        
            int maNhap = scanner.nextInt();
            
            if (KiemTraMaXacThuc(maXT, maNhap))
                break;
        }
        
    }
    
    public void NhapCCCD (){
        
    }
}
