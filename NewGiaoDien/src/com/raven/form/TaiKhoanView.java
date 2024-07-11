/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.form;

import Utils.DBContext; 
import Model.TaiKhoan;
import Service.TaiKhoanService;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author builo
 */
public class TaiKhoanView extends javax.swing.JFrame {

    private Connection cn = DBContext.getConnection();
    private DefaultTableModel mol = new DefaultTableModel();
    private TaiKhoanService service = new TaiKhoanService();
    private ArrayList<TaiKhoan> listTK = new ArrayList<>();

    /**
     * Creates new form TaiKhoan
     */
    public TaiKhoanView() {
        initComponents();
        fillTableTK(service.getAll());
        txtMa.setVisible(false);
        txtmkm.setVisible(false);
        txtmkxn.setVisible(false);
        lblmkm.setVisible(false);
        lblmkxn.setVisible(false);
        btnxacnhan.setVisible(false);
        txtID.setVisible(false);
    }

    public void fillTableTK(List<TaiKhoan> listTK) {
        mol = (DefaultTableModel) tbltaikhoan.getModel();
        mol.setRowCount(0);
        for (TaiKhoan tk : listTK) {
            mol.addRow(new Object[]{
                tk.getId(), tk.getMaTK(), tk.getTenDangNhap(), tk.getMatKhau(), tk.getVaiTro()
            });
        }
    }

    public void showTK(int i) {
        txtID.setText(tbltaikhoan.getValueAt(i, 0).toString());

        txttendangnhap.setText(tbltaikhoan.getValueAt(i, 2).toString());
        txtmk.setText(tbltaikhoan.getValueAt(i, 3).toString());
        String vaitro = (String) tbltaikhoan.getValueAt(i, 4);
        if (vaitro.equals("Nhân viên")) {
            rdonv.setSelected(true);
        } else {
            rdocch.setSelected(true);
        }
    }

