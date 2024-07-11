package Service;

import Utils.DBContext;
import Model.HoaDon;
import Model.SanPham;
import Model.SanPhamChiTiet;
import Model.SanPham_SPCT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonService {

    Connection con = DBContext.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

//    public List<SanPhamChiTiet> getAllSanPham() {
//        List<SanPhamChiTiet> listSP = new ArrayList<>();
//        try {
//            String sql = "  Select ID,IDSP, KhoiLuong,SoLuong,Gia,KichThuoc,IDTH,IDMS,IDPL,IDCL,IDXX From SanPhamChiTiet";
//
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                SanPhamChiTiet sp = new SanPhamChiTiet();
//                sp.setId(rs.getString("ID"));
//                sp.setIdsp(rs.getString("IDSP"));
//                sp.setIdcl(rs.getString("IDCL"));
//                sp.setIdpl(rs.getString("IDPL"));
//                sp.setIdms(rs.getString("IDMS"));
//                sp.setIdth(rs.getString("IDTH"));
//                sp.setIdxx(rs.getString("IDXX"));
//                sp.setKhoiLuong(rs.getFloat("KhoiLuong"));
//                sp.setSoLuong(rs.getInt("SoLuong"));
//                sp.setGia(rs.getFloat("Gia"));
//                sp.setKichThuoc(rs.getString("KichThuoc"));
//                listSP.add(sp);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listSP;
//    }
//    public List<HoaDon> getAllHoaDon() {
//        List<HoaDon> listhd = new ArrayList<>();
//        try {
//            String sql = "Select * from HoaDon where TrangThai = 0";
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDon hd = new HoaDon();
//                hd.setId(rs.getString("IDHD"));
//                hd.setMaHD(rs.getString("MaHD"));
//                hd.setMakh(rs.getString("MaKH"));
//                hd.setManv(rs.getString("MaNV"));
//                hd.setTrangThai(rs.getInt("TrangThai"));
//                hd.setNgaytao(rs.getString("NgayTao"));
//                hd.setTongtien(rs.getFloat("TongTien"));
//
//                listhd.add(hd);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listhd;
//    }
    public List<SanPhamChiTiet> getAllSanPham(int pageSP) {
        List<SanPhamChiTiet> listSP = new ArrayList<>();
        String sql = """
                     Select ID,IDSP, KhoiLuong,SoLuong,Gia,KichThuoc,IDTH,IDMS,IDPL,IDCL,IDXX From SanPhamChiTiet
                     ORDER BY ID
                     OFFSET (? - 1) * 5 ROWS
                     FETCH NEXT 5 ROWS ONLY;
                     """;
        try {

            ps = con.prepareStatement(sql);
            ps.setObject(1, pageSP);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sp = new SanPhamChiTiet();
                sp.setId(rs.getString("ID"));
                sp.setIdsp(rs.getString("IDSP"));
                sp.setIdcl(rs.getString("IDCL"));
                sp.setIdpl(rs.getString("IDPL"));
                sp.setIdms(rs.getString("IDMS"));
                sp.setIdth(rs.getString("IDTH"));
                sp.setIdxx(rs.getString("IDXX"));
                sp.setKhoiLuong(rs.getFloat("KhoiLuong"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGia(rs.getFloat("Gia"));
                sp.setKichThuoc(rs.getString("KichThuoc"));
                listSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }
    
    public List<HoaDon> getAllHoaDon() {
       
        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon WHERE TrangThai = 0
                     """;
        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getString("IDHD"));
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMakh(rs.getString("MaKH"));
                hd.setManv(rs.getString("MaND"));
                hd.setTrangThai(rs.getByte("TrangThai"));
                hd.setNgaytao(rs.getString("NgayTao"));
                hd.setTongtien(rs.getFloat("TongTien"));
                listhd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public List<HoaDon> getAllHoaDon1(int pageHD) {
        if (pageHD < 1) {
            // Xử lý lỗi hoặc đặt giá trị mặc định
            return new ArrayList<>();
        }
        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon WHERE TrangThai = 1
                     ORDER BY IDHD
                     OFFSET (? - 1) * 5 ROWS
                     FETCH NEXT 5 ROWS ONLY;
                     """;
        try {

            ps = con.prepareStatement(sql);
            ps.setObject(1, pageHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getString("IDHD"));
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMakh(rs.getString("MaKH"));
                hd.setManv(rs.getString("MaNV"));
                hd.setTrangThai(rs.getByte("TrangThai"));
                hd.setNgaytao(rs.getString("NgayTao"));
                hd.setTongtien(rs.getFloat("TongTien"));
                listhd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public List<HoaDon> getAllHoaDon2(int pageHD) {
        if (pageHD < 1) {
            // Xử lý lỗi hoặc đặt giá trị mặc định
            return new ArrayList<>();
        }
        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon WHERE TrangThai = 2
                     ORDER BY IDHD
                     OFFSET (? - 1) * 5 ROWS
                     FETCH NEXT 5 ROWS ONLY;
                     """;
        try {

            ps = con.prepareStatement(sql);
            ps.setObject(1, pageHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getString("IDHD"));
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMakh(rs.getString("MaKH"));
                hd.setManv(rs.getString("MaNV"));
                hd.setTrangThai(rs.getByte("TrangThai"));
                hd.setNgaytao(rs.getString("NgayTao"));
                hd.setTongtien(rs.getFloat("TongTien"));
                listhd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public List<HoaDon> getAllHoaDon3(int pageHD) {
        if (pageHD < 1) {
            // Xử lý lỗi hoặc đặt giá trị mặc định
            return new ArrayList<>();
        }
        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon
                     ORDER BY IDHD
                     OFFSET (? - 1) * 5 ROWS
                     FETCH NEXT 5 ROWS ONLY;
                     """;
        try {

            ps = con.prepareStatement(sql);
            ps.setObject(1, pageHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getString("IDHD"));
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMakh(rs.getString("MaKH"));
                hd.setManv(rs.getString("MaND"));
                hd.setTrangThai(rs.getByte("TrangThai"));
                hd.setNgaytao(rs.getString("NgayTao"));
                hd.setTongtien(rs.getFloat("TongTien"));
                listhd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public List<HoaDon> getAllHD() {

        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon
                     """;
        try {

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getString("IDHD"));
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMakh(rs.getString("MaKH"));
                hd.setManv(rs.getString("MaND"));
                hd.setTrangThai(rs.getByte("TrangThai"));
                hd.setNgaytao(rs.getString("NgayTao"));
                hd.setTongtien(rs.getFloat("TongTien"));
                listhd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }
    public List<HoaDon> getAllHDCTT() {

        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon where TrangThai = 0
                     """;
        try {

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getString("IDHD"));
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMakh(rs.getString("MaKH"));
                hd.setManv(rs.getString("MaND"));
                hd.setTrangThai(rs.getByte("TrangThai"));
                hd.setNgaytao(rs.getString("NgayTao"));
                hd.setTongtien(rs.getFloat("TongTien"));
                listhd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }
    public List<HoaDon> getAllHDDTT() {

        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon where TrangThai =  1
                     """;
        try {

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getString("IDHD"));
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMakh(rs.getString("MaKH"));
                hd.setManv(rs.getString("MaND"));
                hd.setTrangThai(rs.getByte("TrangThai"));
                hd.setNgaytao(rs.getString("NgayTao"));
                hd.setTongtien(rs.getFloat("TongTien"));
                listhd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public boolean them(HoaDon th) {
        String sql = "Insert into HoaDon (MaHD,MaKH,MaND,NgayTao,TrangThai,TongTien) Values (?,?,?,?,0,0)";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, th.getMaHD());
            stm.setString(2, th.getMakh());
            stm.setString(3, th.getManv());
            stm.setString(4, th.getNgaytao());
            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public List<SanPhamChiTiet> getAllSP() {
        List<SanPhamChiTiet> listSP = new ArrayList<>();
        String sql = """
                     Select ID,IDSP, KhoiLuong,SoLuong,Gia,KichThuoc,IDTH,IDMS,IDPL,IDCL,IDXX From SanPhamChiTiet
                     where SoLuong > 0 and TrangThai = 0
                     """;
        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sp = new SanPhamChiTiet();
                sp.setId(rs.getString("ID"));
                sp.setIdsp(rs.getString("IDSP"));
                sp.setIdcl(rs.getString("IDCL"));
                sp.setIdpl(rs.getString("IDPL"));
                sp.setIdms(rs.getString("IDMS"));
                sp.setIdth(rs.getString("IDTH"));
                sp.setIdxx(rs.getString("IDXX"));
                sp.setKhoiLuong(rs.getFloat("KhoiLuong"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGia(rs.getFloat("Gia"));
                sp.setKichThuoc(rs.getString("KichThuoc"));
                listSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }
    public SanPham findIDSP(String IDSP) {
        SanPham gh = new SanPham();
        try {
            String sql = "Select MaSP , TenSP From SanPham Where ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDSP);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setMaSP(rs.getString("MaSP"));
                gh.setTenSP(rs.getString("TenSP"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }

    public HoaDon findIDHD(String mahd) {
        HoaDon gh = new HoaDon();
        try {
            String sql = "Select IDHD From HoaDon Where MaHD = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, mahd);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setId(rs.getString("IDHD"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }

    public boolean huyhd(HoaDon th) {
        String sql = "Update HoaDon set TrangThai = 2 where IDHD = ?";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, th.getId());

            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean TinhTongTien(String IDHD) {
        HoaDon th = new HoaDon();
        String sql = "  UPDATE HoaDon Set TongTien =(Select SUM(ThanhTien) From HoaDonChiTiet Where IDHD = ?)\n"
                + "  where  IDHD = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, IDHD);
            stm.setString(2, IDHD);
            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean ThanhToan(HoaDon th) {
        String sql = "Update HoaDon set TrangThai = 1 where IDHD = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, th.getId());
            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
