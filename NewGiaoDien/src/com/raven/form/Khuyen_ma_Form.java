/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import Model.KhuyenMai;
import Model.PhanLoai;
import Model.SanPhamChiTiet;
import Service.ChatLieuService;
import Service.KhuyenMaiService;
import Service.MauSacService;
import Service.PhanLoaiService;
import Service.SPCTService;
import Service.SanPhamService;
import Service.ThuongHieuService;
import Service.XuatXuService;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tuyen PC
 */
public class Khuyen_ma_Form extends javax.swing.JPanel {

    DefaultTableModel mol = new DefaultTableModel();
    List<SanPhamChiTiet> list = new ArrayList<>();
    SPCTService service = new SPCTService();
    PhanLoaiService plservice = new PhanLoaiService();
    MauSacService msSer = new MauSacService();
    ThuongHieuService thSer = new ThuongHieuService();
    ChatLieuService clSer = new ChatLieuService();
    XuatXuService xxSer = new XuatXuService();
    SanPhamService spSer = new SanPhamService();
    List<KhuyenMai> listKM = new ArrayList<>();
    KhuyenMaiService kmSer = new KhuyenMaiService();

    public Khuyen_ma_Form() {
        initComponents();
        for (KhuyenMai x : kmSer.getHetHan()) {
            service.checkMaGG(x.getMaGG());
        }
        DefaultComboBoxModel cbb = (DefaultComboBoxModel) cboLoaiApDung.getModel();
        cbb.removeAllElements();

        cboLoaiApDung.removeAllItems();
        cboLoaiApDung.addItem("Tất cả Loại Sản Phẩm");
        List<PhanLoai> listPL = plservice.gettAll();
        for (PhanLoai pl : listPL) {
            cbb.addElement(pl.getTen());
        }
        cbokmhinhthuc.removeAllItems();
        cbokmhinhthuc.addItem("Tất Cả ");
        cbokmhinhthuc.addItem("ĐÃ có giảm giá");
        cbokmhinhthuc.addItem("Chưa có giảm giá");

        XetHetHan();
        cboKhuyenMai.removeAllItems();
        cboKhuyenMai.addItem("Đã áp dụng ");
        cboKhuyenMai.addItem("Chưa áp dụng ");
        cboKhuyenMai.addItem("Tất cả Mã Giảm giá ");
        this.fillTable(service.gettAll());
        this.fillTableKhuyenMai(kmSer.gettAll());
//        this.checkValidate();

    }

    private void XetHetHan() {
        // Lấy ngày hiện tại
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        boolean xethethan = kmSer.XetHetHan(dateFormat.format(date));

    }

