package Service;

import Utils.DBContext;
import java.sql.Connection;
import Model.SanPhamChiTiet;
import Model.SanPham_SPCT;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SPCTService {

    private Connection con = DBContext.getConnection();

    public List<SanPhamChiTiet> gettAll() {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet where SoLuong > 0 and TrangThai = 0";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                while (rs.next()) {
                    SanPhamChiTiet spct = new SanPhamChiTiet();
                    spct.setId(rs.getString("ID"));
                    spct.setIdsp(rs.getString("IDSP"));
                    spct.setIdcl(rs.getString("IDCL"));
                    spct.setIdpl(rs.getString("IDPL"));
                    spct.setIdms(rs.getString("IDMS"));
                    spct.setIdth(rs.getString("IDTH"));
                    spct.setIdxx(rs.getString("IDXX"));
                    spct.setKichThuoc(rs.getString("KichThuoc"));
                    spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                    spct.setSoLuong(rs.getInt("SoLuong"));
                    spct.setGia(rs.getFloat("Gia"));
                    spct.setMoTa(rs.getString("MoTa"));
                    spct.setAnh(rs.getString("Anh"));
                    spct.setTrangThai(rs.getByte("TrangThai"));
                    spct.setMaGG(rs.getString("MaGG"));
                    lspct.add(spct);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }

    public Integer ThemSPCT(SanPhamChiTiet spct) {
        Integer row = null;
        String sql = "Insert into SanPhamChiTiet(IDSP,IDCL,IDPL,IDMS,IDTH,IDXX,"
                + "KichThuoc,KhoiLuong,SoLuong,Gia,MoTa,Anh,TrangThai) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,0)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, spct.getIdsp());
            stm.setString(2, spct.getIdcl());
            stm.setString(3, spct.getIdpl());
            stm.setString(4, spct.getIdms());
            stm.setString(5, spct.getIdth());
            stm.setString(6, spct.getIdxx());
            stm.setString(7, spct.getKichThuoc());
            stm.setFloat(8, spct.getKhoiLuong());
            stm.setInt(9, spct.getSoLuong());
            stm.setFloat(10, spct.getGia());
            stm.setString(11, spct.getMoTa());
            stm.setString(12, spct.getAnh());
            row = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer SuaSPCT(SanPhamChiTiet spct) {
        Integer row = null;
        String sql = "UPDATE SanPhamChiTiet Set IDCL=?,IDPL=?,IDMS=?,IDTH=?,IDXX=?,KichThuoc =?,KhoiLuong =?,"
                + "SoLuong = ? , Gia = ?, MoTa = ?, Anh = ? Where ID = ? and TrangThai = 0";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, spct.getIdcl());
            stm.setString(2, spct.getIdpl());
            stm.setString(3, spct.getIdms());
            stm.setString(4, spct.getIdth());
            stm.setString(5, spct.getIdxx());
            stm.setString(6, spct.getKichThuoc());
            stm.setFloat(7, spct.getKhoiLuong());
            stm.setInt(8, spct.getSoLuong());
            stm.setFloat(9, spct.getGia());
            stm.setString(10, spct.getMoTa());
            stm.setString(11, spct.getAnh());
            stm.setString(12, spct.getId());
            row = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public SanPhamChiTiet findSPCT(String idspct) {
        SanPhamChiTiet spct = new SanPhamChiTiet();
        try {
            Connection cn = DBContext.getConnection();
            String sql = "Select * from SanPhamChiTiet where ID = ? and TrangThai = 0";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, idspct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                spct.setMaGG(rs.getString("MaGG"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return spct;
    }

    public List<SanPhamChiTiet> findSPCTByIDSP(String MaSP) {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "select * FROM SanPhamChiTiet where IDSP = ? and TrangThai = 0";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, MaSP);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                lspct.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lspct;
    }

    public SanPhamChiTiet findidspct(String IDSP) {
        SanPhamChiTiet spct = new SanPhamChiTiet();
        try {
            String sql = "select ID FROM SanPhamChiTiet where IDSP = ? and TrangThai = 0";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDSP);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                spct.setId(rs.getString("ID"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return spct;
    }

    public boolean SuaSoLuong(SanPhamChiTiet th) {
        String sql = "Update SanPhamChiTiet Set SoLuong = ? where ID = ?";
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

    public List<SanPham_SPCT> TimSPCT(String masp, String tensp, String soluong, String khoiluong, String kichthuoc, String idpl, String idth, String idms, String idxx, String idcl, String gia1, String gia2) {
        List<SanPham_SPCT> listspct = new ArrayList<>();
        try {
            String sql = "Select MaSP,TenSP,SanPhamChiTiet.ID,SanPhamChiTiet.IDSP,IDCL,IDPL,IDMS,IDTH,IDXX,KichThuoc,KhoiLuong,SoLuong,Gia From SanPham\n"
                    + "join SanPhamChiTiet on SanPham.ID = SanPhamChiTiet.IDSP where (MaSP like '%" + masp + "%' or TenSP Like '%" + tensp + "%' )"
                    + "and SoLuong like '%" + soluong + "%' and KhoiLuong like '%" + khoiluong + "%'\n"
                    + "and KichThuoc like '%" + kichthuoc + "%' and IDPL like '%" + idpl + "%' and IDTH like '%" + idth + "%' and IDMS like '%" + idms + "%' and IDXX like '%" + idxx + "%' and IDCL like '%" + idcl + "%' and SanPhamChiTiet.TrangThai = 0"
                    + "and Gia >= " + gia1 + " and Gia < " + gia2 + "and SanPhamChiTiet.TrangThai = 0";
            Statement st = con.createStatement();
//            ps.setString(1, idpl);
//            ps.setString(2, idth);
//            ps.setString(3, idms);
//            ps.setString(4, idxx);
//            ps.setString(5, idcl);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPham_SPCT spct = new SanPham_SPCT();
                spct.setMaSP(rs.getString("MaSP"));
                spct.setTenSP(rs.getString("TenSP"));
                spct.setIdspct(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdCL(rs.getString("IDCL"));
                spct.setIdPL(rs.getString("IDPL"));
                spct.setIdMS(rs.getString("IDMS"));
                spct.setIdTH(rs.getString("IDTH"));
                spct.setIdXX(rs.getString("IDXX"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                listspct.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listspct;
    }
    public List<SanPhamChiTiet> TimSPCT1(String masp, String tensp, String soluong, String khoiluong, String kichthuoc, String idpl, String idth, String idms, String idxx, String idcl, String gia1, String gia2) {
        List<SanPhamChiTiet> listspct = new ArrayList<>();
        try {
            String sql = "Select MaSP,TenSP,SanPhamChiTiet.ID,SanPhamChiTiet.IDSP,IDCL,IDPL,IDMS,IDTH,IDXX,KichThuoc,KhoiLuong,SoLuong,Gia From SanPham\n"
                    + "join SanPhamChiTiet on SanPham.ID = SanPhamChiTiet.IDSP where (MaSP like '%" + masp + "%' or TenSP Like '%" + tensp + "%' )"
                    + "and SoLuong like '%" + soluong + "%' and KhoiLuong like '%" + khoiluong + "%'\n"
                    + "and KichThuoc like '%" + kichthuoc + "%' and IDPL like '%" + idpl + "%' and IDTH like '%" + idth + "%' and IDMS like '%" + idms + "%' and IDXX like '%" + idxx + "%' and IDCL like '%" + idcl + "%' and SanPhamChiTiet.TrangThai = 0"
                    + "and Gia >= " + gia1 + " and Gia < " + gia2 + " and SanPhamChiTiet.TrangThai = 0";
            Statement st = con.createStatement();
//            ps.setString(1, idpl);
//            ps.setString(2, idth);
//            ps.setString(3, idms);
//            ps.setString(4, idxx);
//            ps.setString(5, idcl);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                    spct.setId(rs.getString("ID"));
                    spct.setIdsp(rs.getString("IDSP"));
                    spct.setIdcl(rs.getString("IDCL"));
                    spct.setIdpl(rs.getString("IDPL"));
                    spct.setIdms(rs.getString("IDMS"));
                    spct.setIdth(rs.getString("IDTH"));
                    spct.setIdxx(rs.getString("IDXX"));
                    spct.setKichThuoc(rs.getString("KichThuoc"));
                    spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                    spct.setSoLuong(rs.getInt("SoLuong"));
                    spct.setGia(rs.getFloat("Gia"));
                    spct.setMoTa(rs.getString("MoTa"));
                    spct.setAnh(rs.getString("Anh"));
                    spct.setTrangThai(rs.getByte("TrangThai"));
                    spct.setMaGG(rs.getString("MaGG"));
                listspct.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listspct;
    }
    public List<SanPhamChiTiet> TimKiem(String tePl,String checkmagg ) {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet join PhanLoai on PhanLoai.ID = SanPhamChiTiet.IDPL"
                    + " where( TenPL Like N'%" + tePl + "%' and TrangThai = 0 )"+checkmagg +" ";
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setMaGG(rs.getString("MaGG"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                lspct.add(spct);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }
    public List<SanPhamChiTiet> findByMaGG(String MaGG) {
        List<SanPhamChiTiet> ggList = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet Where MaGG =? and TrangThai=0";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, MaGG);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setMaGG(rs.getString("MaGG"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                ggList.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ggList;
    }
    public boolean ApDungMaGGtoSPCT(SanPhamChiTiet spct) {
        String sql = "UPDATE SanPhamChiTiet SET MaGG=? Where ID = ? and TrangThai = 0";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, spct.getMaGG());
            pstm.setString(2, spct.getId());

            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return false;
    }
    public void checkMaGG(String MaGG){
        try {
            con = DBContext.getConnection();
            String sql = "Update SanPhamChiTiet Set MaGG = null  where MaGG = ? and TrangThai = 0";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, MaGG);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<SanPhamChiTiet> gettAllSPSapHetPage(int page) {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        String sql = """
                     Select * from SanPhamChiTiet WHERE SoLuong < 10 and TrangThai = 0
                     ORDER BY ID
                     OFFSET (? - 1) * 5 ROWS
                     FETCH NEXT 5 ROWS ONLY
                     """;
        try {
            
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setObject(1, page);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                while (rs.next()) {
                    SanPhamChiTiet spct = new SanPhamChiTiet();
                    spct.setId(rs.getString("ID"));
                    spct.setIdsp(rs.getString("IDSP"));
                    spct.setIdcl(rs.getString("IDCL"));
                    spct.setIdpl(rs.getString("IDPL"));
                    spct.setIdms(rs.getString("IDMS"));
                    spct.setIdth(rs.getString("IDTH"));
                    spct.setIdxx(rs.getString("IDXX"));
                    spct.setKichThuoc(rs.getString("KichThuoc"));
                    spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                    spct.setSoLuong(rs.getInt("SoLuong"));
                    spct.setGia(rs.getFloat("Gia"));
                    spct.setMoTa(rs.getString("MoTa"));
                    spct.setAnh(rs.getString("Anh"));
                    spct.setTrangThai(rs.getByte("TrangThai"));
                    lspct.add(spct);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }
    public List<SanPhamChiTiet> gettAllSPSapHet() {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet WHERE SoLuong < 10 TrangThai = 0";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                while (rs.next()) {
                    SanPhamChiTiet spct = new SanPhamChiTiet();
                    spct.setId(rs.getString("ID"));
                    spct.setIdsp(rs.getString("IDSP"));
                    spct.setIdcl(rs.getString("IDCL"));
                    spct.setIdpl(rs.getString("IDPL"));
                    spct.setIdms(rs.getString("IDMS"));
                    spct.setIdth(rs.getString("IDTH"));
                    spct.setIdxx(rs.getString("IDXX"));
                    spct.setKichThuoc(rs.getString("KichThuoc"));
                    spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                    spct.setSoLuong(rs.getInt("SoLuong"));
                    spct.setGia(rs.getFloat("Gia"));
                    spct.setMoTa(rs.getString("MoTa"));
                    spct.setAnh(rs.getString("Anh"));
                    spct.setTrangThai(rs.getByte("TrangThai"));
                    lspct.add(spct);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }
    public Integer delete(String id){
        Integer row = null;
        try {
            con = DBContext.getConnection();
            String sql = "Delete SanPhamChiTiet where ID = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    public Integer AnSPCT(String id){
        Integer row = null;
        try {
            con = DBContext.getConnection();
            String sql = "Update SanPhamChiTiet set TrangThai = 1 where ID = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return row;
    }
}
