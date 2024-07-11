/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.NguoiDung;
import Utils.DBContext;
import Utils.XDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class NguoiDungService {

    Connection cn = DBContext.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<NguoiDung> getAll() {
        List<NguoiDung> lnd = new ArrayList<>();
        try {
            String sql = "Select * from NguoiDung where TrangThai = 0";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung();
                nd.setID(rs.getString("ID"));
                nd.setTenDangNhap(rs.getString("TenDangNhap"));
                nd.setMatKhau(rs.getString("MatKhau"));
                nd.setHoTen(rs.getString("HoTen"));
                nd.setNgaySinh(XDate.toString(rs.getDate("NgaySinh"), "dd/MM/yyyy"));
                nd.setGioiTinh(rs.getString("GioiTinh"));
                nd.setDiaChi(rs.getString("DiaChi"));
                nd.setSDT(rs.getString("SDT"));
                nd.setEmail(rs.getString("Email"));
                nd.setTrangThai(rs.getByte("TrangThai"));
                nd.setVaiTro(rs.getBoolean("VaiTro"));
                lnd.add(nd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lnd;
    }

    public Integer addNguoiDung(NguoiDung nd) {
        Integer row = null;
        try {
            cn = DBContext.getConnection();
            String sql = "insert into NguoiDung(TenDangNhap,MatKhau,HoTen,GioiTinh,NgaySinh,SDT,Email,VaiTro,DiaChi,TrangThai)\n"
                    + "	values(?,1,?,?,?,?,?,?,?,0)";

            ps = cn.prepareStatement(sql);
            ps.setString(1, nd.getTenDangNhap());
            ps.setString(2, nd.getHoTen());
            ps.setString(3, nd.getGioiTinh());
            ps.setString(4, nd.getNgaySinh());
            ps.setString(5,nd.getSDT());
            ps.setString(6, nd.getEmail());
            ps.setBoolean(7, nd.getVaiTro());
            ps.setString(8, nd.getDiaChi());
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    public Integer updateNguoiDung(NguoiDung n){
        Integer row = null;
        try {
            String sql = "Update NguoiDung set TenDangNhap = ?,HoTen = ?,GioiTinh = ?,NgaySinh = ?,SDT = ?,Email = ?,VaiTro = ?,DiaChi = ? where ID = ? and TrangThai = 0";
            cn = DBContext.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, n.getTenDangNhap());
            ps.setString(2, n.getHoTen());
            ps.setString(3, n.getGioiTinh());
            ps.setString(4, n.getNgaySinh());
            ps.setString(5, n.getSDT());
            ps.setString(6, n.getEmail());
            ps.setBoolean(7, n.getVaiTro());
            ps.setString(8, n.getDiaChi());
            ps.setString(9, n.getID());
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    public Integer anNguoiDung(String id){
        Integer row = null;
        try {
            cn = DBContext.getConnection();
            String sql = "Update NguoiDung set TrangThai = 1 where ID = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, id);
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return row;
    }
    public NguoiDung findByTDN(String tdn){
        NguoiDung nd = new NguoiDung();
        try {
            String sql = "Select * from NguoiDung where TenDangNhap = ? and TrangThai = 0";
            ps = cn.prepareStatement(sql);
            ps.setString(1, tdn);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                nd.setID(rs.getString("ID"));
                nd.setTenDangNhap(rs.getString("TenDangNhap"));
                nd.setMatKhau(rs.getString("MatKhau"));
                nd.setHoTen(rs.getString("HoTen"));
                nd.setNgaySinh(XDate.toString(rs.getDate("NgaySinh"), "dd/MM/yyyy"));
                nd.setGioiTinh(rs.getString("GioiTinh"));
                nd.setDiaChi(rs.getString("DiaChi"));
                nd.setSDT(rs.getString("SDT"));
                nd.setEmail(rs.getString("Email"));
                nd.setTrangThai(rs.getByte("TrangThai"));
                nd.setVaiTro(rs.getBoolean("VaiTro"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nd;
    }
    public NguoiDung findByID(String id){
        NguoiDung nd = new NguoiDung();
        try {
            String sql = "Select * from NguoiDung where ID = ? and TrangThai = 0";
            ps = cn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                nd.setID(rs.getString("ID"));
                nd.setTenDangNhap(rs.getString("TenDangNhap"));
                nd.setMatKhau(rs.getString("MatKhau"));
                nd.setHoTen(rs.getString("HoTen"));
                nd.setNgaySinh(XDate.toString(rs.getDate("NgaySinh"), "dd/MM/yyyy"));
                nd.setGioiTinh(rs.getString("GioiTinh"));
                nd.setDiaChi(rs.getString("DiaChi"));
                nd.setSDT(rs.getString("SDT"));
                nd.setEmail(rs.getString("Email"));
                nd.setTrangThai(rs.getByte("TrangThai"));
                nd.setVaiTro(rs.getBoolean("VaiTro"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nd;
    }
    
}
