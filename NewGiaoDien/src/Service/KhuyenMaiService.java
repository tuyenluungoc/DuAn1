package Service;

import Utils.DBContext;
import Model.KhuyenMai;
import Model.MauSac;
import Model.SanPham;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiService {

    Connection con = DBContext.getConnection();

    public List<KhuyenMai> gettAll() {
        List<KhuyenMai> ggList = new ArrayList<>();
        try {
            String sql = "Select * from GiamGia";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhuyenMai gg = new KhuyenMai();
                gg.setMaGG(rs.getString("MaGG"));
//                gg.setId(rs.getString("IDSPCT"));
                gg.setTenGG(rs.getString("Ten"));
                gg.setGiaTri(rs.getFloat("GiaTri"));
                gg.setNgayBatDau(rs.getDate("NgayBatDau"));
                gg.setNgayHetHan(rs.getDate("NgayHetHan"));
                gg.setGhiChu(rs.getString("GhiChu"));
                gg.setTrangThai(rs.getBoolean("TrangThai"));

                ggList.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ggList;
    }

    public KhuyenMai findIDSP(String idspct) {
        KhuyenMai gh = new KhuyenMai();
        try {
            String sql = "Select MaGG,GiaTri From GiamGia Where IDSPCT = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idspct);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setMaGG(rs.getString("MaGG"));
                gh.setGiaTri(rs.getFloat("GiaTri"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }

    public KhuyenMai findgiatri(String magg) {
        KhuyenMai gh = new KhuyenMai();
        try {
            String sql = "Select GiaTri From GiamGia Where MaGG = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, magg);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setGiaTri(rs.getFloat("GiaTri"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }

    public boolean ThemKhuyenMai(KhuyenMai km) {

        String sql = "Insert into GiamGia(MaGG,Ten,GiaTri,NgayBatDau,NgayHetHan,GhiChu,TrangThai) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, km.getMaGG());
            pstm.setString(2, km.getTenGG());
            pstm.setFloat(3, km.getGiaTri());
            pstm.setDate(4, new java.sql.Date(km.getNgayBatDau().getTime()));
            pstm.setDate(5, new java.sql.Date(km.getNgayHetHan().getTime()));
            pstm.setString(6, km.getGhiChu());
            pstm.setBoolean(7, km.getTrangThai());

            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<KhuyenMai> findBySPCT(String id) {
        List<KhuyenMai> ggList = new ArrayList<>();
        try {
            String sql = "Select * from GiamGia Where MaGG =?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhuyenMai gg = new KhuyenMai();
                gg.setMaGG(rs.getString("MaGG"));
//                gg.setId(rs.getString("IDSPCT"));
                gg.setTenGG(rs.getString("Ten"));
                gg.setGiaTri(rs.getFloat("GiaTri"));
                gg.setNgayBatDau(rs.getDate("NgayBatDau"));
                gg.setNgayHetHan(rs.getDate("NgayHetHan"));
                gg.setGhiChu(rs.getString("GhiChu"));
                gg.setTrangThai(rs.getBoolean("TrangThai"));

                ggList.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ggList;
    }
   public boolean UpdateKM(KhuyenMai km) {
       
        String sql = "UPDATE GiamGia SET Ten=?,GiaTri=?,NgayBatDau=?,NgayHetHan=?,GhiChu=?,TrangThai=? Where MaGG = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, km.getTenGG());
            pstm.setFloat(2, km.getGiaTri());
            pstm.setDate(3, new java.sql.Date(km.getNgayBatDau().getTime()));
            pstm.setDate(4, new java.sql.Date(km.getNgayHetHan().getTime()));
            pstm.setString(5,km.getGhiChu());
            pstm.setBoolean(6, km.getTrangThai());
            pstm.setString(7, km.getMaGG());
            pstm.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return false;
    }
   public boolean XoaKhuyenMai(KhuyenMai km){
       String sql = "DELETE  FROM GiamGia Where MaGG = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, km.getMaGG());
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
       
   }
    public List<KhuyenMai> TimKiem(String keyword) {
        String sql = "SELECT * FROM GiamGia WHERE MaGG LIKE ? or Ten Like ? or GiaTri Like ?";
        List<KhuyenMai> kmlist = new ArrayList<>();
        try (Connection conn = DBContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+keyword+"%");
            ps.setString(2, "%"+keyword+"%");
            ps.setString(3, "%"+keyword+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               KhuyenMai gg = new KhuyenMai();
                gg.setMaGG(rs.getString("MaGG"));
//                gg.setId(rs.getString("IDSPCT"));
                gg.setTenGG(rs.getString("Ten"));
                gg.setGiaTri(rs.getFloat("GiaTri"));
                gg.setNgayBatDau(rs.getDate("NgayBatDau"));
                gg.setNgayHetHan(rs.getDate("NgayHetHan"));
                gg.setGhiChu(rs.getString("GhiChu"));
                gg.setTrangThai(rs.getBoolean("TrangThai"));

                kmlist.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kmlist;
    }
    public List<KhuyenMai> getHetHan() {
        List<KhuyenMai> ls = new ArrayList<>();
        try {
            con = DBContext.getConnection();
            String sql = "Select  MaGG From GiamGia where TrangThai = 1";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {    
                KhuyenMai km = new KhuyenMai();
                km.setMaGG(rs.getString("MaGG"));
                ls.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    public boolean XetHetHan(String ngayhientai) {
        String sql = "UPDATE GiamGia SET TrangThai = 1  where NgayHetHan < '" + ngayhientai + "'";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
//          pstm.setDate(1, new java.sql.Date(km.getNgayHetHan().getTime()));
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return false;
    }
}
