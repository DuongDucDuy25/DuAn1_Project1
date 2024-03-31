package fpoly.htdshoes_pro1121.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class DonHang implements Parcelable {
    private int maDH;
    private int maKH;
    private String date;
    private int soDonHang;
    private int donGia;
    private int maSanPham;
    private int trangThai;

    public DonHang(int maDH, int maKH, String date, int soDonHang, int donGia, int maSanPham, int trangThai) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.date = date;
        this.soDonHang = soDonHang;
        this.donGia = donGia;
        this.maSanPham = maSanPham;
        this.trangThai = trangThai;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    protected DonHang(Parcel in) {
        maDH = in.readInt();
        maKH =in.readInt();
        date = in.readString();
        soDonHang = in.readInt();
        donGia = in.readInt();
        maSanPham = in.readInt();
        trangThai = in.readInt();
    }

    public DonHang() {
    }

    public static final Creator<DonHang> CREATOR = new Creator<DonHang>() {
        @Override
        public DonHang createFromParcel(Parcel in) {
            return new DonHang(in);
        }

        @Override
        public DonHang[] newArray(int size) {
            return new DonHang[size];
        }
    };

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(maDH);
        dest.writeInt(maKH);
        dest.writeString(date);
        dest.writeInt(soDonHang);
        dest.writeInt(donGia);
        dest.writeInt(maSanPham);
        dest.writeInt(trangThai);
    }
}