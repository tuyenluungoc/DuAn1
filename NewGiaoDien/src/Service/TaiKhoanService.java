/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Utils.DBContext;
import Model.TaiKhoan;
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
public class TaiKhoanService {

    Connection cn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    public List<TaiKhoan> getAll() {
        List<TaiKhoan> ltk = new ArrayList<>();
        try {
            cn = DBContext.getConnection();
            String sql = "Select * from TaiKhoan ";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setId(rs.getString("ID"));
                tk.setMaTK(rs.getString("MaTK"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setVaiTro(rs.getString("VaiTro"));

                ltk.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ltk;
    }

    public Integer addTK(TaiKhoan tk) {
        Integer row = null;
        try {
            cn = DBContext.getConnection();
            String sql = "Insert into TaiKhoan( MaTK, TenDangNhap,MatKhau,VaiTro) values(?,?,?,?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, tk.getMaTK());
            pstm.setString(2, tk.getTenDangNhap());
            pstm.setString(3, tk.getMatKhau());
            pstm.setString(4, tk.getVaiTro());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return row;
    }

    public boolean suaTK(TaiKhoan tk) {
        String sql = "Update TaiKhoan set  TenDangNhap = ?,MatKhau=?,VaiTro =? where ID = ?";
        try {
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, tk.getTenDangNhap());
            stm.setString(2, tk.getMatKhau());
            stm.setString(3, tk.getVaiTro());
            stm.setString(4, tk.getId());
            stm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
    }

    

    public Integer deleteTK(String ID) {
        Integer row = null;
        try {
            cn = DBContext.getConnection();
            String sql = "DELETE FROM [dbo].[TaiKhoan]  where ID = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, ID);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return row;
    }

    public TaiKhoan findByID(String i) {
        TaiKhoan tk = null;
        try {
            cn = DBContext.getConnection();
            String sql = "Select * from TaiKhoan where ID = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setObject(1, i);
            rs = pstm.executeQuery();
            while (rs.next()) {
                tk = new TaiKhoan();
                tk.setId(rs.getString("ID"));
                tk.setMaTK(rs.getString("MaTK"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setVaiTro(rs.getString("VaiTro"));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return tk;
    }
}
