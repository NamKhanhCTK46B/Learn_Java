
package digitalbank_v3;

import java.util.Scanner;


public class Menu {
    
    private Library library = new Library();
    
    private void showMenu () {
        String title = "NGAN HANG SO";
        String author = "2212391";
        String version = "@v3.0.0";
        
        System.out.println("+--------------+--------------------+-------------+");
        System.out.printf("|%-48s | \n", title + " | " + author + version);
        System.out.println("+--------------+--------------------+-------------+");
        System.out.printf("|%-48s | \n", "1. Thông tin khách hàng");
        System.out.printf("|%-48s | \n", "2. Thêm tài khoản ATM");
        System.out.printf("|%-48s | \n", "3. Thêm tài khoản tín dụng");
        System.out.printf("|%-48s | \n", "4. Rút tiền");
        System.out.printf("|%-48s | \n", "5. Lịch sử giao dịch");
        System.out.printf("|%-48s | \n", "0. Thoát");
        System.out.println("+--------------+--------------------+-------------+");
    }
    
    private int chooseMenu (int menu_number) {
        int choose;
        Scanner scan = new Scanner(System.in);
        
        for (;;){
            System.out.print("Chọn chức năng: ");
            choose = scan.nextInt();
            
            if(choose >= 0 && choose <= menu_number)
                break;
        }
        
        return choose;
    }
    
    private void handleChoose (int choose) {
        String customer_id;
        
        
        switch (choose) {
            case 0:
                System.out.println("+--------------+--------------------+-------------+");
                System.out.printf("|%-48s | \n", "0. Thoát");
                System.out.println("+--------------+--------------------+-------------+");
                
                System.exit(choose);
                break;
            case 1:
                System.out.println("+--------------+--------------------+-------------+");
                System.out.printf("|%-48s | \n", "1. Thông tin khách hàng");
                System.out.println("+--------------+--------------------+-------------+");
                
                customer_id = library.enterCustomerId();
                library.showCustomerInfor(customer_id);
                
                System.out.println("+--------------+--------------------+-------------+");
                break;
            case 2:
                System.out.println("+--------------+--------------------+-------------+");
                System.out.printf("|%-48s | \n", "2. Thêm tài khoản ATM");
                System.out.println("+--------------+--------------------+-------------+");
                
                customer_id = library.enterCustomerId();
                library.addAccount("saving");
                
                System.out.println("+--------------+--------------------+-------------+");
                break;
            case 3:
                System.out.println("+--------------+--------------------+-------------+");
                System.out.printf("|%-48s | \n", "3. Thêm tài khoản tín dụng");
                System.out.println("+--------------+--------------------+-------------+");
                
                customer_id = library.enterCustomerId();
                library.addAccount("loan");
                
                System.out.println("+--------------+--------------------+-------------+");
                break;
            case 4:
                System.out.println("+--------------+--------------------+-------------+");
                System.out.printf("|%-48s | \n", "4. Rút tiền");
                System.out.println("+--------------+--------------------+-------------+");
                
                library.withdraw();
                
                System.out.println("+--------------+--------------------+-------------+");
                break;
            case 5:
                System.out.println("+--------------+--------------------+-------------+");
                System.out.printf("|%-48s | \n", "5. Lịch sử giao dịch");
                System.out.println("+--------------+--------------------+-------------+");
                
                library.showTransactionHistory();
                
                System.out.println("+--------------+--------------------+-------------+");
                break;
            default:
                throw new AssertionError();
        }
    }
    
    // Phương thức dùng để chạy chương trình trong hàm main
    public void runProgram () {
        int menu_number = 5, choose;
        
        library.initSampleData();
        
        showMenu();
        
        do {            
          choose = chooseMenu(menu_number);
            handleChoose(choose);
        } while (choose != 0);
    }
    
}
