
package view;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.Customer;
import nganhangso_v4.DigitalBank;


public class MainFrame extends javax.swing.JFrame {
    
    private String customerId;
    private DigitalBank active_bank = new DigitalBank();
    private DefaultTableModel model;


    public MainFrame(String customerID) {
        this.customerId = customerID;
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/bank.png"));
        setIconImage(icon.getImage());
        
        initComponents();
        
        displayAccounts();
    }
    
    // Phương thức tải danh sách tài khoản lên JTable
    private void displayAccounts () {
        // Lấy danh sách tài khoản của khách hàng
        Customer cus = active_bank.getCustomerById(customerId);      
        List<Account> accounts = cus.getAccount();
        
        model = (DefaultTableModel) tbAccount.getModel();
        model.setRowCount(0);
        
        for (Account acc : accounts) {
            model.addRow(new Object[] {
                acc.getFirstName(), acc.getLastName(),
                acc.getAccountNumber(), acc.getBalance()
            });
        }
    }
    
    // Phương thức thêm tài khoản
    public void addAccount (String firstName, String lastName, String password, double balance, String acc_type) {
        active_bank.addNewAccount(customerId, firstName, lastName, password, balance, acc_type);
        displayAccounts();
    }
    
    // Phương thức xoá tài khoản được chọn
    private void removeSelectedAccount () {
        int selectedRow = tbAccount.getSelectedRow();
        String accountNumber = (String) tbAccount.getValueAt(selectedRow, 2);
        int option = JOptionPane.showConfirmDialog(
                null, 
                "Bạn có chắc muốn xoá tài khoản số: " + accountNumber + "?",
                "Xác nhận xoá", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            if (selectedRow >= 0) {
            
                try {
                    active_bank.removeAccount(customerId, accountNumber);
                }       
                catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                }
                
                model.removeRow(selectedRow);
            }
            else
                JOptionPane.showMessageDialog(null, "Xin hãy chọn tài khoản để xoá");
        }
                
        
    }
    
    //Phương thức cập nhật số dư của tài khoản
    public void updateAccountBalance (String accountNumber, int row, double newBalance) {
        active_bank.updateAccountBlance(customerId, accountNumber, newBalance);
        tbAccount.setValueAt(newBalance, row, 3);
    }
    
    public void withdraw (String accountNumber, double amount, int row) {
        if (active_bank.withdraw(customerId, accountNumber, amount) == true) {
            JOptionPane.showMessageDialog(null, "Rút tiền thành công");
            updateAccountBalance(accountNumber, row, amount);
        }
        else
            JOptionPane.showMessageDialog(null, "Rút tiền thất bại");
    }

//    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        AddAcc = new javax.swing.JButton();
        btnRemoveAcc = new javax.swing.JButton();
        btnDeposit = new javax.swing.JButton();
        btnWithdraw = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAccount = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bank Application");
        setLocation(new java.awt.Point(500, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("BANK");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 51, 255));
        jLabel2.setText("version 4");

        AddAcc.setText("Add account");
        AddAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAccActionPerformed(evt);
            }
        });

        btnRemoveAcc.setText("Remove account");
        btnRemoveAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAccActionPerformed(evt);
            }
        });

        btnDeposit.setText("Deposit");
        btnDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositActionPerformed(evt);
            }
        });

        btnWithdraw.setText("Withdraw");
        btnWithdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWithdrawActionPerformed(evt);
            }
        });

        tbAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Họ", "Tên", "Số tài khoản", "Số dư"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbAccount.setColumnSelectionAllowed(true);
        tbAccount.setSelectionBackground(new java.awt.Color(0, 204, 204));
        tbAccount.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbAccount.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbAccount.setShowGrid(true);
        tbAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAccountMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbAccount);
        tbAccount.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(AddAcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoveAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDeposit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnWithdraw, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddAcc)
                            .addComponent(btnDeposit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnWithdraw)
                            .addComponent(btnRemoveAcc)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAccActionPerformed
        AddAccountFrame addAccFrame = new AddAccountFrame(this);
        addAccFrame.setVisible(true);
    }//GEN-LAST:event_AddAccActionPerformed

    private void btnRemoveAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAccActionPerformed
        removeSelectedAccount();
    }//GEN-LAST:event_btnRemoveAccActionPerformed

    private void btnDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositActionPerformed
        int selectedRow = tbAccount.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản để gửi tiền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
        }
        
        String accountNumber = tbAccount.getValueAt(selectedRow, 2).toString();
        double currentBalance = Double.parseDouble(tbAccount.getValueAt(selectedRow, 3).toString());
        
        DepositFrame depositFrame = new DepositFrame (this, accountNumber, currentBalance, selectedRow);
        depositFrame.setVisible(true);
    }//GEN-LAST:event_btnDepositActionPerformed

    private void btnWithdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWithdrawActionPerformed
        int selectedRow = tbAccount.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản để gửi tiền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
        }
        
        String accountNumber = tbAccount.getValueAt(selectedRow, 2).toString();
        
        WithdrawFrame withdrawFrame = new WithdrawFrame(this, customerId, accountNumber, selectedRow);
        withdrawFrame.setVisible(true);
    }//GEN-LAST:event_btnWithdrawActionPerformed

    private void tbAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAccountMouseClicked
        if (evt.getClickCount() == 2) { 
            int selectedRow = tbAccount.getSelectedRow();
            String accountNumber = tbAccount.getValueAt(selectedRow, 2).toString();
            
            Account acc = active_bank.getAccountByAccNumber(customerId, accountNumber);
            
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản để hiển thị thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            
            AccountDetailFrame accountDetailFrame = new AccountDetailFrame(acc);
            accountDetailFrame.setVisible(true);
        }
        
    }//GEN-LAST:event_tbAccountMouseClicked

 
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
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainFrame(String customerID).setVisible(true);
//            }
//        });
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAcc;
    private javax.swing.JButton btnDeposit;
    private javax.swing.JButton btnRemoveAcc;
    private javax.swing.JButton btnWithdraw;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbAccount;
    // End of variables declaration//GEN-END:variables

}
