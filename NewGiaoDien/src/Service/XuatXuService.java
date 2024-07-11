package Service;

import Model.SanPhamChiTiet;
import Model.XuatXu;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class XuatXuService {

    private Connection con = DBContext.getConnection();

    public boolean them(XuatXu th) {
        String sql = "Insert into XuatXu (TenXX) Values (?)";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, th.getTen());
            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean sua(XuatXu th) {
        String sql = "UPDATE XuatXu set TenXX = ? Where ID = ?";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, th.getTen());
            stm.setString(2, th.getId());
            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean an(XuatXu th) {
        String sql = "DELETE FROM XuatXu Where ID = ?";
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

    public XuatXu findXX(String id) {
        XuatXu ms = new XuatXu();
        try {
            String sql = "select * from XuatXu where ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ms.setId(rs.getString("id"));
                ms.setTen(rs.getString("TenXX"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ms;
    }

    public XuatXu findidXX(String ten) {
        XuatXu ms = new XuatXu();
        try {
            String sql = "select ID from XuatXu where TenXX = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, ten);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ms.setId(rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms;
    }

    public List<XuatXu> gettAll() {
        List<XuatXu> lspct = new ArrayList<>();
        try {
            String sql = "Select * from XuatXu";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                XuatXu spct = new XuatXu();
                spct.setId(rs.getString("ID"));
                spct.setTen(rs.getString("TenXX"));
                lspct.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }
}
