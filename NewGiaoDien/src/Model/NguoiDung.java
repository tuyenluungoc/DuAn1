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
public class NguoiDung {
    String ID,TenDangNhap,MatKhau,HoTen,GioiTinh,NgaySinh,DiaChi,SDT,Email;
    Byte TrangThai;
    Boolean VaiTro;
    
    public NguoiDung() {
    }

    public NguoiDung(String ID, String TenDangNhap, String MatKhau, String HoTen, String GioiTinh, String NgaySinh, String DiaChi, String SDT, String Email, Byte TrangThai, Boolean VaiTro) {
        this.ID = ID;
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.TrangThai = TrangThai;
        this.VaiTro = VaiTro;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
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

    public Boolean getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(Boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

    

    

    
    
}
