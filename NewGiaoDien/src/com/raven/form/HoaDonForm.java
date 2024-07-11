package com.raven.form;

import Utils.DBContext;
import Service.HoaDonService;
import javax.swing.table.DefaultTableModel;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Service.ChatLieuService;
import Service.GiamGiaService;
import Service.HDCTService;
import Service.KhachHangService;
import Service.MauSacService;
import Service.NguoiDungService;
import Service.NhanVienService;
import Service.PhanLoaiService;
import Service.SPCTService;
import Service.SanPhamService;
import Service.TaiKhoanService;
import Service.ThuongHieuService;
import Service.XuatXuService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonForm extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private HoaDonService servicehd = new HoaDonService();
    private Connection con = DBContext.getConnection();
    private GiamGiaService ggservice = new GiamGiaService();
    private SPCTService spctservice = new SPCTService();
    private HDCTService hdctservice = new HDCTService();
    private TaiKhoanService servicetk = new TaiKhoanService();
    private ThuongHieuService serviceth = new ThuongHieuService();
    private ChatLieuService servicecl = new ChatLieuService();
    private MauSacService servicems = new MauSacService();
    private PhanLoaiService servicepl = new PhanLoaiService();
    private XuatXuService servicexx = new XuatXuService();
    private NhanVienService servicenv = new NhanVienService();
    private SanPhamService spservice = new SanPhamService();
    private KhachHangService khservice = new KhachHangService();
    private NguoiDungService ndservice = new NguoiDungService();
    private int i = 1;
    private int pagesp = 1;
    private int pagehd = 1;
    private ArrayList<HoaDonChiTiet> lsthdct = new ArrayList<>();

    public HoaDonForm() {
        initComponents();
        LoadDataHD(servicehd.getAllHD());

    }

    public void LoadDataHD(List<HoaDon> l) {
        dtm = (DefaultTableModel) tblhd.getModel();
        dtm.setRowCount(0);
        for (HoaDon hd : l) {
            dtm.addRow(new Object[]{
                hd.getId(), hd.getMaHD(),hd.getManv(), hd.getMakh(), hd.getNgaytao(),hd.getTongtien(), hd.getTrangThai()==0?"Chưa thanh toán":"Đã thanh toán"
            });
        }
    }

    private void loadGioHang() {
        i = tblhd.getSelectedRow();

        String sql = "Select ID,IDSPCT,SoLuong,DonGia,ThanhTien FROM HoaDonChiTiet Where IDHD = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tblhd.getValueAt(i, 0).toString());
            ResultSet rs = st.executeQuery();
            lsthdct.clear();
            while (rs.next()) {

                String id = rs.getString(1);
                String idspct = rs.getString(2);
                int soluong = rs.getInt(3);
                Float dongia = rs.getFloat(4);
                float thanhien = rs.getFloat(5);

                HoaDonChiTiet d = new HoaDonChiTiet(id, id, idspct, soluong, dongia, thanhien);
                lsthdct.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        dtm = (DefaultTableModel) tblsp.getModel();
        dtm.setRowCount(0);
        for (HoaDonChiTiet x : lsthdct) {
            Float magg = ggservice.findgiatri(spctservice.findSPCT(x.getIdspct()).getMaGG()).getGiaTri();
            if (magg == null) {
                magg = Float.parseFloat("0");
            }
            dtm.addRow(new Object[]{
                x.getId(), hdctservice.findIDSP(x.getIdspct()).getMasp(), hdctservice.findIDSP(x.getIdspct()).getTensp(), x.getSoLuong(),
                x.getDongia(), magg, x.getThanhtien(), spctservice.findSPCT(x.getIdspct()).getKhoiLuong(),
                spctservice.findSPCT(x.getIdspct()).getKichThuoc(), servicepl.findPL(spctservice.findSPCT(x.getIdspct()).getIdpl()).getTen(),
                serviceth.findTH(spctservice.findSPCT(x.getIdspct()).getIdth()).getTen(), servicems.findMS(spctservice.findSPCT(x.getIdspct()).getIdms()).getTen(),
                servicexx.findXX(spctservice.findSPCT(x.getIdspct()).getIdxx()).getTen(), servicecl.findCL(spctservice.findSPCT(x.getIdspct()).getIdcl()).getTen(),});
        }

    }

    

    

    
    
    public void Show(int i){
        
        lblmanv.setText(tblhd.getValueAt(i, 2).toString());
        lbltennv.setText(ndservice.findByID(tblhd.getValueAt(i, 2).toString()).getHoTen());
        lbltenkh.setText(khservice.findByMa(tblhd.getValueAt(i, 3).toString()).getHoTen());
        lbldiachi.setText(khservice.findByMa(tblhd.getValueAt(i, 3).toString()).getDiaChi());
        lblsodt.setText(khservice.findByMa(tblhd.getValueAt(i, 3).toString()).getSDT());
        lbltongtien.setText(tblhd.getValueAt(i, 5).toString());
        lblngaytao.setText(tblhd.getValueAt(i, 4).toString());
        if(tblhd.getValueAt(i, 6).toString().equalsIgnoreCase("Chưa thanh toán")){
            lbltrangthai.setText("Chưa thanh toán");
        }else{
            lbltrangthai.setText("Đã thanh toán");
        }
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel17 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblhd = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        cbohdtrangthai = new javax.swing.JComboBox<>();
        btnLastHD = new javax.swing.JButton();
        btnNextHD = new javax.swing.JButton();
        txtPageHD = new javax.swing.JTextField();
        btnPrevHD = new javax.swing.JButton();
        btnFirstHD = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblsp = new javax.swing.JTable();
        btnLastHD1 = new javax.swing.JButton();
        btnNextHD1 = new javax.swing.JButton();
        txtPageHD1 = new javax.swing.JTextField();
        btnPrevHD1 = new javax.swing.JButton();
        btnFirstHD1 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        lblmanv = new javax.swing.JLabel();
        lbltennv = new javax.swing.JLabel();
        lbltenkh = new javax.swing.JLabel();
        lblsodt = new javax.swing.JLabel();
        lbldiachi = new javax.swing.JLabel();
        lblngaytao = new javax.swing.JLabel();
        lbltrangthai = new javax.swing.JLabel();
        lbltongtien = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        tblhd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã HD", "Mã nhân viên", "Mã khách hàng", "Ngày thanh toán", "Tổng tiền", "Trạng thái"
            }
        ));
        tblhd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhdMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblhd);

        jLabel13.setText("Trạng thái");

        cbohdtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán", "Tất cả hóa đơn" }));
        cbohdtrangthai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbohdtrangthaiMouseClicked(evt);
            }
        });
        cbohdtrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbohdtrangthaiActionPerformed(evt);
            }
        });

        btnLastHD.setText(">|");
        btnLastHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastHDActionPerformed(evt);
            }
        });

        btnNextHD.setText(">>");
        btnNextHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextHDActionPerformed(evt);
            }
        });

        txtPageHD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPageHD.setText("1");

        btnPrevHD.setText("<<");

        btnFirstHD.setText("|<");
        btnFirstHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jScrollPane8)
                        .addContainerGap())
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbohdtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                        .addComponent(btnFirstHD, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrevHD, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPageHD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNextHD, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLastHD, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbohdtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLastHD)
                    .addComponent(btnNextHD)
                    .addComponent(txtPageHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevHD)
                    .addComponent(btnFirstHD))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        tblsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền", "Khối lượng", "Kích thước", "Phân loại", "Thương hiệu", "Màu sắc", "Xuất xứ", "Chất liệu"
            }
        ));
        jScrollPane4.setViewportView(tblsp);
        if (tblsp.getColumnModel().getColumnCount() > 0) {
            tblsp.getColumnModel().getColumn(0).setMinWidth(0);
            tblsp.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblsp.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btnLastHD1.setText(">|");
        btnLastHD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastHD1ActionPerformed(evt);
            }
        });

        btnNextHD1.setText(">>");
        btnNextHD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextHD1ActionPerformed(evt);
            }
        });

        txtPageHD1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPageHD1.setText("1");

        btnPrevHD1.setText("<<");

        btnFirstHD1.setText("|<");
        btnFirstHD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstHD1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFirstHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrevHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPageHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNextHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLastHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(229, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLastHD1)
                    .addComponent(btnNextHD1)
                    .addComponent(txtPageHD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevHD1)
                    .addComponent(btnFirstHD1))
                .addContainerGap())
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE)))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel44.setText("Mã nhân viên");

        jLabel45.setText("Tên NV");

        jLabel46.setText("Tên KH");

        jLabel47.setText("Số ĐT");

        jLabel48.setText("Địa chỉ");

        jLabel49.setText("Tổng tiền hàng");

        jLabel50.setText("Ngày tạo");

        jLabel51.setText("Trạng thái");

        lblmanv.setText("-");

        lbltennv.setText("-");

        lbltenkh.setText("-");

        lblsodt.setText("-");

        lbldiachi.setText("-");

        lblngaytao.setText("-");

        lbltrangthai.setText("-");

        lbltongtien.setText("0");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltennv, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblsodt, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbldiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(lblmanv))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(lbltennv))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(lbltenkh))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(lblsodt))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(lbldiachi))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(lbltongtien))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(lblngaytao))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(lbltrangthai))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Quản lý hóa đơn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(452, 452, 452))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbohdtrangthaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbohdtrangthaiMouseClicked

    }//GEN-LAST:event_cbohdtrangthaiMouseClicked

    private void cbohdtrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbohdtrangthaiActionPerformed
        if(cbohdtrangthai.getSelectedIndex()==2){
            LoadDataHD(servicehd.getAllHD());
        }
        if(cbohdtrangthai.getSelectedIndex()==0){
            LoadDataHD(servicehd.getAllHDCTT());
        }
        if(cbohdtrangthai.getSelectedIndex()==1){
            LoadDataHD(servicehd.getAllHDDTT());
        }
        
    }//GEN-LAST:event_cbohdtrangthaiActionPerformed

    private void btnLastHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastHDActionPerformed
