
package Model;


public class HoaDonChiTiet {
    String id;
    String idhd;
    String idspct;
    Integer soLuong;
    Float dongia;
    Float thanhtien;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String id, String idhd, String idspct, Integer soLuong, Float dongia, Float thanhtien) {
        this.id = id;
        this.idhd = idhd;
        this.idspct = idspct;
        this.soLuong = soLuong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdhd() {
        return idhd;
    }

    public void setIdhd(String idhd) {
        this.idhd = idhd;
    }

    public String getIdspct() {
        return idspct;
    }

    public void setIdspct(String idspct) {
        this.idspct = idspct;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getDongia() {
        return dongia;
    }

    public void setDongia(Float dongia) {
        this.dongia = dongia;
    }

    public Float getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(Float thanhtien) {
        this.thanhtien = thanhtien;
    }

    
    
    public Object[] toDataRow() {
        return new Object[]{this.id,this.idhd, this.idspct, this.soLuong, this.dongia,this.thanhtien};
    }

}
