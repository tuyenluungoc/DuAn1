
package Service;

import Model.MauSac;
import Model.PhanLoai;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MauSacService {
    private Connection con = DBContext.getConnection();
    
    public boolean Them(MauSac ms){
        String sql = "Insert into MauSac (TenMS) VALUES (?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, ms.getTen());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean Sua(MauSac ms){
        String sql = "UPDATE MauSac SET  TenMS = ? WHERE ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, ms.getTen());
            stm.setString(2, ms.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean An(MauSac ms){
         String sql = "DELETE FROM MauSac Where ID = ?";
         try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, ms.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
         
    }
    public MauSac findMS(String id){
        MauSac ms= new MauSac();
        try {
            String sql = "select * from MauSac where ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ms.setId(rs.getString("id"));
                ms.setTen(rs.getString("TenMS"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return ms;
    }
    
    public MauSac findidMS(String ten){
        MauSac ms= new MauSac();
        try {
            String sql = "select ID from MauSac where TenMS = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, ten);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ms.setId(rs.getString("id"));                
            }          
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return ms;
    }
    
    public List<MauSac> gettAll() {
        List<MauSac> lspct = new ArrayList<>();
        try {
            String sql = "Select * from MauSac";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                MauSac spct = new MauSac();
                spct.setId(rs.getString("ID"));
                spct.setTen(rs.getString("TenMS"));
                lspct.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }
}
