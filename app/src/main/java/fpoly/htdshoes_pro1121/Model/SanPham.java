package fpoly.htdshoes_pro1121.Model;

public class SanPham {
    private int maSanPham;
    private String tenSanPham;
    private int maCTSP;
    private int maTL;

    private int giaTien;

    private int soLuong;

    public SanPham(int maSanPham, String tenSanPham, int maCTSP, int maTL, int giaTien, int soLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maCTSP = maCTSP;
        this.maTL = maTL;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
    }

    public SanPham(String tenSanPham, int maCTSP, int maTL, int giaTien, int soLuong) {
        this.tenSanPham = tenSanPham;
        this.maCTSP = maCTSP;
        this.maTL = maTL;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
    }

    public SanPham() {
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(int maCTSP) {
        this.maCTSP = maCTSP;
    }

    public int getMaTL() {
        return maTL;
    }

    public void setMaTL(int maTL) {
        this.maTL = maTL;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }
}
