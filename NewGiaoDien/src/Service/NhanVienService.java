/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Utils.DBContext;
import Model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class NhanVienService {

    private Connection conn = DBContext.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<NhanVien> getAllNV() {
        List<NhanVien> listNV = new ArrayList<>();
        sql = "SELECT MaNV,IDTaiKhoan, HoTen, NgaySinh,GioiTinh, DiaChi, SDT, Email FROM NhanVien Where TrangThai = 0";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setIdtk(rs.getString("IDTaiKhoan"));
                nv.setTenNV(rs.getString("HoTen"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSdt(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                listNV.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }
    
    public List<NhanVien> getAllNVbyID(String id) {
        List<NhanVien> listNV = new ArrayList<>();
        sql = "SELECT MaNV,IDTaiKhoan, HoTen, NgaySinh,GioiTinh, DiaChi, SDT, Email FROM NhanVien Where IDTaiKhoan = ?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setIdtk(rs.getString("IDTaiKhoan"));
                nv.setTenNV(rs.getString("HoTen"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSdt(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                listNV.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }

    public List<NhanVien> getAllNVHienThi() {
        List<NhanVien> listNV = new ArrayList<>();
        sql = "SELECT MaNV,IDTaiKhoan, HoTen, NgaySinh,GioiTinh, DiaChi, SDT, Email FROM NhanVien Where TrangThai = 1";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setIdtk(rs.getString("IDTaiKhoan"));
                nv.setTenNV(rs.getString("HoTen"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSdt(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                listNV.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }

    public boolean them(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (MaNV,IDTaiKhoan, HoTen,GioiTinh,NgaySinh,DiaChi,SDT,Email,TrangThai) VALUES (?,?,?,?,?,?,?,?,0)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nv.getMaNV());
            stm.setString(2, nv.getIdtk());
            stm.setString(3, nv.getTenNV());
            stm.setString(4, nv.getGioiTinh());
            stm.setDate(5, new java.sql.Date(nv.getNgaySinh().getTime()));
            stm.setString(6, nv.getDiaChi());
            stm.setString(7, nv.getSdt());
            stm.setString(8, nv.getEmail());
            stm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
    }

    public boolean sua(NhanVien nv) {
        String sql = "UPDATE NHANVIEN SET HoTen = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ?, SDT = ?,Email = ?,TrangThai = 1 Where MaNV = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nv.getTenNV());
            stm.setString(2, nv.getGioiTinh());
            stm.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
            stm.setString(4, nv.getDiaChi());
            stm.setString(5, nv.getSdt());
            stm.setString(6, nv.getEmail());
            stm.setString(7, nv.getMaNV());
            stm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
    }

    public boolean xoa(NhanVien nv) {
        String sql = "DELETE FROM NhanVien Where MaNV = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nv.getMaNV());
            stm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
    }

    public boolean anNV(NhanVien nv) {
        String sql = "UPDATE NhanVien SET TrangThai = 1 Where MaNV = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nv.getMaNV());
            stm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
    }

    public boolean hienthilaiNV(NhanVien nv) {
        String sql = "UPDATE NhanVien SET TrangThai = 0 Where MaNV = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nv.getMaNV());
            stm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
    }
    public NhanVien TimTen(String ma) {
        NhanVien nv = new NhanVien();
        String sql = "Select HoTen From NhanVien Where MaNV = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ma);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                nv.setTenNV(rs.getString("HoTen"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return nv;
    }
}
