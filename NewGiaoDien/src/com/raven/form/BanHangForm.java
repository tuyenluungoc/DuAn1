package com.raven.form;

import Utils.DBContext;
import Model.ChatLieu;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.MauSac;
import Model.NhanVien;
import Model.PhanLoai;
import Model.SanPhamChiTiet;
import Model.SanPham_SPCT;
import Model.ThuongHieu;
import Model.XuatXu;
import Service.ChatLieuService;
import Service.GiamGiaService;
import Service.HDCTService;
import Service.HoaDonService;
import Service.KhachHangService;
import Service.MauSacService;
import Service.NguoiDungService;
import Service.NhanVienService;
import Service.PhanLoaiService;
import Service.SPCTService;
import Service.SanPhamService;
import Service.SanPham_SPCT_Service;
import Service.TaiKhoanService;
import Service.ThuongHieuService;
import Service.XuatXuService;
import Utils.Auth;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BanHangForm extends javax.swing.JPanel {
//Khai báo list ở đây

    List<String> lsttentt = new ArrayList<>();
    private ArrayList<NhanVien> listnv = new ArrayList<>();
    List<SanPham_SPCT> listsp_ct = new ArrayList<>();
    List<SanPham_SPCT> listsp_ct1 = new ArrayList<>();
    private Connection con = DBContext.getConnection();
    private DefaultTableModel dtm = new DefaultTableModel();
    private TaiKhoanService servicetk = new TaiKhoanService();
    private ThuongHieuService serviceth = new ThuongHieuService();
    private ChatLieuService servicecl = new ChatLieuService();
    private MauSacService servicems = new MauSacService();
    private PhanLoaiService servicepl = new PhanLoaiService();
    private XuatXuService servicexx = new XuatXuService();
    private NhanVienService servicenv = new NhanVienService();
    private SanPhamService spservice = new SanPhamService();
    private SPCTService spctservice = new SPCTService();
    private SanPham_SPCT_Service sp_ctservice = new SanPham_SPCT_Service();
    private GiamGiaService ggservice = new GiamGiaService();
    private HoaDonService servicehd = new HoaDonService();
    private HDCTService hdctservice = new HDCTService();
    private ArrayList<ThuongHieu> lthuonghieu = new ArrayList<>();
    private ArrayList<ChatLieu> lchatlieu = new ArrayList<>();
    private ArrayList<MauSac> lmausac = new ArrayList<>();
    private ArrayList<PhanLoai> lphanloai = new ArrayList<>();
    private ArrayList<XuatXu> lxuatxu = new ArrayList<>();
    private ArrayList<HoaDonChiTiet> lsthdct = new ArrayList<>();
    private ArrayList<SanPhamChiTiet> lspct = new ArrayList<>();
    private KhachHangService khsv = new KhachHangService();
    private NguoiDungService ndsv = new NguoiDungService();
    String input = null;
    private int i = -1;
    private int index = -1;
    private int pagesp = 1;
    private int pagehd = 1;

    public BanHangForm() {
        initComponents();
        cbohdtrangthai.setVisible(false);
        loadcbokt();
        loadcbocl();
        loadcboms();
        loadcbopl();
        loadcboth();
        loadcboxx();
        LoadDataHDSP();
        LoadDataHD();

    }

    private void TimKiemSP() {
        String masp = txttimma.getText();
        String gia1 = "";
        String gia2 = "";
        if (cbogia.getSelectedItem().equals("Thấp hơn 100k")) {
            gia1 = "0";
            gia2 = "100000";
        } else if (cbogia.getSelectedItem().equals("100k - 200k")) {
            gia1 = "100000";
            gia2 = "200000";
        } else if (cbogia.getSelectedItem().equals("200k - 500k")) {
            gia1 = "200000";
            gia2 = "500000";
        } else if (cbogia.getSelectedItem().equals("Lớn hơn 500k")) {
            gia1 = "500000";
            gia2 = "9999999999999999999999999999999";
        } else {
            gia1 = "0";
            gia2 = "9999999999999999999999999999999";
        }
        String soluong = "";
        String khoiluong = "";
        String kichthuoc = "";
        String phanloai = "";
        String thuonghieu = "";
        String mausac = "";
        String xuatxu = "";
        String chatlieu = "";
        if (((String) cbomausac.getSelectedItem()) == null || (cbomausac.getSelectedIndex() == 0)) {
            mausac = "";
        } else {
            mausac = servicems.findidMS((String) cbomausac.getSelectedItem()).getId();
        }

        if (((String) cbochatlieu.getSelectedItem()) == null || (cbochatlieu.getSelectedIndex() == 0)) {
            chatlieu = "";
        } else {
            chatlieu = servicecl.findidCL((String) cbochatlieu.getSelectedItem()).getId();
        }

        if (((String) cboxuatxu.getSelectedItem()) == null || (cboxuatxu.getSelectedIndex() == 0)) {
            xuatxu = "";
        } else {
            xuatxu = servicexx.findidXX((String) cboxuatxu.getSelectedItem()).getId();
        }

        if (((String) cbothuonghieu.getSelectedItem()) == null || (cbothuonghieu.getSelectedIndex() == 0)) {
            thuonghieu = "";
        } else {
            thuonghieu = serviceth.findidTH((String) cbothuonghieu.getSelectedItem()).getId();
        }

        if (((String) cbophanloai.getSelectedItem()) == null || (cbophanloai.getSelectedIndex() == 0)) {
            phanloai = "";
        } else {
            phanloai = servicepl.findidPL((String) cbophanloai.getSelectedItem()).getId();
        }

        dtm = (DefaultTableModel) tblhddssp.getModel();
        dtm.setRowCount(0);
        for (SanPham_SPCT x : spctservice.TimSPCT(masp, masp, soluong, khoiluong, kichthuoc, phanloai,
                thuonghieu, mausac, xuatxu, chatlieu, gia1, gia2)) {
            dtm.addRow(new Object[]{
                x.getIdspct(), x.getMaSP(), x.getTenSP(),
                x.getGia(), x.getSoLuong(), x.getKhoiLuong(), x.getKichThuoc(), servicepl.findPL(x.getIdPL()).getTen(),
                serviceth.findTH(x.getIdTH()).getTen(), servicems.findMS(x.getIdMS()).getTen(),
                servicexx.findXX(x.getIdXX()).getTen(), servicecl.findCL(x.getIdCL()).getTen()
            });
        }

    }

    private void ThanhToan() {
        i = tblhddshd.getSelectedRow();
        HoaDon hd = new HoaDon();
        hd.setId(tblhddshd.getValueAt(i, 0).toString());
        boolean ThanhToanResult = servicehd.ThanhToan(hd);
        if (ThanhToanResult) {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
        }

    }

    private void TongTien() {
        i = tblhddshd.getSelectedRow();
        boolean tongtienResult = servicehd.TinhTongTien(tblhddshd.getValueAt(i, 0).toString());
        HoaDon hd = servicehd.getAllHoaDon().get(i);
        DecimalFormat df = new DecimalFormat("#########");
        
        txthdtongtien.setText(df.format(hd.getTongtien()));
    }

    private void HuyHD() {
        i = tblhddshd.getSelectedRow();

        HoaDon hd = new HoaDon();
        hd.setId(tblhddshd.getValueAt(i, 0).toString());
        boolean HuyResult = servicehd.huyhd(hd);
        if (HuyResult) {
            JOptionPane.showMessageDialog(this, "Hủy thành công");
        }
    }

    private void suahdct() {
        i = tblhddsgh.getSelectedRow();

        String input = JOptionPane.showInputDialog("Nhập số lượng");
        int soluong = Integer.parseInt(input) - Integer.parseInt(tblhddsgh.getValueAt(i, 3).toString());
        int soluonghdmoi = Integer.parseInt(input);
        int soluongcu = hdctservice.findSoLuong(tblhddsgh.getValueAt(i, 0).toString()).getSoLuong();
        int soluongmoi = soluongcu - soluong;
        Float dongia = Float.parseFloat(tblhddsgh.getValueAt(i, 4).toString());
        Float giamgia = Float.parseFloat(tblhddsgh.getValueAt(i, 5).toString());
        Float thanhtien = (dongia - giamgia) * soluonghdmoi;
        if (soluongmoi >= 0) {
            SanPhamChiTiet spct = new SanPhamChiTiet();
            spct.setSoLuong(soluongmoi);
            spct.setId(hdctservice.findIDSPCT(tblhddsgh.getValueAt(i, 0).toString()).getIdspct());

            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setThanhtien(thanhtien);
            hdct.setSoLuong(Integer.parseInt(input));
            hdct.setId(tblhddsgh.getValueAt(i, 0).toString());
            boolean suahdResult = hdctservice.suahd(hdct);

            boolean suaResult = hdctservice.suasp(spct);
            boolean suatongtien = hdctservice.suathanhtien(hdct);
        } else {
            JOptionPane.showMessageDialog(this, "Không đủ số lượng sản phẩm");
        }

    }

    private void xoagh() {
        i = tblhddsgh.getSelectedRow();
        String idhdct = tblhddsgh.getValueAt(i, 0).toString();
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setId(idhdct);
        int soluongcu = hdctservice.findSoLuong(tblhddsgh.getValueAt(i, 0).toString()).getSoLuong();
        int soluongmoi = soluongcu + Integer.parseInt(tblhddsgh.getValueAt(i, 3).toString());
        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setSoLuong(soluongmoi);
        spct.setId(hdctservice.findIDSPCT(tblhddsgh.getValueAt(i, 0).toString()).getIdspct());
        boolean xoaResult = hdctservice.xoa(hdct);
        boolean suaResult = spctservice.SuaSoLuong(spct);
        if (xoaResult) {
            JOptionPane.showMessageDialog(this, "Xóa thành công.");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa không thành công.");
        }
    }

    private void xoahet() {

        int ihd = tblhddshd.getSelectedRow();
        String idhd = tblhddshd.getValueAt(ihd, 0).toString();

        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setIdhd(idhd);
        i = tblhddsgh.getSelectedRow();
        int soluongcu = hdctservice.findSoLuong(tblhddsgh.getValueAt(i, 0).toString()).getSoLuong();
        int soluongmoi = soluongcu + Integer.parseInt(tblhddsgh.getValueAt(i, 3).toString());
        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setSoLuong(soluongmoi);
        spct.setId(hdctservice.findIDSPCT(tblhddsgh.getValueAt(i, 0).toString()).getIdspct());
        boolean xoaResult = hdctservice.XoaHet(idhd);
        boolean suaResult = spctservice.SuaSoLuong(spct);
        if (xoaResult) {
            JOptionPane.showMessageDialog(this, "Xóa thành công.");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa không thành công.");
        }
    }

    private boolean CheckThemGH() {
        i = tblhddssp.getSelectedRow();
        index = tblhddshd.getSelectedRow();
        if (cbohdtrangthai.getSelectedItem().equals("Chưa thanh toán")) {
            for (HoaDonChiTiet x : hdctservice.getAllHoaDon123(tblhddshd.getValueAt(index, 0).toString())) {
                if (x.getIdspct().equals(tblhddssp.getValueAt(i, 0))) {
                    JOptionPane.showMessageDialog(this, "Đã có sản phẩm trong giỏ hàng");
                    return false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm vào giỏ hàng");
        }
        return true;
    }

    private void themgh() {
        index = tblhddshd.getSelectedRow();
        i = tblhddssp.getSelectedRow();

        int soluong = (int) tblhddssp.getValueAt(i, 4);
        String idspct = tblhddssp.getValueAt(i, 0).toString();
        String idhd = tblhddshd.getValueAt(index, 0).toString();
        input = JOptionPane.showInputDialog("Nhập số lượng");
        int soluongspct = soluong - Integer.parseInt(input);
        float magg = 0;
        float dongia = (float) tblhddssp.getValueAt(i, 3);

        if (spctservice.findSPCT(idspct).getMaGG() == null) {
            magg = 0;
        } else {
            magg = ggservice.findgiatri(spctservice.findSPCT(idspct).getMaGG()).getGiaTri();
        }
        float thanhtien = (dongia - magg) * Integer.parseInt(input);

        HoaDonChiTiet dd = new HoaDonChiTiet();
        dd.setIdhd(idhd);
        dd.setIdspct(idspct);
        dd.setSoLuong(Integer.parseInt(input));
        dd.setDongia(dongia);
        dd.setThanhtien(thanhtien);

        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setSoLuong(soluongspct);
        spct.setId(idspct);
        if (Integer.parseInt(input) > 0) {
            if (soluongspct >= 0) {
                boolean themResult = hdctservice.them(dd);
                boolean suaResult = hdctservice.suasp(spct);
                if (themResult) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công.");

                } else {
                    JOptionPane.showMessageDialog(this, "Thêm không thành công");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không đủ số lượng sản phẩm");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
        }

    }

    private String taoNgay() {
        // Lấy ngày hiện tại
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void TaoHD() {
        String ngaytao = taoNgay();
        HoaDon hd = new HoaDon();
        hd.setManv(ndsv.findByTDN(Auth.user.getTenDangNhap()).getID());
        hd.setNgaytao(ngaytao);
        hd.setMakh(khsv.findBySDT(txtsdtkh.getText()).getMaKH());

        String alpha = "QWERTYUIOPASDFGHJKLZXCVBNM0987654321";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;
        for (int j = 0; j < length; j++) {
            int ind = random.nextInt(alpha.length());
            char randomchar = alpha.charAt(ind);
            sb.append(randomchar);
        }
        hd.setMaHD(sb.toString());

        boolean taoResult = servicehd.them(hd);

    }

    public void LoadDataHD() {
        dtm = (DefaultTableModel) tblhddshd.getModel();
        dtm.setRowCount(0);
        for (HoaDon hd : servicehd.getAllHDCTT()) {
            dtm.addRow(new Object[]{
                hd.getId(), hd.getMaHD(), khsv.findByMa(hd.getMakh()).getHoTen(), hd.getNgaytao(), hd.getTrangThai() == 0 ? "Chưa thanh toán" : "Đã thanh toán", ndsv.findByID(hd.getManv()).getHoTen()
            });
        }
    }

    public void LoadDataHDSP() {
        dtm = (DefaultTableModel) tblhddssp.getModel();
        dtm.setRowCount(0);
        for (SanPhamChiTiet sp : servicehd.getAllSP()) {
            dtm.addRow(new Object[]{
                sp.getId(), servicehd.findIDSP(sp.getIdsp()).getMaSP(), servicehd.findIDSP(sp.getIdsp()).getTenSP(), sp.getGia(),
                sp.getSoLuong(), sp.getKhoiLuong(), sp.getKichThuoc(), servicepl.findPL(sp.getIdpl()).getTen(),
                serviceth.findTH(sp.getIdth()).getTen(), servicems.findMS(sp.getIdms()).getTen(),
                servicexx.findXX(sp.getIdxx()).getTen(), servicecl.findCL(sp.getIdcl()).getTen()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblhddshd = new javax.swing.JTable();
        cbohdtrangthai = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblhddsgh = new javax.swing.JTable();
        btnsuagh = new javax.swing.JButton();
        btnhdxoa = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cbohdthanhtoan = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        txthdkhachdua = new javax.swing.JTextField();
        txthdma = new javax.swing.JLabel();
        txthdtongtien = new javax.swing.JTextField();
        txthdtienthua = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnhdhuy = new javax.swing.JButton();
        btnhdthanhtoan = new javax.swing.JButton();
        btnhdtaohoadon = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        txtsdtkh = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txttenkh = new javax.swing.JTextField();
        btnthemkh = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblhddssp = new javax.swing.JTable();
        txttimma = new javax.swing.JTextField();
        cbogia = new javax.swing.JComboBox<>();
        cbokichthuoc = new javax.swing.JComboBox<>();
        cbophanloai = new javax.swing.JComboBox<>();
        cbothuonghieu = new javax.swing.JComboBox<>();
        cbomausac = new javax.swing.JComboBox<>();
        cboxuatxu = new javax.swing.JComboBox<>();
        cbochatlieu = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        tblhddshd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblhddshd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã HD", "Tên KH", "Ngày tạo", "Trạng thái", "Tên nhân viên"
            }
        ));
        tblhddshd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhddshdMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblhddshd);

        cbohdtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán", "Đã hủy", "Tất cả hóa đơn" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(cbohdtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 687, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbohdtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Túi sách 4Men");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblhddsgh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblhddsgh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền", "Khối lượng", "Kích thước", "Phân loại", "Thương hiệu", "Màu sắc", "Xuất xứ", "Chất liệu"
            }
        ));
        tblhddsgh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhddsghMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblhddsgh);
        if (tblhddsgh.getColumnModel().getColumnCount() > 0) {
            tblhddsgh.getColumnModel().getColumn(0).setMinWidth(0);
            tblhddsgh.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblhddsgh.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btnsuagh.setText("Sửa");
        btnsuagh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaghActionPerformed(evt);
            }
        });

        btnhdxoa.setText("Xóa");
        btnhdxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhdxoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnhdxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnsuagh)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsuagh)
                    .addComponent(btnhdxoa)))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Bán hàng"));

        jLabel23.setText("Tổng tiền hàng :");

        jLabel25.setText("HT thanh toán :");

        jLabel26.setText("Tiền thừa :");

        cbohdthanhtoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản" }));
        cbohdthanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbohdthanhtoanActionPerformed(evt);
            }
        });

        jLabel30.setText("Tiền khách đưa :");

        txthdkhachdua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthdkhachduaActionPerformed(evt);
            }
        });

        txthdma.setText("jLabel1");

        txthdtongtien.setEditable(false);
        txthdtongtien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txthdtongtienMouseClicked(evt);
            }
        });
        txthdtongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthdtongtienActionPerformed(evt);
            }
        });
        txthdtongtien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txthdtongtienKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txthdtongtienKeyReleased(evt);
            }
        });

        txthdtienthua.setEditable(false);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txthdma, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txthdtienthua, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txthdkhachdua, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbohdthanhtoan, javax.swing.GroupLayout.Alignment.LEADING, 0, 157, Short.MAX_VALUE)
                            .addComponent(txthdtongtien)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txthdma, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txthdtongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbohdthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txthdkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txthdtienthua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnhdhuy.setText("Hủy");
        btnhdhuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhdhuyActionPerformed(evt);
            }
        });

        btnhdthanhtoan.setText("Thanh toán");
        btnhdthanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhdthanhtoanActionPerformed(evt);
            }
        });

        btnhdtaohoadon.setText("Tạo hóa đơn");
        btnhdtaohoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhdtaohoadonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnhdtaohoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnhdthanhtoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnhdhuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnhdtaohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnhdthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnhdhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel39.setText("SDT:");

        txtsdtkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsdtkhActionPerformed(evt);
            }
        });

        jLabel40.setText("Tên khách hàng:");

        btnthemkh.setText("Thêm khách hàng");
        btnthemkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemkhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsdtkh)
                    .addComponent(txttenkh)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnthemkh, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel39)
                .addGap(8, 8, 8)
                .addComponent(txtsdtkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnthemkh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tblhddssp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblhddssp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "Giá", "Số lượng", "Khối lượng", "Kích thước", "Phân loại", "Thương Hiệu", "Màu sắc", "Xuất xứ", "Chất liệu"
            }
        ));
        tblhddssp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhddsspMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblhddssp);

        txttimma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimmaKeyReleased(evt);
            }
        });

        cbogia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Thấp hơn 100k", "100k - 200k", "200k - 500k", "Lớn hơn 500k" }));
        cbogia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbogiaActionPerformed(evt);
            }
        });

        cbokichthuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbophanloai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbophanloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbophanloaiActionPerformed(evt);
            }
        });

        cbothuonghieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbothuonghieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbothuonghieuActionPerformed(evt);
            }
        });

        cbomausac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbomausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomausacActionPerformed(evt);
            }
        });

        cboxuatxu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboxuatxu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxuatxuActionPerformed(evt);
            }
        });

        cbochatlieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbochatlieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbochatlieuActionPerformed(evt);
            }
        });

        jLabel3.setText("Tìm Mã/Tên SP:");

        jLabel4.setText("Tìm theo giá");

        jLabel5.setText("Tìm thương hiệu");

        jLabel6.setText("Tìm kích thước");

        jLabel7.setText("Tìm phân loại");

        jLabel8.setText("Tìm màu sắc");

        jLabel10.setText("Tìm xuất xứ");

        jLabel11.setText("Tìm chất liệu");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txttimma, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbogia, 0, 130, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbokichthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cbophanloai, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cbothuonghieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5))
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(cbomausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(cboxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbochatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbochatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbomausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbothuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbophanloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbokichthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbogia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttimma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(427, 427, 427)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblhddshdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhddshdMouseClicked
        i = tblhddshd.getSelectedRow();
        LoadGH(hdctservice.getAllGH(tblhddshd.getValueAt(i, 0).toString()));
        TongTien();
    }//GEN-LAST:event_tblhddshdMouseClicked

    private void btnhdxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhdxoaActionPerformed
        xoagh();
        LoadGH(lsthdct);
        LoadDataHDSP();
    }//GEN-LAST:event_btnhdxoaActionPerformed

    private void btnsuaghActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaghActionPerformed
        suahdct();
        LoadDataHDSP();
        LoadGH(hdctservice.getAllGH(tblhddshd.getValueAt(tblhddshd.getSelectedRow(), 0).toString()));
    }//GEN-LAST:event_btnsuaghActionPerformed

    private void cbohdthanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbohdthanhtoanActionPerformed
        if (cbohdthanhtoan.getSelectedItem().equals("Chuyển khoản")) {
            txthdkhachdua.setText(txthdtongtien.getText());
            txthdtienthua.setText("0");
        } else {
            txthdkhachdua.setText("0");
        }
    }//GEN-LAST:event_cbohdthanhtoanActionPerformed

    private void btnhdhuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhdhuyActionPerformed
        i = tblhddshd.getSelectedRow();
        if (tblhddshd.getValueAt(i, 4).equals("Chưa thanh toán")) {
            HuyHD();
            LoadDataHD();
        } else {
            JOptionPane.showMessageDialog(this, "Không thể thực hiện");
        }
    }//GEN-LAST:event_btnhdhuyActionPerformed

    private void btnhdthanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhdthanhtoanActionPerformed
        i = tblhddshd.getSelectedRow();
        

        if (!txthdtongtien.getText().equals("0.0")) {
            if (txthdkhachdua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tiền khách đưa");
            return;
        }
        try {
            Double.valueOf(txthdkhachdua.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa phải là số");
            return;
        }
        Double a = Double.valueOf(txthdtongtien.getText());
        Double b = Double.valueOf(txthdkhachdua.getText());
        txthdtienthua.setText(String.valueOf(b - a));
            if (b < a) {
                JOptionPane.showMessageDialog(this, "Chưa đủ tiền");
            } else {
                ThanhToan();
                LoadDataHD();
                txthdkhachdua.setText("");
                txthdtongtien.setText("");
                txthdtienthua.setText("");  
                lsthdct.clear();
                LoadGH(lsthdct);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm trong giỏ hàng");
        }

    }//GEN-LAST:event_btnhdthanhtoanActionPerformed

    private void btnhdtaohoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhdtaohoadonActionPerformed
        TaoHD();
        LoadDataHD();
    }//GEN-LAST:event_btnhdtaohoadonActionPerformed

    private void tblhddsspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhddsspMouseClicked
        if (CheckThemGH()) {
            themgh();
            LoadGH(hdctservice.getAllGH(tblhddshd.getValueAt(tblhddshd.getSelectedRow(), 0).toString()));
            LoadDataHDSP();
        }
    }//GEN-LAST:event_tblhddsspMouseClicked

    private void txttimmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimmaKeyReleased
        TimKiemSP();
    }//GEN-LAST:event_txttimmaKeyReleased

    private void cbogiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbogiaActionPerformed
        TimKiemSP();
    }//GEN-LAST:event_cbogiaActionPerformed

    private void cbophanloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbophanloaiActionPerformed
        TimKiemSP();
    }//GEN-LAST:event_cbophanloaiActionPerformed

    private void cbothuonghieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbothuonghieuActionPerformed
        TimKiemSP();
    }//GEN-LAST:event_cbothuonghieuActionPerformed

    private void cbomausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomausacActionPerformed
        TimKiemSP();
    }//GEN-LAST:event_cbomausacActionPerformed

    private void cboxuatxuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxuatxuActionPerformed
        TimKiemSP();
    }//GEN-LAST:event_cboxuatxuActionPerformed

    private void cbochatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbochatlieuActionPerformed
        TimKiemSP();
    }//GEN-LAST:event_cbochatlieuActionPerformed

    private void txtsdtkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsdtkhActionPerformed
        // TODO add your handling code here:

        if (khsv.findBySDT(txtsdtkh.getText()) == null) {
            txttenkh.setText("Vãng lai");
        } else {
            txttenkh.setText(khsv.findBySDT(txtsdtkh.getText()).getHoTen());
        }
    }//GEN-LAST:event_txtsdtkhActionPerformed

    private void txthdtongtienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthdtongtienMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txthdtongtienMouseClicked

    private void txthdtongtienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthdtongtienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthdtongtienKeyPressed

    private void txthdtongtienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthdtongtienKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txthdtongtienKeyReleased

    private void txthdtongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthdtongtienActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txthdtongtienActionPerformed

    private void txthdkhachduaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthdkhachduaActionPerformed
        // TODO add your handling code here:
        String tongtien = txthdtongtien.getText();
        String tienkhachdua = txthdkhachdua.getText();
        double tienthua = Float.parseFloat(tienkhachdua) - Float.parseFloat(tongtien);
        txthdtienthua.setText(String.valueOf(tienthua));
    }//GEN-LAST:event_txthdkhachduaActionPerformed

    private void tblhddsghMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhddsghMouseClicked
        // TODO add your handling code here:
        

    }//GEN-LAST:event_tblhddsghMouseClicked

    private void btnthemkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemkhActionPerformed
        // TODO add your handling code here:
        KhachHangJfram a = new KhachHangJfram();
        a.setVisible(true);
    }//GEN-LAST:event_btnthemkhActionPerformed

    public ArrayList<String> loadcbocl() {
        cbochatlieu.removeAllItems();
        cbochatlieu.addItem("Tất cả");
        ArrayList<String> lstcl = new ArrayList<String>();
        String sql = "Select TenCL From ChatLieu";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lstcl.add(rs.getString("TenCL"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String item : lstcl) {
            cbochatlieu.addItem(item.toString());
        }

        return lstcl;
    }

    public ArrayList<String> loadcboth() {
        cbothuonghieu.removeAllItems();
        cbothuonghieu.addItem("Tất cả");
        ArrayList<String> lstth = new ArrayList<String>();
        String sql = "Select TenTH From ThuongHieu";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lstth.add(rs.getString("TenTH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String item : lstth) {
            cbothuonghieu.addItem(item.toString());
        }

        return lstth;

    }

    public void loadcboms() {
        cbomausac.removeAllItems();
        cbomausac.addItem("Tất cả");
        ArrayList<String> lstms = new ArrayList<String>();
        String sql = "Select TenMS From MauSac";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lstms.add(rs.getString("TenMS"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String item : lstms) {
            cbomausac.addItem(item);
        }

    }

    public ArrayList<String> loadcbopl() {
        cbophanloai.removeAllItems();
        cbophanloai.addItem("Tất cả");
        ArrayList<String> lstpl = new ArrayList<String>();
        String sql = "Select TenPL From PhanLoai";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lstpl.add(rs.getString("TenPL"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String item : lstpl) {
            cbophanloai.addItem(item.toString());
        }

        return lstpl;
    }

    public ArrayList<String> loadcboxx() {
        cboxuatxu.removeAllItems();
        cboxuatxu.addItem("Tất cả");
        ArrayList<String> lstxx = new ArrayList<String>();
        String sql = "Select TenXX From XuatXu";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lstxx.add(rs.getString("TenXX"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String item : lstxx) {
            cboxuatxu.addItem(item.toString());
        }
        return lstxx;
    }

    public ArrayList<String> loadcbokt() {
        cbokichthuoc.removeAllItems();
        cbokichthuoc.addItem("Tất cả");
        ArrayList<String> lstxx = new ArrayList<String>();
        String sql = "Select KichThuoc From SanPhamChiTiet";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lstxx.add(rs.getString("KichThuoc"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String item : lstxx) {
            cbokichthuoc.addItem(item.toString());
        }
        return lstxx;
    }

    public void LoadGH(List<HoaDonChiTiet> l) {
        dtm = (DefaultTableModel) tblhddsgh.getModel();
        dtm.setRowCount(0);
        for (HoaDonChiTiet x : l) {
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
        TongTien();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhdhuy;
    private javax.swing.JButton btnhdtaohoadon;
    private javax.swing.JButton btnhdthanhtoan;
    private javax.swing.JButton btnhdxoa;
    private javax.swing.JButton btnsuagh;
    private javax.swing.JButton btnthemkh;
    private javax.swing.JComboBox<String> cbochatlieu;
    private javax.swing.JComboBox<String> cbogia;
    private javax.swing.JComboBox<String> cbohdthanhtoan;
    private javax.swing.JComboBox<String> cbohdtrangthai;
    private javax.swing.JComboBox<String> cbokichthuoc;
    private javax.swing.JComboBox<String> cbomausac;
    private javax.swing.JComboBox<String> cbophanloai;
    private javax.swing.JComboBox<String> cbothuonghieu;
    private javax.swing.JComboBox<String> cboxuatxu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblhddsgh;
    private javax.swing.JTable tblhddshd;
    private javax.swing.JTable tblhddssp;
    private javax.swing.JTextField txthdkhachdua;
    private javax.swing.JLabel txthdma;
    private javax.swing.JTextField txthdtienthua;
    private javax.swing.JTextField txthdtongtien;
    private javax.swing.JTextField txtsdtkh;
    private javax.swing.JTextField txttenkh;
    private javax.swing.JTextField txttimma;
    // End of variables declaration//GEN-END:variables
}
