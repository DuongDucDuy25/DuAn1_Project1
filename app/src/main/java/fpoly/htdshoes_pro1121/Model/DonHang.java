package fpoly.htdshoes_pro1121.Model;

public class DonHang {
    private int maDonHang;
    private String date;
    private int soDonHang;
    private int donGia;
    private int trangthai;

    public DonHang() {
    }

    public DonHang(int maDonHang, String date, int soDonHang, int donGia, int trangthai) {
        this.maDonHang = maDonHang;
        this.date = date;
        this.soDonHang = soDonHang;
        this.donGia = donGia;
        this.trangthai = trangthai;
    }

    public DonHang(String date, int soDonHang, int donGia, int trangthai) {
        this.date = date;
        this.soDonHang = soDonHang;
        this.donGia = donGia;
        this.trangthai = trangthai;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
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

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