//        if (cbohdtrangthai.getSelectedItem().toString().equals("Đã thanh toán")) {
//            try {
//                int hoaDonListSize = servicehd.getAllHoaDon1(1000).size();
//                if (hoaDonListSize > 0) {
//                    int rowsPerPage = 5;
//                    int totalPages = (hoaDonListSize + rowsPerPage - 1) / rowsPerPage;
//                    // Đặt page để chỉ đến trang cuối cùng
//                    pagehd = totalPages;
//                    this.setPageHD1();
//                } else {
//                    // Xử lý trường hợp danh sách trống
//                    System.out.println("Danh sách hoá đơn trống.");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                // Xử lý ngoại lệ nếu có
//            }
//        } else if (cbohdtrangthai.getSelectedItem().toString().equals("Chưa thanh toán")) {
//            try {
//                int hoaDonListSize = servicehd.getAllHoaDon(1000).size();
//                if (hoaDonListSize > 0) {
//                    int rowsPerPage = 5;
//                    int totalPages = (hoaDonListSize + rowsPerPage - 1) / rowsPerPage;
//                    // Đặt page để chỉ đến trang cuối cùng
//                    pagehd = totalPages;
//                    this.setPageHD();
//                } else {
//                    // Xử lý trường hợp danh sách trống
//                    System.out.println("Danh sách hoá đơn trống.");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                // Xử lý ngoại lệ nếu có
//            }
//        } else if (cbohdtrangthai.getSelectedItem().toString().equals("Đã hủy")) {
//            try {
//                int hoaDonListSize = servicehd.getAllHoaDon2(1000).size();
//                if (hoaDonListSize > 0) {
//                    int rowsPerPage = 5;
//                    int totalPages = (hoaDonListSize + rowsPerPage - 1) / rowsPerPage;
//                    // Đặt page để chỉ đến trang cuối cùng
//                    pagehd = totalPages;
//                    this.setPageHD2();
//                } else {
//                    // Xử lý trường hợp danh sách trống
//                    System.out.println("Danh sách hoá đơn trống.");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                // Xử lý ngoại lệ nếu có
//            }
//        } else {
//            try {
//                int hoaDonListSize = servicehd.getAllHoaDon3(1000).size();
//                if (hoaDonListSize > 0) {
//                    int rowsPerPage = 5;
//                    int totalPages = (hoaDonListSize + rowsPerPage - 1) / rowsPerPage;
//                    // Đặt page để chỉ đến trang cuối cùng
//                    pagehd = totalPages;
//                    this.setPageHD3();
//                } else {
//                    // Xử lý trường hợp danh sách trống
//                    System.out.println("Danh sách hoá đơn trống.");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                // Xử lý ngoại lệ nếu có
//            }
//        }
    }//GEN-LAST:event_btnLastHDActionPerformed

    private void btnNextHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextHDActionPerformed

