package com.raven.form;

import Model.SanPham;
import Service.SanPhamService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SanPhamDaAn extends javax.swing.JFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private SanPhamService service = new SanPhamService();
    int i = 0;

    public SanPhamDaAn() {
        initComponents();
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        fillTBLSanPham(service.gettAllAn());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnpresp = new javax.swing.JButton();
        txttimsp = new javax.swing.JTextField();
        btnnextsp = new javax.swing.JButton();
        tbmtimsp = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblidsp = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnchitietsp = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanpham = new javax.swing.JTable();
        tbnxoasp = new javax.swing.JButton();
        txtmasp = new javax.swing.JTextField();
        txttensp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnchitietsp1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnpresp.setText("<");

        txttimsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnnextsp.setText(">");

        tbmtimsp.setText("Tìm kiếm");
        tbmtimsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbmtimspActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("ID:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Quản lý sản phẩm");

        lblidsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblidsp.setText("S0281931");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mã sản phẩm:");

        btnchitietsp.setText("Chi tiết");
        btnchitietsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchitietspActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tên sản phẩm:");

        tblsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Sản phẩm", "Mã sản phẩm", "Tên sản phẩm"
            }
        ));
        tblsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsanphamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblsanpham);

        tbnxoasp.setText("Xóa");
        tbnxoasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnxoaspActionPerformed(evt);
            }
        });

        txtmasp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txttensp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Tìm kiếm:");

        btnchitietsp1.setText("Hiển thị lại");
        btnchitietsp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchitietsp1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnpresp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnnextsp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(275, 275, 275))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txttimsp)
                                                .addGap(55, 55, 55)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnchitietsp1)
                                                .addGap(36, 36, 36)
                                                .addComponent(tbnxoasp))
                                            .addComponent(tbmtimsp)
                                            .addComponent(btnchitietsp)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel12))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblidsp, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmasp, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(291, 291, 291))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnchitietsp, btnchitietsp1, tbmtimsp, tbnxoasp});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txttimsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbmtimsp)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(lblidsp))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtmasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38)
                .addComponent(btnchitietsp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnpresp)
                    .addComponent(btnnextsp)
                    .addComponent(tbnxoasp)
                    .addComponent(btnchitietsp1))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnchitietspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchitietspActionPerformed
        // TODO add your handling code here:
        i = tblsanpham.getSelectedRow();
        viewSPCT spct = new viewSPCT(service.findSPByID(tblsanpham.getValueAt(i, 0).toString()));
        System.out.println(lblidsp.getText());
        spct.setVisible(true);
    }//GEN-LAST:event_btnchitietspActionPerformed

    private void tblsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsanphamMouseClicked
        // TODO add your handling code here:
        i = tblsanpham.getSelectedRow();
        ShowSP(i);
    }//GEN-LAST:event_tblsanphamMouseClicked

    private void tbnxoaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnxoaspActionPerformed
        // TODO add your handling code here:
        i = tblsanpham.getSelectedRow();
        if (service.DeleteSP(tblsanpham.getValueAt(i, 0).toString()) > 0) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            fillTBLSanPham(service.gettAllAn());
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_tbnxoaspActionPerformed

    private void btnchitietsp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchitietsp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnchitietsp1ActionPerformed

    private void tbmtimspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbmtimspActionPerformed
        // TODO add your handling code here:
        String t = txttimsp.getText();
        if (t.isEmpty()) {
            fillTBLSanPham(service.gettAllAn());
        } else {
            fillTBLSanPham(service.findAnByMa(t));
        }
    }//GEN-LAST:event_tbmtimspActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPhamDaAn().setVisible(true);
            }
        });
    }

    private void fillTBLSanPham(List<SanPham> l) {
        dtm = (DefaultTableModel) tblsanpham.getModel();
        dtm.setRowCount(0);
        for (SanPham x : l) {
            dtm.addRow(new Object[]{
                x.getId(), x.getMaSP(), x.getTenSP()
            });
        }
    }

    private void ShowSP(int i) {
        lblidsp.setText(tblsanpham.getValueAt(i, 0).toString());
        txtmasp.setText(tblsanpham.getValueAt(i, 1).toString());
        txttensp.setText(tblsanpham.getValueAt(i, 2).toString());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnchitietsp;
    private javax.swing.JButton btnchitietsp1;
    private javax.swing.JButton btnnextsp;
    private javax.swing.JButton btnpresp;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblidsp;
    private javax.swing.JTable tblsanpham;
    private javax.swing.JButton tbmtimsp;
    private javax.swing.JButton tbnxoasp;
    private javax.swing.JTextField txtmasp;
    private javax.swing.JTextField txttensp;
    private javax.swing.JTextField txttimsp;
    // End of variables declaration//GEN-END:variables
}
