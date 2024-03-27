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
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getInt(3),
                            cursor.getInt(4));
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
    public boolean DeleteDonHang(int maDonHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("DonHang","maDonHang = ?", new String[]{String.valueOf(maDonHang)});
        if (check<=0){
            return false;
        }
        return true;
    }

}
