
package Service;
import Model.ChatLieu;
import Model.MauSac;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ChatLieuService {
    private Connection con = DBContext.getConnection();
    
    public boolean Them(ChatLieu ms){
        String sql = "Insert into ChatLieu (TenCL) VALUES (?)";
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
    
    public boolean Sua(ChatLieu ms){
        String sql = "UPDATE ChatLieu SET  TenCL = ? WHERE ID = ?";
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
    
    public boolean An(ChatLieu ms){
         String sql = "DELETE FROM ChatLieu Where ID = ?";
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
    
    public ChatLieu findCL(String id){
        ChatLieu ms= new ChatLieu();
        try {
            String sql = "select * from ChatLieu where ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ms.setId(rs.getString("id"));
                ms.setTen(rs.getString("TenCL"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return ms;
    }
    
    public ChatLieu findidCL(String ten){
        ChatLieu ms= new ChatLieu();
        try {
            String sql = "select ID from ChatLieu where TenCL = ?";
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
    
    public List<ChatLieu> gettAll() {
        List<ChatLieu> lspct = new ArrayList<>();
        try {
            String sql = "Select * from ChatLieu";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ChatLieu spct = new ChatLieu();
                spct.setId(rs.getString("ID"));
                spct.setTen(rs.getString("TenCL"));
                lspct.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }
}
