
package Model;

import java.util.Date;


public class HoaDon {
    String id;
    String maHD;
    String manv;
    String makh;
    String ngaytao;
    Float tongtien;
    int trangThai;

    public HoaDon() {
    }

    public HoaDon(String id, String maHD, String manv, String makh, String ngaytao, Float tongtien, int trangThai) {
        this.id = id;
        this.maHD = maHD;
        this.manv = manv;
        this.makh = makh;
        this.ngaytao = ngaytao;
        this.tongtien = tongtien;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Float getTongtien() {
        return tongtien;
    }

    public void setTongtien(Float tongtien) {
        this.tongtien = tongtien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    

    
    
}
