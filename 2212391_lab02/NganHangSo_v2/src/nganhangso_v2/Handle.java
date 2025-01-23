/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nganhangso_v2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import models.Account;
import models.Bank;
import models.Customer;

/**
 *
 * @author Admin
 */
public class Handle {
    
    private Bank bank = new Bank ();
    Scanner scan = new Scanner(System.in);
    
    // Phương thức nhập khách hàng
    private Customer enterCustomer () {
        Customer newCustomer = new Customer() {};
        String customerId;
        String name;
        
        for (;;) {
            System.out.print ("Nhập CCCD của khách hàng: ");
            customerId = scan.next();
            if (checkCustomerId(customerId)) {
                newCustomer.setCustomerId(customerId);
                break;
            }
        }
        
        System.out.print ("Nhập tên của khách hàng: ");
        name = scan.next();
        newCustomer.setName(name);
        
        return newCustomer;
    }
    
    // Danh sách các mã tỉnh
    private final String[] listProvince = { "001", "002", "004", "006", "008",
        "010", "011", "012", "014", "015", "017", "019", 
        "020", "022", "024", "025", "026", "027", 
        "030", "031", "033", "034", "035", "036", "037", "038", 
        "040", "042", "044", "045", "046", "048", "049", 
        "051", "052", "054", "056", "058", 
        "060", "062", "064", "066", "067", "068",
        "070", "073", "074", "075", "077", "079", 
        "080", "082", "083", "084", "086", "087", "089", 
        "091", "092", "093", "094", "095", "096"};
    
    // Phương thức kiểm tra mã tỉnh
    private boolean checkProvince (String provinceCode) {
        for (String p : listProvince){
            if (provinceCode.equals(p))
                return true;
        }
        return false;
    }
    
    // Phương thức kiểm tra hợp lệ của căn cước công dân
    private  boolean checkCustomerId (String id) {
        if (id == null || id.trim().length() != 12 || !id.matches("\\d{12}")) {
            System.out.println("CCCD không hợp lệ: Độ dài không đúng hoặc chứa ký tự không phải số");
            return false;
        }
        
        String provinceCode = id.substring(0,3);
        
        if (!checkProvince(provinceCode)) {
            System.out.println("CCCD không hợp lệ: Mã tỉnh không hợp lệ");
            return false;
        }
        
        return true;
    }
    
    // Phương thức thêm khách hàng
    public void addCustomer () {
        System.out.print("Nhập số khách hàng: ");
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.printf("Nhập thông tin của khách hàng thứ %d\n", i + 1);
            for (;;) {
                 Customer newCustomer = enterCustomer();
                
                if (!bank.addCustomer(newCustomer))
                    System.out.println("Khách hàng đã tồn tại. Vui lòng thử lại");
                else
                    break;  
            }    
        }
        
        System.out.println("Thêm khách hàng thành công");
    }
    
    // Phương thức nhập thông tin tài khoản
    public Account enterAccount () {
        Account newAccount = new Account() {};
        String accountNumber;
        double balance;
        
        for (;;) { 
            System.out.print("Nhập số tài khoản gồm 6 chữ số: "); 
            accountNumber = scan.next();
            
            if (accountNumber.trim().length() != 6 || !accountNumber.matches("\\d{6}")) {
                System.out.println("Số tài khoản có độ dài không đúng hoặc có ký tự không phải số");
            }
            else if (bank.isAccountExisted(accountNumber)) {
                System.out.println("Đã tồn tại tài khoản có số tài khoản " + accountNumber);
            }
            else {
                newAccount.setAccountNumber(accountNumber);
                break;
            }
        }
        
        for (;;) {
            System.out.print("Nhập số dư của tài khoản (số dư > 50.000): ");
            balance = scan.nextDouble();
            
            if (balance < 50000)
                System.out.println("Số dư tài khoản không được nhỏ hơn 50.000");
            else {
                newAccount.setBlance(balance);
                break;
            }
        }
        
        return newAccount;
    }
    
    // Phương thức nhập căn cước công dân
    public String enterCustomerId () {
        String customerId = "";
        
        for (;;) {
            System.out.print("Nhập CCCD của khách hàng: ");
            customerId = scan.next();
            
            if (!bank.isCustomerExisted(customerId))
                System.out.println("Không tìm thấy khách hàng có CCCD " + customerId);
            else
                break;
        }
        
        return customerId;
    }
    
    // Phương thức thêm tài khoản
    public void addAcount () {
        int n;
        String customerId;
        Account acc;
        
        customerId = enterCustomerId();
        
        System.out.print("Nhập số lượng tài khoản muốn thêm: ");
        n = scan.nextInt();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập thông tin tài khoản thứ " + (i + 1));
            acc = enterAccount();
            bank.addAccount(customerId, acc);
        }
        System.out.print("Thêm tài khoản thành công");
    }
    
    // Phương thức dùng để in dấu gạch ngang
    public void printDiver (String character) {
        System.out.println("|" + character.repeat(81) + "|");
    }
    
    // Phương thức dùng để xuất tiêu đề của bảng (totalWidth: độ dài của dòng)
    public void printTitle (String title, int totalWidth) {
        int padding = (totalWidth - title.length()) / 2;
        System.out.println("|" + " ".repeat(padding) + title + " ".repeat(padding + 1) + "|");
    }
    
    // Phương thức hiển thị danh sách khách hàng
    public void showCustomers () {
        printDiver("=");
        printTitle("DANH SÁCH KHÁCH HÀNG", 81);
        printDiver("=");
        
        ArrayList<Customer> customers = bank.getCustomers();
        
        for (Customer customer: customers) {
            customer.displayInformation();
            printDiver("-");
        }
    }
    
    // Phương thức nhập khách hàng cố định
    public void enterStaticCustomer () {
        ArrayList<Account> accounts_1 = new ArrayList<>();
        accounts_1.add(new Account("123456",1000000) {} );
        bank.addCustomer(new Customer("037456789111", "David", accounts_1) {});
        
        ArrayList<Account> accounts_2 = new ArrayList<>();
        accounts_2.add(new Account("234567",10000000) {} );
        accounts_2.add(new Account("345678",2000000) {} );
        bank.addCustomer(new Customer("037456789112", "Evan", accounts_2) {});
        
    }
    
    public Customer findCustomerById () {
        String customerId = enterCustomerId();
        Customer result = bank.findCustomerById(customerId);
        return result;
    }
    
    public Customer findCustomerByName () {
        System.out.print("Nhập tên của khách hàng mà bạn muốn tìm: ");
        String name = scan.next();
        
        Customer customer = bank.findCustomerByName(name);
        
        return customer; 
    }
    
    public void showFindedCustomer (Customer customer) {
            printDiver("=");
            printTitle("KHÁCH HÀNG TÌM ĐƯỢC", 81);
            printDiver("=");
            customer.displayInformation();
            printDiver("=");
    }
}
