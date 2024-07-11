/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Utils.DBContext;
import Model.KhachHang;
import Utils.XDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class KhachHangService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<KhachHang> getAllKH() {
        List<KhachHang> listKH = new ArrayList<>();
        sql = "SELECT * FROM KhachHang Where TrangThai = 0";
        try {
            con = DBContext.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setNgaySinh(XDate.toString(rs.getDate("NgaySinh"), "dd/MM/yyyy"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSDT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setTrangThai(rs.getByte("TrangThai"));
                listKH.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKH;
    }

    

    public Integer addKH(KhachHang kh) {
        Integer row = null;
        try {
            con = DBContext.getConnection();
            String sql = "Insert into KhachHang values(?,?,?,?,?,?,?,0)";
            ps = con.prepareStatement(sql);
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getNgaySinh());
            ps.setString(4, kh.getGioiTinh());
            ps.setString(5, kh.getDiaChi());
            ps.setString(6, kh.getSDT());
            ps.setString(7, kh.getEmail());

            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        return row;
    }

    public Integer updateKH(KhachHang kh) {
        Integer row = null;
        try {
            con = DBContext.getConnection();
            String sql = "UPDATE KhachHang set HoTen=?, NgaySinh = ?, GioiTinh = ?, DiaChi = ?, SDT = ?, Email = ? where MaKH = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getNgaySinh());
            ps.setString(3, kh.getGioiTinh());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getSDT());
            ps.setString(6, kh.getEmail());
            ps.setString(7, kh.getMaKH());
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        return row;
    }

    public Integer anKH(String MaKH) {
        Integer row = null;
        String sql = "UPDATE KhachHang SET TrangThai = 1 Where MaKH = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, MaKH);
            row = stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    public boolean hienthilaiKh(String MaKH) {
        String sql = "UPDATE KhachHang SET TrangThai = 0 Where MaKH = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, MaKH);
            stm.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
    }
    public KhachHang findByMa(String ma) {
        con = DBContext.getConnection();
        KhachHang kh = new KhachHang();
        String sql = "Select * From KhachHang Where MaKH = ? and TrangThai = 0";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, ma);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                kh.setMaKH(rs.getString("MaKH"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setNgaySinh(XDate.toString(rs.getDate("NgaySinh"), "dd/MM/yyyy"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSDT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setTrangThai(rs.getByte("TrangThai"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return kh;
    }
    public KhachHang findBySDT(String sdt){
        con = DBContext.getConnection();
        KhachHang kh = new KhachHang();
        String sql = "Select * From KhachHang Where SDT = ? and TrangThai = 0";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, sdt);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                kh.setMaKH(rs.getString("MaKH"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setNgaySinh(XDate.toString(rs.getDate("NgaySinh"), "dd/MM/yyyy"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSDT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setTrangThai(rs.getByte("TrangThai"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return kh;
    }
}