    private void themTK() {
        String alpha = "QWERTYUIOPASDFGHJKLZXCVBNM0987654321";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;
        for (int j = 0; j < length; j++) {
            int ind = random.nextInt(alpha.length());
            char randomchar = alpha.charAt(ind);
            sb.append(randomchar);
        }
        String maKH = sb.toString();
        txtMa.setText(maKH);
        String ten = txttendangnhap.getText();
        String matKhau = txtmk.getText();
        String vaiTro = "";
        if (rdonv.isSelected()) {
            vaiTro = "Nhân viên";
        } else {
            vaiTro = "Chủ cửa hàng";
        }
        TaiKhoan tk = new TaiKhoan();
        tk.setId(txtID.getText());
        tk.setMaTK(txtMa.getText());
        tk.setTenDangNhap(ten);
        tk.setMatKhau(matKhau);
        tk.setVaiTro(vaiTro);
        Integer themResult = service.addTK(tk);
        if (themResult != null) {
            JOptionPane.showMessageDialog(this, "Thêm thành công.");
            fillTableTK(service.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm không thành công.");
        }
    }

    private void suaTK() {
        String vaiTro = "";
        if (rdonv.isSelected()) {
            vaiTro = "Nhân viên";
        } else {
            vaiTro = "Chủ cửa hàng";
        }
        TaiKhoan tk = new TaiKhoan();
        tk.setId(txtID.getText());
        tk.setMaTK(txtMa.getText());
        tk.setTenDangNhap(txttendangnhap.getText());
        tk.setMatKhau(txtmk.getText());
        tk.setVaiTro(vaiTro);
        boolean check = service.suaTK(tk);
        if (check) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            fillTableTK(service.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }

    }

//    private void changePassword() {
//        // Lấy mật khẩu mới từ text field
//        String newPassword = txtmkm.getText();
//        String confirmPassword = txtmkxn.getText();
//        // Xác nhận mật khẩu mới
////        int confirmResult = JOptionPane.showConfirmDialog(this,
////                "Are you sure you want to change the password?",
////                "Confirm Password Change", JOptionPane.YES_NO_OPTION);
//
//        // Thay đổi mật khẩu trong bảng
//        int selectedRow = tbltaikhoan.getSelectedRow();
//
//        if (selectedRow >= 0) {
//            tbltaikhoan.setValueAt(newPassword, selectedRow, 3);
//            if (newPassword.equals(confirmPassword)) {
//                // Xử lý thay đổi mật khẩu ở đây
//                JOptionPane.showMessageDialog(this, "Mật khẩu đã được thay đổi thành công!");
//            } else {
//                JOptionPane.showMessageDialog(this, "Mật khẩu mới và xác nhận mật khẩu không khớp!");
//            }
//
//        } else {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản bạn muốn đổi mật khẩu!");
//        }
//
//    }
//
//    public TaiKhoan MK() {
//        TaiKhoan tk = new TaiKhoan();
//        tk.setId(txtID.getText());
//        tk.setMatKhau(txtmk.getText());
//        return tk;
//    }
    public boolean mk() throws SQLException {
        String sql = "Select * From TaiKhoan where MatKhau = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, txtmk.getText());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không chính xác");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "ok");
        }

        return false;
    }

    public boolean doimk() {
        String name = txttendangnhap.getText().trim();
        String mk = txtmk.getText().trim();
        String mk1 = txtmkm.getText().trim();
        String mk2 = txtmkxn.getText().trim();

        if (name.isEmpty() || mk.isEmpty() || mk1.isEmpty() || mk2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền hết thông tin");
            if (name.isEmpty()) {
                txttendangnhap.requestFocus();
            } else if (mk.isEmpty()) {
                txtmk.requestFocus();
            } else if (mk1.isEmpty()) {
                txtmkm.requestFocus();
            } else {
                txtmkxn.requestFocus();
            }
            return false;
        } else if (!mk1.equals(mk2)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới không trùng khớp");
            return false;
        } else if (mk.equals(mk1)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới khác mật khẩu cũ");
            return false;
        } else {

            return true;
        }
    }

    public boolean Validate() {
        if (txttendangnhap.getText().isEmpty() || txtmk.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jpdoimk = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        lbtMk = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        txttendangnhap = new javax.swing.JTextField();
        txtmk = new javax.swing.JTextField();
        txtmkm = new javax.swing.JTextField();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        jLabel120 = new javax.swing.JLabel();
        rdocch = new javax.swing.JRadioButton();
        rdonv = new javax.swing.JRadioButton();
        txtmkxn = new javax.swing.JTextField();
        lblmkm = new javax.swing.JLabel();
        lblmkxn = new javax.swing.JLabel();
        btnxoa = new javax.swing.JButton();
        btndoimk = new javax.swing.JButton();
        btnxacnhan = new javax.swing.JButton();
        txtMa = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        btnThongTin = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbltaikhoan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpdoimk.setBackground(new java.awt.Color(255, 255, 255));
        jpdoimk.setPreferredSize(new java.awt.Dimension(919, 654));

        jLabel62.setText("Tên đăng nhập");

        lbtMk.setText("Mật khẩu");

        jLabel64.setText("Vai trò");

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        jLabel120.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel120.setText("QUẢN LÝ TÀI KHOẢN");

        buttonGroup1.add(rdocch);
        rdocch.setText("Chủ cửa hàng");

        buttonGroup1.add(rdonv);
        rdonv.setText("Nhân viên");

        lblmkm.setText("Mật khẩu mới");

        lblmkxn.setText("Mật khẩu xác nhận");

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btndoimk.setText("Đổi mật khẩu");
        btndoimk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndoimkActionPerformed(evt);
            }
        });

        btnxacnhan.setText("Xác nhận");
        btnxacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxacnhanActionPerformed(evt);
            }
        });

        btnThongTin.setText("Thông tin nhân viên");
        btnThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpdoimkLayout = new javax.swing.GroupLayout(jpdoimk);
        jpdoimk.setLayout(jpdoimkLayout);
        jpdoimkLayout.setHorizontalGroup(
            jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpdoimkLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(lbtMk)
                    .addComponent(jLabel64)
                    .addComponent(btnthem))
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpdoimkLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttendangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmk, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71))
                    .addGroup(jpdoimkLayout.createSequentialGroup()
                        .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpdoimkLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(rdocch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpdoimkLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnsua)
                                .addGap(34, 34, 34)
                                .addComponent(btnxoa)))
                        .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpdoimkLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(btnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpdoimkLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(rdonv, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)))
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpdoimkLayout.createSequentialGroup()
                        .addComponent(lblmkxn)
                        .addGap(27, 27, 27)
                        .addComponent(txtmkxn, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpdoimkLayout.createSequentialGroup()
                        .addComponent(lblmkm)
                        .addGap(54, 54, 54)
                        .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmkm, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpdoimkLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btndoimk)
                        .addGap(75, 75, 75)
                        .addComponent(btnxacnhan)
                        .addGap(50, 50, 50)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpdoimkLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel120)
                .addGap(328, 328, 328))
        );
        jpdoimkLayout.setVerticalGroup(
            jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpdoimkLayout.createSequentialGroup()
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpdoimkLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel120)
                        .addGap(17, 17, 17)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpdoimkLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(txttendangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmkm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblmkm))
                .addGap(18, 18, 18)
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbtMk)
                    .addComponent(txtmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmkxn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblmkxn))
                .addGap(18, 18, 18)
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(rdocch)
                    .addComponent(rdonv))
                .addGap(33, 33, 33)
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnsua)
                    .addComponent(btnxoa)
                    .addComponent(btndoimk)
                    .addComponent(btnxacnhan)
                    .addComponent(btnThongTin))
                .addContainerGap(346, Short.MAX_VALUE))
        );

        tbltaikhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Ma", "Tên đăng nhập", "Mật khẩu", "Vai trò"
            }
        ));
        tbltaikhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltaikhoanMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tbltaikhoan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpdoimk, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(390, Short.MAX_VALUE)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpdoimk, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbltaikhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltaikhoanMouseClicked
        int i = tbltaikhoan.getSelectedRow();
        showTK(i);
    }//GEN-LAST:event_tbltaikhoanMouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        
        themTK();
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        txtmk.setVisible(false);
        int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?",
                "Xác nhận muốn sửa?", JOptionPane.YES_NO_OPTION);
        if (confirmResult == JOptionPane.YES_OPTION) {
            if (Validate()) {
                suaTK();
            }

        } else {
            return;
        }

    }//GEN-LAST:event_btnsuaActionPerformed

    private void btndoimkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndoimkActionPerformed
        int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn đổi mật không?",
                "Xác nhận muốn đổi mật khẩu?", JOptionPane.YES_NO_OPTION);
        if (confirmResult == JOptionPane.YES_OPTION) {
            txtmkm.setVisible(true);
            txtmkxn.setVisible(true);
            lblmkm.setVisible(true);
            lblmkxn.setVisible(true);
            btnxacnhan.setVisible(true);
            btndoimk.setVisible(false);
        } else {
            return;
        }


    }//GEN-LAST:event_btndoimkActionPerformed
    public void clear() {

    }
    private void btnxacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhanActionPerformed
        try {
            if (doimk() && mk()) {
                String sql = "UPDATE TaiKhoan SET MatKhau = ? Where ID = ? ";

                try {
                    PreparedStatement stm = cn.prepareStatement(sql);
                    stm.setString(1, txtmkm.getText());
                    stm.setString(2, txttendangnhap.getText());
                    stm.setString(3, txtmk.getText());
                    stm.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "333");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "222");
        }

    }//GEN-LAST:event_btnxacnhanActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        int i = tbltaikhoan.getSelectedRow();
        String id = txtID.getText();
        if (service.deleteTK(id) > 0) {
            JOptionPane.showMessageDialog(this, "Xoa thanh cong");
            fillTableTK(service.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "xoa that bai");
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinActionPerformed
        int row = tbltaikhoan.getSelectedRow();
       
        if (row == -1) {
            return;
        }
        String id_TK = (String) this.tbltaikhoan.getValueAt(row, 0);
        System.out.println(id_TK);
        NhanVienView view = new NhanVienView(id_TK);
        view.setVisible(true);

    }//GEN-LAST:event_btnThongTinActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TaiKhoanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaiKhoanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaiKhoanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaiKhoanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaiKhoanView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThongTin;
    private javax.swing.JButton btndoimk;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxacnhan;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JPanel jpdoimk;
    private javax.swing.JPanel jpnhanvien;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblmkm;
    private javax.swing.JLabel lblmkxn;
    private javax.swing.JLabel lbtMk;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdocch;
    private javax.swing.JRadioButton rdonv;
    private javax.swing.JTable tbltaikhoan;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDNV;
    private javax.swing.JTextField txtIDTaiKhoan;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtmk;
    private javax.swing.JTextField txtmkm;
    private javax.swing.JTextField txtmkxn;
    private javax.swing.JTextArea txtnvdiachi;
    private javax.swing.JTextField txtnvemail;
    private com.toedter.calendar.JDateChooser txtnvngaysinh;
    private javax.swing.JTextField txtnvsodt;
    private javax.swing.JTextField txtnvtennv;
    private javax.swing.JTextField txttendangnhap;
    // End of variables declaration//GEN-END:variables
}
