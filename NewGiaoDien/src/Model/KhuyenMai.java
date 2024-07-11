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
public class KhuyenMai {
    String id;
    String idlgg;
    String maGG;
    String tenGG;
    Float giaTri;
    Date ngayBatDau;
    Date ngayHetHan;
    String ghiChu;
    boolean trangThai;

    public KhuyenMai() {
    }

    public KhuyenMai(String id, String idlgg, String maGG, String tenGG, Float giaTri, Date ngayBatDau, Date ngayHetHan, String ghiChu, boolean trangThai) {
        this.id = id;
        this.idlgg = idlgg;
        this.maGG = maGG;
        this.tenGG = tenGG;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdlgg() {
        return idlgg;
    }

    public void setIdlgg(String idlgg) {
        this.idlgg = idlgg;
    }

    public String getMaGG() {
        return maGG;
    }

    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }

    public String getTenGG() {
        return tenGG;
    }

    public void setTenGG(String tenGG) {
        this.tenGG = tenGG;
    }

    public Float getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Float giaTri) {
        this.giaTri = giaTri;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
}
