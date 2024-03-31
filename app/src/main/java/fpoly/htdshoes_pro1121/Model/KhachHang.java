package fpoly.htdshoes_pro1121.Model;

public class KhachHang {
    private int maKH;
    private String hoTen;
    private String diaChi;
    private String sdt;

    public KhachHang() {
    }

    public KhachHang(int maKH, String hoTen, String diaChi, String sdt) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }
    public KhachHang(String hoTen, String diaChi, String sdt) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public KhachHang(int maKH, String hoTen) {
        this.maKH = maKH;
        this.hoTen = hoTen;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
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