    public void Timkiem() {
        String checkmagg = "";
        String tePl = "";

        if (cboLoaiApDung.getSelectedIndex() == 0) {
            tePl = "";

        } else {
            tePl = (String) cboLoaiApDung.getSelectedItem();

        }
        System.out.println(tePl + "1");
//        System.out.println((String)cboLoaiApDung.getSelectedItem());  
        if (((String) cbokmhinhthuc.getSelectedItem()) == null || (cbokmhinhthuc.getSelectedIndex() == 0)) {
            checkmagg = "";
        } else if (cbokmhinhthuc.getSelectedIndex() == 1) {
            checkmagg = " and MaGG Is not null";
        } else {
            checkmagg = " and MaGG is null";
        }
        mol = (DefaultTableModel) tblkmdssp.getModel();
        mol.setRowCount(0);
        for (SanPhamChiTiet sct : service.TimKiem(tePl, checkmagg)) {

            Object[] toData = new Object[]{sct.getId(),
                spSer.findByID(sct.getIdsp()).getTenSP(),
                clSer.findCL(sct.getIdcl()),
                plservice.SElectbyIDPL(sct.getIdpl()).getTen(),
                msSer.findMS(sct.getIdms()).getTen(),
                thSer.findTH(sct.getIdth()).getTen(),
                xxSer.findXX(sct.getIdxx()).getTen(),
                sct.getKichThuoc(), sct.getKhoiLuong(), sct.getSoLuong(), sct.getGia(),
                sct.getMaGG()};
            mol.addRow(toData);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel32 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        rdokmdangdienra = new javax.swing.JRadioButton();
        jPanel33 = new javax.swing.JPanel();
        btnkmluu = new javax.swing.JButton();
        btnkmmoi = new javax.swing.JButton();
        btnkmsua = new javax.swing.JButton();
        btnApDung = new javax.swing.JButton();
        txtkmmucgg = new javax.swing.JTextField();
        txtkmtenct = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        txtkmbatdau = new com.toedter.calendar.JDateChooser();
        txtkmketthuc = new com.toedter.calendar.JDateChooser();
        rdokmdaketthuc = new javax.swing.JRadioButton();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblkmdssp = new javax.swing.JTable();
        jLabel108 = new javax.swing.JLabel();
        cboLoaiApDung = new javax.swing.JComboBox<>();
        jLabel116 = new javax.swing.JLabel();
        cbokmhinhthuc = new javax.swing.JComboBox<>();
        btnxemgg = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblkmdskm = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        cboKhuyenMai = new javax.swing.JComboBox<>();
        btnXemMaDaAD = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder("Khuyến mại"));

        jLabel109.setText("Tên chương trình");

        jLabel110.setText("Mức giảm giá");

        jLabel111.setText("Thời gian bắt đầu");

        jLabel112.setText("Thời gian kết thúc");

        jLabel113.setText("Trạng thái :");

        buttonGroup1.add(rdokmdangdienra);
        rdokmdangdienra.setSelected(true);
        rdokmdangdienra.setText("Đang diễn ra");

        jPanel33.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        btnkmluu.setText("Lưu");
        btnkmluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkmluuActionPerformed(evt);
            }
        });

        btnkmmoi.setText("Mới");
        btnkmmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkmmoiActionPerformed(evt);
            }
        });

        btnkmsua.setText("Sửa");
        btnkmsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkmsuaActionPerformed(evt);
            }
        });

        btnApDung.setText("Áp Dụng");
        btnApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApDungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnkmmoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(btnkmluu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnkmsua, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnkmluu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnkmmoi, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnkmsua, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );

        txtkmmucgg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtkmmucggMouseClicked(evt);
            }
        });

        txtkmtenct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtkmtenctMouseClicked(evt);
            }
        });
        txtkmtenct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtkmtenctKeyTyped(evt);
            }
        });

        jLabel1.setText("Ghi Chú");

        txtkmbatdau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtkmbatdauMouseClicked(evt);
            }
        });

        txtkmketthuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtkmketthucMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdokmdaketthuc);
        rdokmdaketthuc.setText("Đã kết thúc");
        rdokmdaketthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdokmdaketthucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                            .addComponent(rdokmdangdienra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(65, 65, 65)))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtkmmucgg)
                    .addComponent(txtkmtenct, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtkmketthuc, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(txtkmbatdau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdokmdaketthuc)))
                .addContainerGap())
            .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(txtkmtenct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(txtkmmucgg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel111)
                    .addComponent(txtkmbatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112)
                    .addComponent(txtkmketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(25, 25, 25)
                .addComponent(jLabel113)
                .addGap(25, 25, 25)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdokmdangdienra)
                    .addComponent(rdokmdaketthuc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tblkmdssp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên ", "Chất Liệu", "Phân Loại", "Màu Sắc", "THương Hiệu", "Xuất Xứ", "Kích thước", "Khối lượng", "Số Lượng", "Giá", "MaGG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblkmdssp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkmdsspMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tblkmdssp);

        jLabel108.setText("Tìm Loại Sản Phẩm");

        cboLoaiApDung.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiApDung.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiApDungItemStateChanged(evt);
            }
        });
        cboLoaiApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiApDungActionPerformed(evt);
            }
        });

        jLabel116.setText("Hình thức");

        cbokmhinhthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbokmhinhthuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbokmhinhthucItemStateChanged(evt);
            }
        });
        cbokmhinhthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbokmhinhthucActionPerformed(evt);
            }
        });

        btnxemgg.setText("Xem giảm giá");
        btnxemgg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxemggActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLoaiApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(cbokmhinhthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnxemgg)
                .addGap(251, 251, 251))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jScrollPane15)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(cboLoaiApDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel116)
                    .addComponent(cbokmhinhthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxemgg))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khuyến mãi"));

        tblkmdskm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khuyến Mại", "Tên ", "Ngày Bắt Đầu", "Ngày Hết Hạn", "Giá Trị", "Ghi Chú", "Trạng Thái"
            }
        ));
        tblkmdskm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkmdskmMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblkmdskm);

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });

        cboKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKhuyenMai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhuyenMaiItemStateChanged(evt);
            }
        });

        btnXemMaDaAD.setText("Xem Sản Phẩm đã áp dụng");
        btnXemMaDaAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemMaDaADActionPerformed(evt);
            }
        });

        jLabel3.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane14)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXemMaDaAD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(26, 26, 26)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemMaDaAD)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Quản lý khuyến mại");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(355, 355, 355))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdokmdaketthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdokmdaketthucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdokmdaketthucActionPerformed

    private void btnkmluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkmluuActionPerformed
        // TODO add your handling code here:
        if (checkTrong()) {
            if (kmSer.ThemKhuyenMai(readForm())) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                listKM = kmSer.gettAll();
                fillTableKhuyenMai(listKM);
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm lỗi");
            }
        }


    }//GEN-LAST:event_btnkmluuActionPerformed

    private void btnkmmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkmmoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnkmmoiActionPerformed

    private void btnkmsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkmsuaActionPerformed
        // TODO add your handling code here:
        if (checkTrongT()) {
            if (kmSer.UpdateKM(readFormT())) {
                
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                for (KhuyenMai x : kmSer.getHetHan()) {
                    service.checkMaGG(x.getMaGG());
                }
                XetHetHan();

                fillTableKhuyenMai(kmSer.gettAll());
                fillTable(service.gettAll());

            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
                for (KhuyenMai x : kmSer.getHetHan()) {
                    service.checkMaGG(x.getMaGG());
                }
                XetHetHan();

                fillTableKhuyenMai(kmSer.gettAll());
                fillTable(service.gettAll());
            }
        }

    }//GEN-LAST:event_btnkmsuaActionPerformed

    private void btnApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungActionPerformed
        // TODO add your handling code here:
        int index = tblkmdskm.getSelectedRow();
        int index2 = tblkmdssp.getSelectedRow();
        String xgetMagg = (String) tblkmdskm.getValueAt(index, 0);
        String idSP = (String) tblkmdssp.getValueAt(index2, 0);

        SanPhamChiTiet spct = new SanPhamChiTiet();

        spct.setMaGG(xgetMagg);
        spct.setId(idSP);

        if (tblkmdskm.getValueAt(index, 6).toString().equals("Đang diễn ra")) {
            if (service.ApDungMaGGtoSPCT(spct)) {
                fillTable(service.gettAll());
                JOptionPane.showMessageDialog(this, "Áp dụng mã thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Áp dụng thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chương trình đã kết thúc");
        }
    }//GEN-LAST:event_btnApDungActionPerformed

    private void btnxemggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxemggActionPerformed
        // TODO add your handling code here:
        int i = tblkmdssp.getSelectedRow();
        String magg = (String) tblkmdssp.getValueAt(i, 11);

        fillTableKhuyenMai(kmSer.findBySPCT(magg));
    }//GEN-LAST:event_btnxemggActionPerformed

    private void cbokmhinhthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbokmhinhthucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbokmhinhthucActionPerformed

    private void cbokmhinhthucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbokmhinhthucItemStateChanged
        // TODO add your handling code here:

        Timkiem();
    }//GEN-LAST:event_cbokmhinhthucItemStateChanged

    private void cboLoaiApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiApDungActionPerformed
        // TODO add your handling code here:

        //
        //        PhanLoai pl = (PhanLoai) cboLoaiApDung.getSelectedItem();
        //        pl.getId();
        //        for (SanPhamChiTiet sct : list) {
        //            if (pl.getId()==sct.getIdpl()) {
        //               Object[] toData = new Object[]{sct.getId(),sct.getIdsp(),sct.getIdcl(),sct.getIdpl(),sct.getIdms(),sct.getIdth(),sct.getIdxx(),sct.getKichThuoc(),sct.getKhoiLuong(),sct.getSoLuong(),sct.getGia(),sct.getTrangThai()};
        //            mol.addRow(toData);
        //            }
        //
        //        }
    }//GEN-LAST:event_cboLoaiApDungActionPerformed

    private void cboLoaiApDungItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiApDungItemStateChanged
        // TODO add your handling code here:
        //        String id = (String) cboLoaiApDung.getSelectedItem();
        //        fillTable(service.gettAllByTenPL(id));
        //        System.out.println(id);

        //        int index=-1;
        Timkiem();
    }//GEN-LAST:event_cboLoaiApDungItemStateChanged

    private void tblkmdsspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkmdsspMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblkmdsspMouseClicked

    private void tblkmdskmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkmdskmMouseClicked
        // TODO add your handling code here:
        int index = tblkmdskm.getSelectedRow();

        showBangKM(index);
    }//GEN-LAST:event_tblkmdskmMouseClicked

    private void cboKhuyenMaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhuyenMaiItemStateChanged
        // TODO add your handling code here:
        int i = cboKhuyenMai.getSelectedIndex();
        if (i == 2) {
            fillTableKhuyenMai(kmSer.gettAll());
            //        } else {
            //            fillTable(service.gettAllChuaGG());
        }
    }//GEN-LAST:event_cboKhuyenMaiItemStateChanged

    private void btnXemMaDaADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemMaDaADActionPerformed
        // TODO add your handling code here:
        int index = tblkmdskm.getSelectedRow();
        String magg = (String) tblkmdskm.getValueAt(index, 0);

        fillTable(service.findByMaGG(magg));
    }//GEN-LAST:event_btnXemMaDaADActionPerformed

    private void txtkmtenctKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkmtenctKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtkmtenctKeyTyped

    private void txtkmtenctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtkmtenctMouseClicked
        // TODO add your handling code here:
        txtkmtenct.setBackground(Color.white);

    }//GEN-LAST:event_txtkmtenctMouseClicked

    private void txtkmmucggMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtkmmucggMouseClicked
        // TODO add your handling code here:
        txtkmmucgg.setBackground(Color.white);

    }//GEN-LAST:event_txtkmmucggMouseClicked

    private void txtkmbatdauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtkmbatdauMouseClicked
        // TODO add your handling code here:
        txtkmbatdau.setBackground(Color.white);
    }//GEN-LAST:event_txtkmbatdauMouseClicked

    private void txtkmketthucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtkmketthucMouseClicked
        // TODO add your handling code here:

        txtkmketthuc.setBackground(Color.white);
    }//GEN-LAST:event_txtkmketthucMouseClicked

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_txtTimKiemKeyTyped
    public void timKiem() {
        mol = (DefaultTableModel) tblkmdskm.getModel();
        mol.setRowCount(0);
        String keyword = txtTimKiem.getText();
        List<KhuyenMai> kmList = kmSer.TimKiem(keyword);
        for (KhuyenMai km : kmList) {
            Object[] toData = new Object[]{km.getMaGG(), km.getTenGG(), km.getNgayBatDau(), km.getNgayHetHan(), km.getGiaTri(), km.getGhiChu(), km.getTrangThai() ? "Đã kết thúc" : "Đang diễn ra"};

            mol.addRow(toData);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApDung;
    private javax.swing.JButton btnXemMaDaAD;
    private javax.swing.JButton btnkmluu;
    private javax.swing.JButton btnkmmoi;
    private javax.swing.JButton btnkmsua;
    private javax.swing.JButton btnxemgg;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboKhuyenMai;
    private javax.swing.JComboBox<String> cboLoaiApDung;
    private javax.swing.JComboBox<String> cbokmhinhthuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JRadioButton rdokmdaketthuc;
    private javax.swing.JRadioButton rdokmdangdienra;
    private javax.swing.JTable tblkmdskm;
    private javax.swing.JTable tblkmdssp;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtTimKiem;
    private com.toedter.calendar.JDateChooser txtkmbatdau;
    private com.toedter.calendar.JDateChooser txtkmketthuc;
    private javax.swing.JTextField txtkmmucgg;
    private javax.swing.JTextField txtkmtenct;
    // End of variables declaration//GEN-END:variables
 private void fillTable(List<SanPhamChiTiet> list) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        mol = (DefaultTableModel) tblkmdssp.getModel();
        mol.setRowCount(0);
        for (SanPhamChiTiet sct : list) {
            Object[] toData = new Object[]{sct.getId(),
                spSer.findByID(sct.getIdsp()).getTenSP(),
                clSer.findCL(sct.getIdcl()).getTen(),
                plservice.SElectbyIDPL(sct.getIdpl()).getTen(),
                msSer.findMS(sct.getIdms()).getTen(),
                thSer.findTH(sct.getIdth()).getTen(),
                xxSer.findXX(sct.getIdxx()).getTen(),
                sct.getKichThuoc(), sct.getKhoiLuong(), sct.getSoLuong(), sct.getGia(),
                sct.getMaGG()};
            mol.addRow(toData);
        }

    }

    private void fillTableKhuyenMai(List<KhuyenMai> listKM) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        mol = (DefaultTableModel) tblkmdskm.getModel();
        mol.setRowCount(0);
        for (KhuyenMai km : listKM) {
            Object[] toData2 = new Object[]{km.getMaGG(), km.getTenGG(), km.getNgayBatDau(), km.getNgayHetHan(), km.getGiaTri(), km.getGhiChu(), km.getTrangThai() ? "Đã kết thúc" : "Đang diễn ra"};
            mol.addRow(toData2);
        }
    }

    void showBangKM(int index) {

        KhuyenMai km = kmSer.gettAll().get(index);
        txtkmtenct.setText(km.getTenGG());
        txtkmmucgg.setText(km.getGiaTri().toString());
        txtkmbatdau.setDate(km.getNgayBatDau());
        txtkmketthuc.setDate(km.getNgayHetHan());
        txtGhiChu.setText(km.getGhiChu());
        if (!km.getTrangThai()) {
            rdokmdangdienra.setSelected(true);

        } else {
            rdokmdaketthuc.setSelected(true);
        }
    }

    public boolean checkTrong() {
        Boolean x = true;
        if (txtkmtenct.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống");
            txtkmtenct.setBackground(Color.red);
            x = false;
        }
        if (txtkmmucgg.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá trị không được để trống");
            txtkmmucgg.setBackground(Color.red);
            x = false;
        } else {
            try {
                Double.valueOf(txtkmmucgg.getText());
                x = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mức giảm giá phải là số");
                x = false;
            }
        }
        if (Float.parseFloat(txtkmmucgg.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá trị phải lớn hơn 0");
            txtkmmucgg.setBackground(Color.red);
            x = false;
        }

        if (txtkmbatdau.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ko được để trống ngày");
            txtkmbatdau.setBackground(Color.red);
            x = false;
        }
        if (txtkmketthuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ko được để trống ngày");
            txtkmketthuc.setBackground(Color.red);
            x = false;
        }

        if (txtkmketthuc.getDate().before(txtkmbatdau.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
            txtkmketthuc.setBackground(Color.red);
            x = false;
        }
        for (KhuyenMai a : kmSer.gettAll()) {
            if (a.getTenGG().equals(txtkmtenct.getText()) && a.getGiaTri().equals(Float.valueOf(txtkmmucgg.getText())) && a.getNgayBatDau().equals(txtkmbatdau.getDate()) && a.getNgayHetHan().equals(txtkmketthuc.getDate())) {
                JOptionPane.showMessageDialog(this, "Đã tồn tại khuyến mại như vậy! Vui lòng kiểm tra lại");
                x = false;
            }
        }
        return x;
    }

    public boolean checkTrongT() {
        Boolean x = true;
        if (txtkmtenct.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống");
            txtkmtenct.setBackground(Color.red);
            x = false;
        }
        if (txtkmmucgg.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá trị không được để trống");
            txtkmmucgg.setBackground(Color.red);
            x = false;
        } else {
            try {
                Double.valueOf(txtkmmucgg.getText());
                x = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mức giảm giá phải là số");
                x = false;
            }
        }

        if (txtkmbatdau.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ko được để trống ngày");
            txtkmbatdau.setBackground(Color.red);
            x = false;
        }
        if (txtkmketthuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ko được để trống ngày");
            txtkmketthuc.setBackground(Color.red);
            x = false;
        }
        if (Float.parseFloat(txtkmmucgg.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá trị phải lớn hơn 0");
            txtkmmucgg.setBackground(Color.red);
            x = false;
        }
        if (txtkmketthuc.getDate().before(txtkmbatdau.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
            txtkmketthuc.setBackground(Color.red);
            x = false;
        }
        return x;
    }

    public void clear() {
        txtkmtenct.setText("");
        txtkmmucgg.setText("");
        txtkmbatdau.setDate(null);
        txtkmketthuc.setDate(null);
        rdokmdangdienra.setSelected(false);
    }

    public KhuyenMai readForm() {
        KhuyenMai km = new KhuyenMai();
        String alpha = "QWERTYUIOPASDFGHJKLZXCVBNM0987654321";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;
        for (int j = 0; j < length; j++) {
            int ind = random.nextInt(alpha.length());
            char randomchar = alpha.charAt(ind);
            sb.append(randomchar);
        }

        km.setMaGG(sb.toString());
        km.setTenGG(txtkmtenct.getText());
        km.setGiaTri(Float.valueOf(txtkmmucgg.getText()));
        km.setNgayBatDau(txtkmbatdau.getDate());
        km.setNgayHetHan(txtkmketthuc.getDate());
        if (rdokmdangdienra.isSelected()) {
            km.setTrangThai(false);
        } else {
            km.setTrangThai(true);
        }
        km.setGhiChu(txtGhiChu.getText());

        return km;
    }

    public KhuyenMai readFormT() {
        KhuyenMai km = new KhuyenMai();

        km.setMaGG(tblkmdskm.getValueAt(tblkmdskm.getSelectedRow(), 0).toString());
        km.setTenGG(txtkmtenct.getText());
        km.setGiaTri(Float.valueOf(txtkmmucgg.getText()));
        km.setNgayBatDau(txtkmbatdau.getDate());
        km.setNgayHetHan(txtkmketthuc.getDate());
        if (rdokmdangdienra.isSelected()) {
            km.setTrangThai(false);
        } else {
            km.setTrangThai(true);
        }
        km.setGhiChu(txtGhiChu.getText());

        return km;
    }
}
