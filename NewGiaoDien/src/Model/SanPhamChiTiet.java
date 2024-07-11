
package Model;


public class SanPhamChiTiet {
    String id;
    String idsp;
    String idcl;
    String maGG;
    String idpl;
    String idth;
    String idxx;
    String idms;
    String kichThuoc;
    Float khoiLuong;
    Integer soLuong;
    Float gia;
    String moTa;
    String anh;
    Byte trangThai;
    
    
    

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(String id, String idsp, String idcl, String maGG, String idpl, String idth, String idxx, String idms, String kichThuoc, Float khoiLuong, Integer soLuong, Float gia, String moTa, String anh, Byte trangThai) {
        this.id = id;
        this.idsp = idsp;
        this.idcl = idcl;
        this.maGG = maGG;
        this.idpl = idpl;
        this.idth = idth;
        this.idxx = idxx;
        this.idms = idms;
        this.kichThuoc = kichThuoc;
        this.khoiLuong = khoiLuong;
        this.soLuong = soLuong;
        this.gia = gia;
        this.moTa = moTa;
        this.anh = anh;
        this.trangThai = trangThai;
    }

    public String getMaGG() {
        return maGG;
    }

    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getIdcl() {
        return idcl;
    }

    public void setIdcl(String idcl) {
        this.idcl = idcl;
    }

    public String getIdpl() {
        return idpl;
    }

    public void setIdpl(String idpl) {
        this.idpl = idpl;
    }

    public String getIdth() {
        return idth;
    }

    public void setIdth(String idth) {
        this.idth = idth;
    }

    public String getIdxx() {
        return idxx;
    }

    public void setIdxx(String idxx) {
        this.idxx = idxx;
    }

    public String getIdms() {
        return idms;
    }

    public void setIdms(String idms) {
        this.idms = idms;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public Float getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(Float khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public Byte getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Byte trangThai) {
        this.trangThai = trangThai;
    }

    
    
}
