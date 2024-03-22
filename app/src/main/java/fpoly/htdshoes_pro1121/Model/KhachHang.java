package fpoly.htdshoes_pro1121.Model;

public class KhachHang {
    private int maKH;
    private String tenKH;
    private String diaChi;
    private String sdt;

    public KhachHang() {
    }

    public KhachHang(int maKH, String tenKH, String diaChi, String sdt) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public KhachHang(String tenKH, String diaChi, String sdt) {
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