//        if (cbohdtrangthai.getSelectedItem().toString().equals("Đã thanh toán")) {
//            pagehd++;
//            this.setPageHD1();
//
//        } else if (cbohdtrangthai.getSelectedItem().toString().equals("Chưa thanh toán")) {
//            pagehd++;
//            this.setPageHD();
//        } else if (cbohdtrangthai.getSelectedItem().toString().equals("Đã hủy")) {
//            pagehd++;
//            this.setPageHD2();
//
//        } else {
//            pagehd++;
//            this.setPageHD3();
//        }
    }//GEN-LAST:event_btnNextHDActionPerformed

    private void btnFirstHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstHDActionPerformed
//        if (cbohdtrangthai.getSelectedItem().toString().equals("Đã thanh toán")) {
//            pagehd = 1;
//            this.setPageHD1();
//        } else if (cbohdtrangthai.getSelectedItem().toString().equals("Chưa thanh toán")) {
//            pagehd = 1;
//            this.setPageHD();
//        } else if (cbohdtrangthai.getSelectedItem().toString().equals("Đã hủy")) {
//            pagehd = 1;
//            this.setPageHD2();
//        } else {
//            pagehd = 1;
//            this.setPageHD3();
//        }
    }//GEN-LAST:event_btnFirstHDActionPerformed

    private void btnLastHD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastHD1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastHD1ActionPerformed

    private void btnNextHD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextHD1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextHD1ActionPerformed

    private void btnFirstHD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstHD1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstHD1ActionPerformed

    private void tblhdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhdMouseClicked
        loadGioHang();
        i = tblhd.getSelectedRow();
        Show(i);
        
    }//GEN-LAST:event_tblhdMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirstHD;
    private javax.swing.JButton btnFirstHD1;
    private javax.swing.JButton btnLastHD;
    private javax.swing.JButton btnLastHD1;
    private javax.swing.JButton btnNextHD;
    private javax.swing.JButton btnNextHD1;
    private javax.swing.JButton btnPrevHD;
    private javax.swing.JButton btnPrevHD1;
    private javax.swing.JComboBox<String> cbohdtrangthai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lbldiachi;
    private javax.swing.JLabel lblmanv;
    private javax.swing.JLabel lblngaytao;
    private javax.swing.JLabel lblsodt;
    private javax.swing.JLabel lbltenkh;
    private javax.swing.JLabel lbltennv;
    private javax.swing.JLabel lbltongtien;
    private javax.swing.JLabel lbltrangthai;
    private javax.swing.JTable tblhd;
    private javax.swing.JTable tblsp;
    private javax.swing.JTextField txtPageHD;
    private javax.swing.JTextField txtPageHD1;
    // End of variables declaration//GEN-END:variables
}
