package fpoly.htdshoes_pro1121.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.htdshoes_pro1121.Database.DBHelper;
import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.Model.SanPham;

public class DonHangDao {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DonHangDao(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<DonHang> getlistdata() {
        ArrayList<DonHang> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM DonHang ORDER BY trangThai DESC", null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    DonHang donHang = new DonHang(cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getInt(3),
                            cursor.getInt(4),
                            cursor.getInt(5),
                            cursor.getInt(6));
                    list.add(donHang);
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
        return list;
    }
    public boolean addDonHang(DonHang donHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date",donHang.getDate());
        contentValues.put("soDonHang",donHang.getSoDonHang());
        contentValues.put("donGia",donHang.getDonGia());
        contentValues.put("trangThai",donHang.getTrangThai());
        long check = sqLiteDatabase.insert("DonHang",null,contentValues);
        if (check==-1){
            return false;
        }else {
            return true;
        }
    }
    public List<DonHang> getDonHangBySanPham(int maSanPham) {
        List<DonHang> donHangList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM DonHang WHERE maSanPham = " + maSanPham;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                DonHang donHang = new DonHang();
                donHang.setMaDH(cursor.getInt(0));
                donHang.setDate(cursor.getString(1));
                donHang.setSoDonHang(cursor.getInt(2));
                donHang.setDonGia(cursor.getInt(3));
                donHang.setMaSanPham(cursor.getInt(4));
                donHang.setTrangThai(cursor.getInt(5));
                donHangList.add(donHang);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return donHangList;
    }
    public boolean updateDonHang(DonHang donHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDonHang",donHang.getMaDH());
        values.put("date",donHang.getDate());
        values.put("soDonHang",donHang.getSoDonHang());
        values.put("donGia",donHang.getDonGia());
        values.put("trangThai",donHang.getTrangThai());

        int check = sqLiteDatabase.update("DonHang",values,"maDonHang = ?", new String[]{String.valueOf(donHang.getMaDH())});
        if (check<=0){
            return false;
        }
        return true;
    }
    public String getTenSanPhamByDonHang(int maDonHang) {
        String tenSanPham = "";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT SanPham.tenSanPham " +
                "FROM DonHang " +
                "INNER JOIN SanPham ON DonHang.maSanPham = SanPham.maSanPham " +
                "WHERE DonHang.maDonHang = " + maDonHang;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            tenSanPham = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return tenSanPham;
    }
    public boolean DeleteDonHang(int maDonHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("DonHang","maDonHang = ?", new String[]{String.valueOf(maDonHang)});
        if (check<=0){
            return false;
        }
        return true;
    }
    public int getDoanhthu(String ngaybatdau, String ngayketthuc) {
        ngaybatdau = ngaybatdau.replace("/","");
        ngayketthuc = ngayketthuc.replace("/","");

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT SUM(tienthue) FROM DonHang WHERE substr(ngay,7,4)||substr (ngay,4,2)|| substr (ngay,1,2) BETWEEN ? AND ?", new String[]{ngaybatdau, ngayketthuc});
        int doanhthu = 0;
        if (cursor.moveToFirst()) {
            doanhthu = cursor.getInt(0);
        }
        cursor.close();
        return doanhthu;
    }


}
