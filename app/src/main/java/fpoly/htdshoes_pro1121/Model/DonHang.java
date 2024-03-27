package fpoly.htdshoes_pro1121.Model;

public class DonHang {
    private int maDH;
    private String date;
    private int soDonHang;
    private int donGia;
    private int trangThai;

    public DonHang(int maDH, String date, int soDonHang, int donGia, int trangThai) {
        this.maDH = maDH;
        this.date = date;
        this.soDonHang = soDonHang;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public DonHang(String date, int soDonHang, int donGia, int trangThai) {
        this.date = date;
        this.soDonHang = soDonHang;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public DonHang() {
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSoDonHang() {
        return soDonHang;
    }

    public void setSoDonHang(int soDonHang) {
        this.soDonHang = soDonHang;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
