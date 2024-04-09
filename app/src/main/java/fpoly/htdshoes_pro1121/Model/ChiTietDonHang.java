package fpoly.htdshoes_pro1121.Model;

public class ChiTietDonHang {
    private int maCTDH;
    private int maKH;
    private int maDH;
    private int SoLuong;
    private int maSanPham;
    private int status;

    public ChiTietDonHang(int maCTDH, int maKH, int maDH, int soLuong, int maSanPham, int status) {
        this.maCTDH = maCTDH;
        this.maKH = maKH;
        this.maDH = maDH;
        SoLuong = soLuong;
        this.maSanPham = maSanPham;
        this.status = status;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public ChiTietDonHang() {
    }

    public int getMaCTDH() {
        return maCTDH;
    }

    public void setMaCTDH(int maCTDH) {
        this.maCTDH = maCTDH;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}