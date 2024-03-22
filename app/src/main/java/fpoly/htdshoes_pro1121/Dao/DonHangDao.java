package fpoly.htdshoes_pro1121.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.Database.DBHelper;
import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.Model.User;

public class DonHangDao {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DonHangDao(Context context){
        this.dbHelper = new DBHelper(context);
    }
    public boolean insert(DonHang donHang){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maDonHang",donHang.getMaDonHang());
        contentValues.put("date",donHang.getDate());
        contentValues.put("soDonHang",donHang.getSoDonHang());
        contentValues.put("donGia",donHang.getDonGia());
        contentValues.put("status",donHang.getTrangthai());
        long check =db.insert("DonHang",null,contentValues);
        if (check==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean updatedonHang(DonHang donHang){
        ContentValues contentValues = new ContentValues();
        contentValues.put("date",donHang.getDate());
        contentValues.put("soDonHang",donHang.getSoDonHang());
        contentValues.put("donGia",donHang.getDonGia());
        contentValues.put("status",donHang.getTrangthai());
        int check = db.update("DonHang",contentValues,"maDonHang = ?", new String[]{String.valueOf(donHang.getMaDonHang())});
        if (check<=0){
            return false;
        }
        return true;
    }
    public ArrayList<DonHang> getlistdata(){
        ArrayList<DonHang> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM DonHang", null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    list.add(new DonHang(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getInt(3),
                            cursor.getInt(4)));
                }while (cursor.moveToNext());

            }
        }catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }
        return list;
    }
    public boolean DeleteDonHang(int maDonHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("DonHang","maDonHang = ?", new String[]{String.valueOf(maDonHang)});
        if (check<=0){
            return false;
        }
        return true;
    }
    public ArrayList<DonHang> getDonHangByTrangThai(int trangThai) {
        ArrayList<DonHang> donHangList = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            String[] projection = {"maDonHang", "date", "soDonHang", "donGia", "status"};
            String selection = "status = ?";
            String[] selectionArgs = {String.valueOf(trangThai)};
            cursor = db.query(
                    "DonHang",
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int maDonHang = cursor.getInt(cursor.getColumnIndexOrThrow("maDonHang"));
                    String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                    int soDonHang = cursor.getInt(cursor.getColumnIndexOrThrow("soDonHang"));
                    int donGia = cursor.getInt(cursor.getColumnIndexOrThrow("donGia"));
                    int status = cursor.getInt(cursor.getColumnIndexOrThrow("status"));

                    DonHang donHang = new DonHang(maDonHang, date, soDonHang, donGia, status);
                    donHangList.add(donHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return donHangList;
    }

}
