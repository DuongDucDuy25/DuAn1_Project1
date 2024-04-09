package fpoly.htdshoes_pro1121.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.Database.DBHelper;
import fpoly.htdshoes_pro1121.Model.ChiTietDonHang;
import fpoly.htdshoes_pro1121.Model.KhachHang;
import fpoly.htdshoes_pro1121.Model.SanPham;

public class ChiTietDonHangDao {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public ChiTietDonHangDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Phương thức truy vấn để lấy tên khách hàng từ mã khách hàng
    public ArrayList<ChiTietDonHang> getlistdata() {
        ArrayList<ChiTietDonHang> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor = null; // Initialize cursor
        try {
            cursor = database.rawQuery("SELECT * FROM ChiTietDonHang", null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    list.add(new ChiTietDonHang(cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getInt(3),
                            cursor.getInt(4),
                            cursor.getInt(5)));
                }
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close(); // Close the cursor if it's not null
            }
        }
        return list;
    }
    public String getTenKhachHang(int maKhachHang) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT tenKhachHang FROM KhachHang WHERE maKhachHang = " + maKhachHang;
        Cursor cursor = db.rawQuery(query, null);
        String tenKhachHang = "";
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("tenKhachHang");
            if (columnIndex >= 0) {
                tenKhachHang = cursor.getString(columnIndex);
            } else {
                // Xử lý trường hợp tên cột không tồn tại
            }
        }
        cursor.close();
        db.close();
        return tenKhachHang;
    }

    // Phương thức truy vấn để lấy tên sản phẩm từ mã sản phẩm
    public String getTenSanPham(int maSanPham) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT tenSanPham FROM SanPham WHERE maSanPham = " + maSanPham;
        Cursor cursor = db.rawQuery(query, null);
        String tenSanPham = "";
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("tenSanPham");
            if (columnIndex >= 0) {
                tenSanPham = cursor.getString(columnIndex);
            } else {
                // Xử lý trường hợp tên cột không tồn tại
            }
        }
        cursor.close();
        db.close();
        return tenSanPham;
    }

    // Phương thức truy vấn để lấy đối tượng khách hàng từ mã khách hàng
    public KhachHang getKhachHang(int maKhachHang) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM KhachHang WHERE maKhachHang = " + maKhachHang;
        Cursor cursor = db.rawQuery(query, null);
        KhachHang khachHang = null;
        if (cursor.moveToFirst()) {
            int columnIndexTen = cursor.getColumnIndex("tenKhachHang");
            String tenKhachHang = cursor.getString(columnIndexTen);
            // Lấy thông tin khác của khách hàng nếu cần

            khachHang = new KhachHang(maKhachHang, tenKhachHang);
            // Gán các thông tin khác của khách hàng vào đối tượng khachHang
        }
        cursor.close();
        db.close();
        return khachHang;
    }

    // Phương thức truy vấn để lấy đối tượng sản phẩm từ mã sản phẩm
    public SanPham getSanPham(int maSanPham) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM SanPham WHERE maSanPham = " + maSanPham;
        Cursor cursor = db.rawQuery(query, null);
        SanPham sanPham = null;
        if (cursor.moveToFirst()) {
            int columnIndexTen = cursor.getColumnIndex("tenSanPham");
            String tenSanPham = cursor.getString(columnIndexTen);
            // Lấy thông tin khác của sản phẩm nếu cần

            sanPham = new SanPham(maSanPham, tenSanPham);
            // Gán các thông tin khác của sản phẩm vào đối tượng sanPham
        }
        cursor.close();
        db.close();
        return sanPham;
    }
}