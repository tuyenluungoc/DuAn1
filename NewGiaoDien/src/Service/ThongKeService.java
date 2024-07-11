package Service;


import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeService {

    Connection conn = DBContext.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int getDonHangNgayHienTai() {
        String sql = """
                     SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
                     FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                     WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE()) 
                         AND DAY(HD.NgayTao) = DAY(GETDATE())
                     """;
        int soLuong = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt(1);
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDonHangNgayThanhCong() {
        String sql = """
                     SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
                     FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                     WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE()) 
                         AND DAY(HD.NgayTao) = DAY(GETDATE()) AND HD.TrangThai = 1
                     """;
        int soLuong = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt(1);
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDonHangNgayBiHuy() {
        String sql = """
                     SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
                     FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                     WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE()) 
                         AND DAY(HD.NgayTao) = DAY(GETDATE()) AND HD.TrangThai = 2
                     """;
        int soLuong = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt(1);
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getDoanhThuNgayHienTai() {
        String sql = """
                     SELECT SUM(SoLuong * DonGia) AS DoanhThuHomNay
                     FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                     WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE())
                         AND DAY(HD.NgayTao) = DAY(GETDATE()) AND HD.TrangThai = 1
                     """;
        double doanhThu = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getInt(1);
            }
            return doanhThu;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public double getDoanhThuThangHienTai() {
        String sql = """
                     SELECT SUM(SoLuong * DonGia) AS DoanhThuThangNay
                     FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                     WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE())
                         AND HD.TrangThai = 1
                     """;
        double doanhThu = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getInt(1);
            }
            return doanhThu;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int getDonHangThangHienTaiThanhCong() {
        String sql = """
                     SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
                         FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                         WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE()) 
                              AND HD.TrangThai = 1
                     """;
        int soLuong = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt(1);
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDonHangThangHienTaiBiHuy() {
        String sql = """
                     SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
                         FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                         WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE()) 
                              AND HD.TrangThai = 2
                     """;
        int soLuong = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt(1);
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public double getDoanhThuNamHienTai() {
        String sql = """
                     SELECT SUM(SoLuong * DonGia) AS DoanhThuThangNay
                         FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                         WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND HD.TrangThai = 1
                     """;
        double doanhThu = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getInt(1);
            }
            return doanhThu;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int getDonHangNamHienTaiThanhCong() {
        String sql = """
                     SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
                              FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                              WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND HD.TrangThai = 1
                     """;
        int soLuong = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt(1);
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDonHangNamHienTaiBiHuy() {
        String sql = """
                     SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
                              FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                              WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND HD.TrangThai = 2
                     """;
        int soLuong = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt(1);
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public List<Integer> getYear() {
        String sql = """
                    SELECT DISTINCT YEAR(NgayTao)
                    FROM HoaDon
                    ORDER BY YEAR(NgayTao) DESC
                    """;
        List<Integer> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int getSoLuongSPThang(int nam, int thang) {
        String sql = """
                     SELECT SUM(SoLuong) AS TongSoLuong
                     FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                     WHERE  YEAR(HD.NgayTao) = ? AND MONTH(HD.NgayTao) = ?
                           AND HD.TrangThai = 1
                     """;
        int soLuong = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, nam);
            ps.setObject(2, thang);
            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt(1);
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getDoanhThuThang(int nam, int thang) {
        String sql = """
                     SELECT SUM(SoLuong * DonGia) AS DoanhThuThang
                     FROM HoaDonChiTiet HDCT JOIN HOADON HD ON HDCT.IDHD = HD.IDHD 
                     WHERE YEAR(hd.NGAYTAO) = ? AND MONTH(hd.NGAYTAO) = ?
                          AND hd.TrangThai = 1
                     """;
        double doanhThu = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, nam);
            ps.setObject(2, thang);
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getInt(1);
            }
            return doanhThu;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getTongGiaGiamThang(int nam, int thang) {
        String sql = """
                     SELECT SUM(GG.GiaTri * SPCT.Gia / 100) TongSoTienGiam
                     FROM GiamGia GG JOIN SanPhamChiTiet SPCT ON GG.MaGG = SPCT.MaGG
                     				JOIN HoaDonChiTiet HDCT ON SPCT.ID = HDCT.IDSPCT
                     				JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
                     WHERE YEAR(hd.NGAYTAO) = ? AND MONTH(hd.NGAYTAO) = ?
                          AND hd.TrangThai = 1
                     """;
        double tienGiam = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, nam);
            ps.setObject(2, thang);
            rs = ps.executeQuery();
            while (rs.next()) {
                tienGiam = rs.getInt(1);
            }
            return tienGiam;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
