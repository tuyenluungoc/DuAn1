package Service;

import Model.SanPham;
import Utils.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamService {

    Connection con = DBContext.getConnection();
    PreparedStatement pstm = null;
    ResultSet rs = null;

    public List<SanPham> gettAll() {
        List<SanPham> lsp = new ArrayList<>();
        try {
            con = DBContext.getConnection();
            String sql = "Select * from SanPham where TrangThai = 0";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));
                lsp.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsp;
    }

    public List<SanPham> gettAllPT(int pageNumber, int pageSize) {
        List<SanPham> lsp = new ArrayList<>();
        try ( Connection cn1 = DBContext.getConnection()) {
            int offset = (pageNumber - 1) * pageSize;
            String sql = "SELECT * FROM SanPham where TrangThai = 0 ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
            try ( PreparedStatement ps = cn1.prepareStatement(sql)) {
                ps.setInt(1, offset);
                ps.setInt(2, offset + pageSize);
                try ( ResultSet rs1 = ps.executeQuery()) {

                    while (rs1.next()) {
                        SanPham sp = new SanPham();
                        sp.setId(rs1.getString("ID"));
                        sp.setMaSP(rs1.getString("MaSP"));
                        sp.setTenSP(rs1.getString("TenSP"));
                        sp.setTrangThai(rs1.getByte("TrangThai"));
                        lsp.add(sp);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lsp;
    }

    public Integer AddSP(SanPham sp) {
        Integer row = null;
        String sql = "Insert into SanPham(MaSP,TenSP,TrangThai) values (?,?,0)";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, sp.getMaSP());
            pstm.setString(2, sp.getTenSP());
            row = pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer UpdateSP(SanPham sp) {
        Integer row = null;
        String sql = "UPDATE SanPham SET TenSP=?,MaSP=? Where ID = ?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, sp.getTenSP());
            pstm.setString(2, sp.getMaSP());
            pstm.setString(3, sp.getId());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    public Integer AnSP(String ID) {
        Integer row = null;
        String sql = "UPDATE SanPham SET TrangThai = 1 Where ID = ?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, ID);
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    public SanPham findSPByMa(String ma) {
        SanPham sp = new SanPham();
        try {
            con = DBContext.getConnection();
            String sql = "select * from SanPham where MaSP = ? and TrangThai =0";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, ma);
            rs = pstm.executeQuery();
            while (rs.next()) {
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

    public SanPham findSPByID(String ID) {
        SanPham sp = new SanPham();
        try {
            String sql = "select * from SanPham where ID = ? and TrangThai = 0";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, ID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

    public List<SanPham> findByMa(String Ma) {
        List<SanPham> ls = new ArrayList<>();
        try {
            String sql = "select * from SanPham where MaSP = ? and TrangThai = 0";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, Ma);
            rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));
                ls.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    

    public List<SanPham> findAll(String text, int pageNumber, int pageSize) {
        List<SanPham> ls = new ArrayList<>();
        try {
            int offset = (pageNumber - 1) * pageSize;
            String sql = "select * from SanPham where MaSP like ? and TrangThai = 0 or TenSP like ? and TrangThai = 0  ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, "%"+text+"%");
            pstm.setString(2, "%"+text+"%");
            pstm.setInt(3, offset);
            pstm.setInt(4, offset + pageSize);
            rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));
                ls.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<SanPham> findAnByMa(String Ma) {
        List<SanPham> ls = new ArrayList<>();
        try {
            String sql = "select * from SanPham where MaSP = ? and TrangThai = 1";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, Ma);
            rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));
                ls.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<SanPham> gettAllAn() {
        List<SanPham> lsp = new ArrayList<>();
        try {
            con = DBContext.getConnection();
            String sql = "Select * from SanPham where TrangThai = 1";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));
                lsp.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsp;
    }

    public Integer DeleteSP(String ID) {
        Integer row = null;
        String sql = "Delete SanPhamChiTiet where idsp = ?  Delete SanPham Where ID = ?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, ID);
            pstm.setString(2, ID);
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    public void HienThiLaiSP(String ID) {
        String sql = "UPDATE SanPham SET TrangThai = 0 Where ID = ?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, ID);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public SanPham findByID(String ID) {
        SanPham sp = new SanPham();
        try {
            String sql = "select * from SanPham where ID = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, ID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
 
    }
}
