
package pages;

import javax.swing.JOptionPane;
import model.Account;
import model.LoansAccount;
import nganhangso_v4.DigitalBank;


public class WithdrawFrame extends javax.swing.JFrame {
    
    private String customerId;
    private String accountNumber;
    private MainFrame mainFrame;
    private int selectedRow;
    DigitalBank activeBank;
    
    
    public WithdrawFrame(MainFrame parent, String customerId, String accountNumber, int selectedRow) {
        this.mainFrame = parent;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.selectedRow = selectedRow;
        initComponents();
        activeBank = new DigitalBank();
    }
    
    private void handleOK () {
        try {
            double amount = Double.parseDouble(txtAmount.getText());
            double fee = 0;
            Account acc = activeBank.getAccountByAccNumber(customerId, accountNumber);
            
            if (amount < 50000 || amount % 10000 !=0) {
                JOptionPane.showMessageDialog(this, "Số tiền rút phải lớn hơn hoặc bằng 50,000 VNĐ.\nSố tiền rút phải là bội số của 10,000 VNĐ.");
            }
        
            if (acc instanceof LoansAccount loansAccount) {
                fee = loansAccount.isPremium() ? amount * LoansAccount.LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : amount * LoansAccount.LOAN_ACCOUNT_WITHDRAW_FEE;
            }
            
            String message = String.format("Rút %.2f VNĐ từ tài khoản?\n Phí giao dịch %.2f VNĐ", amount, fee);
            
            int option = JOptionPane.showConfirmDialog(this,message, "Xác nhận rút tiền", JOptionPane.YES_NO_CANCEL_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                double totalAmount = amount + fee;
                mainFrame.withdraw(accountNumber, totalAmount, selectedRow);
                dispose();
            }
            else if (option == JOptionPane.NO_OPTION)
                txtAmount.setText("");
            else
                dispose();
        
        } catch (NumberFormatException e) {
        }
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Withdraw Menu");
        setLocation(new java.awt.Point(600, 300));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Withdraw");

        txtAmount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnOK)
                        .addGap(36, 36, 36)
                        .addComponent(btnCancel)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK)
                    .addComponent(btnCancel))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        handleOK();
    }//GEN-LAST:event_btnOKActionPerformed


//    public static void main(String args[]) {
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(WithdrawFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(WithdrawFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(WithdrawFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(WithdrawFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new WithdrawFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtAmount;
    // End of variables declaration//GEN-END:variables

}
