package Utils;

import Model.NguoiDung;



public class Auth {
    public static NguoiDung user = null;

    public static void clear() {
        Auth.user = null;
    }
    
    public static boolean isLogin() {
        return Auth.user != null;
    }
    
    public static boolean isManager() {
        return Auth.isLogin() && user.getVaiTro();
    }
}
