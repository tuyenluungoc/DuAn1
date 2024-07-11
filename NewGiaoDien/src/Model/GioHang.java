
package Model;


public class GioHang {
    String masp;
    String tensp;
    Float giatri;

    public GioHang() {
    }

    public GioHang(String masp, String tensp, Float giatri) {
        this.masp = masp;
        this.tensp = tensp;
        this.giatri = giatri;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public Float getGiatri() {
        return giatri;
    }

    public void setGiatri(Float giatri) {
        this.giatri = giatri;
    }

    
    
    
}
