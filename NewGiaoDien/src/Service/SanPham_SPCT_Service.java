/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Utils.DBContext;
import Model.SanPham_SPCT;
import java.sql.*;

/**
 *
 * @author builo
 */
public class SanPham_SPCT_Service {

    Connection con = DBContext.getConnection();

    public boolean ThemSP_SPCT(SanPham_SPCT sp) {
        String sql = "Insert into SanPham_SanPhamChiTiet (IDSanPham,IDSanPhamChiTiet) Values (?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, sp.getIdsp());
            stm.setString(2, sp.getIdspct());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
