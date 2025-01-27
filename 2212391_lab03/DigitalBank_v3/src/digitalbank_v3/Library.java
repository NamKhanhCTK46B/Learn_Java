
package digitalbank_v3;

import digitalbank_v3.models.DigitalBank;
import java.util.Scanner;


public class Library {
    
    Scanner scan = new Scanner(System.in);
    DigitalBank active_bank = new DigitalBank();
    
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
    private boolean checkProvince (String province_code) {
        for (String p : listProvince){
            if (province_code.equals(p))
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
    
    // Phương thức nhập CCCD
    public String enterCustomerId () {
        String customer_id;
        for (;;) {
            System.out.print("Enter Customer ID: ");
            customer_id = scan.next();
            
            if (checkCustomerId(customer_id))
                break;
        }
        
        return customer_id;
    }
    
    // Phương thức hiển thị thông tin khách hàng
    public void showCustomerInfor (String customer_id) {
        active_bank.displayCustomerInfor(customer_id);
    }
    
    // Phương thức thêm tài khoản
    public void addAccount (String acc_type) {
        
        String account_number;
        double balance;
        
        String customer_id = enterCustomerId();
        
        // Nhập thông tin tài khoản
        for (;;) { 
            System.out.print("Enter Account Number (6 digits): "); 
            account_number = scan.next();
            
            if (account_number.trim().length() != 6 || !account_number.matches("\\d{6}"))
                System.out.println("The account number has the wrong length or does not contain non-numeric characters");
            else
                break;
        }
        
        System.out.print("Enter Balance: "); 
        balance = scan.nextDouble();
        
        active_bank.addNewAccount(customer_id, account_number, balance, acc_type);
    }
    
    // Phương thức nhập cố định khách hàng 
    public void initSampleData () {
        
        active_bank.addNewCustomer("037456789112", "Evan");
        
        active_bank.addNewAccount("037456789112", "123456", 1000000, "saving");
        active_bank.addNewAccount("037456789112", "234567", 10000000, "loan");
    
    }
    
    // Phương thức thực thi rút tiền
    public void withdraw () {
        String customer_id = enterCustomerId();
        
        System.out.print("Enter Account number: ");
        String account_number = scan.next();
        
        System.out.print("Enter Amount to withdraw: ");
        double amout = scan.nextDouble();
        
        active_bank.withdraw(customer_id, account_number, amout);
    }
    
    // Phương thức hiển thị lịch sử rút tiền của khách hàng
    public void showTransactionHistory () {
        String customer_id = enterCustomerId();
        
        active_bank.displayTranactionHistory(customer_id);
    }
    
}
