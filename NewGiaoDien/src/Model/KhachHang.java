/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;


/**
 *
 * @author admin
 */
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class KhachHang {

    String MaKH,HoTen,GioiTinh,NgaySinh,DiaChi,SDT,Email;
    Byte TrangThai;

    public KhachHang() {
    }

    public KhachHang(String MaKH, String HoTen, String GioiTinh, String NgaySinh, String DiaChi, String SDT, String Email, Byte TrangThai) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.TrangThai = TrangThai;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Byte getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Byte TrangThai) {
        this.TrangThai = TrangThai;
    }

}
