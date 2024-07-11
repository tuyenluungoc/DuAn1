
package Service;

import Model.ThuongHieu;
import Model.XuatXu;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ThuongHieuService {
    private Connection con = DBContext.getConnection();
    
    public boolean them (ThuongHieu th){
        String sql = "Insert into ThuongHieu (TenTH) Values (?)";
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
    
    public boolean sua (ThuongHieu th){
        String sql = "UPDATE ThuongHieu set TenTH = ? Where ID = ?";
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
    
    public boolean an (ThuongHieu th){
        String sql = "DELETE FROM ThuongHieu Where ID = ?";
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
    
    public ThuongHieu findTH(String id){
        ThuongHieu ms= new ThuongHieu();
        try {
            String sql = "select * from ThuongHieu where ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ms.setId(rs.getString("id"));
                ms.setTen(rs.getString("TenTH"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return ms;
    }
    
    public ThuongHieu findidTH (String ten){
        ThuongHieu ms= new ThuongHieu();
        try {
            String sql = "select ID from ThuongHieu where TenTH = ?";
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
    
    public List<ThuongHieu> gettAll() {
        List<ThuongHieu> lspct = new ArrayList<>();
        try {
            String sql = "Select * from ThuongHieu";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ThuongHieu spct = new ThuongHieu();
                spct.setId(rs.getString("ID"));
                spct.setTen(rs.getString("TenTH"));
                lspct.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }
    
}
