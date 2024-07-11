package Service;

import Utils.DBContext;
import Model.HoaDonChiTiet;
import Model.GioHang;
import Model.HoaDon;
import Model.SanPhamChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HDCTService {

    Connection con = DBContext.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<HoaDonChiTiet> getAllHoaDon() {
        List<HoaDonChiTiet> listhd = new ArrayList<>();
        try {
            String sql = "Select * from HoaDonChiTiet";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hd = new HoaDonChiTiet();
                hd.setIdhd(rs.getString("IDHD"));
                hd.setIdspct(rs.getString("IDSPCT"));
                hd.setSoLuong(rs.getInt("SoLuong"));
                hd.setDongia(rs.getFloat("DonGia"));
                hd.setThanhtien(rs.getFloat("ThanhTien"));

                listhd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public List<HoaDonChiTiet> getAllHoaDon123(String IDHD) {
        List<HoaDonChiTiet> listhd = new ArrayList<>();
        try {
            String sql = "Select * from HoaDonChiTiet where IDHD = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDHD);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hd = new HoaDonChiTiet();
                hd.setIdhd(rs.getString("IDHD"));
                hd.setIdspct(rs.getString("IDSPCT"));
                hd.setSoLuong(rs.getInt("SoLuong"));
                hd.setDongia(rs.getFloat("DonGia"));
                hd.setThanhtien(rs.getFloat("ThanhTien"));

                listhd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public GioHang findIDSP(String IDSPCT) {
        GioHang gh = new GioHang();
        try {
            String sql = "Select MaSP , TenSP From SanPham\n"
                    + "join SanPhamChiTiet on SanPham.ID = SanPhamChiTiet.IDSP\n"
                    + "where SanPhamChiTiet.ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDSPCT);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setMasp(rs.getString("MaSP"));
                gh.setTensp(rs.getString("TenSP"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }

    public GioHang findMaGG(String magg) {
        GioHang gh = new GioHang();
        try {
            String sql = "Select GiaTri From GiamGia Where MaGG = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, magg);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setGiatri(rs.getFloat("GiaTri"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }

    public boolean them(HoaDonChiTiet th) {
        String sql = "Insert into HoaDonChiTiet (IDHD,IDSPCT,SoLuong,DonGia,ThanhTien) Values (?,?,?,?,?)";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, th.getIdhd());
            stm.setString(2, th.getIdspct());
            stm.setInt(3, th.getSoLuong());
            stm.setFloat(4, th.getDongia());
            stm.setFloat(5, th.getThanhtien());
            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean xoa(HoaDonChiTiet th) {
        String sql = "DELETE FROM HoaDonChiTiet Where ID = ?";
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
    
    public List<HoaDonChiTiet> getAllGH(String i){
        List<HoaDonChiTiet> lhdct = new ArrayList<>();
        String sql = "Select ID,IDSPCT,SoLuong,DonGia,ThanhTien FROM HoaDonChiTiet Where IDHD = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, i);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String idspct = rs.getString(2);
                int soluong = rs.getInt(3);
                Float dongia = rs.getFloat(4);
                float thanhien = rs.getFloat(5);

                HoaDonChiTiet d = new HoaDonChiTiet(id, id, idspct, soluong, dongia, thanhien);
                lhdct.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lhdct;
    }

    public boolean suasp(SanPhamChiTiet th) {
        String sql = "Update SanPhamChiTiet set SoLuong = ? where ID = ?";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, th.getSoLuong());
            stm.setString(2, th.getId());

            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean suathanhtien(HoaDonChiTiet th) {
        String sql = "Update HoaDonChiTiet Set ThanhTien = ? From HoaDonChiTiet where ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setFloat(1, th.getThanhtien());
            stm.setString(2, th.getId());
            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean suahd(HoaDonChiTiet th) {
        String sql = "Update HoaDonChiTiet set SoLuong = ?  where ID = ?";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, th.getSoLuong());
            stm.setString(2, th.getId());

            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public SanPhamChiTiet findSoLuong(String IDHDCT) {
        SanPhamChiTiet gh = new SanPhamChiTiet();
        try {
            String sql = "    select SanPhamChiTiet.SoLuong From SanPhamChiTiet \n"
                    + "  join HoaDonChiTiet on SanPhamChiTiet.ID = HoaDonChiTiet.IDSPCT \n"
                    + "  where HoaDonChiTiet.ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDHDCT);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setSoLuong(rs.getInt("SoLuong"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }

    public HoaDonChiTiet findIDSPCT(String IDHDCT) {
        HoaDonChiTiet gh = new HoaDonChiTiet();
        try {
            String sql = "Select IDSPCT From HoaDonChiTiet where ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDHDCT);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setIdspct(rs.getString("IDSPCT"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }
    public Boolean XoaHet(String idhd){
        String sql = "delete HoaDonChiTiet Where IDHD = ?";
        try {
            con = DBContext.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idhd);
            st.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
