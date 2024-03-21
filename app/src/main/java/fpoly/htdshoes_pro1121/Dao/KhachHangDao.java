package fpoly.htdshoes_pro1121.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import fpoly.htdshoes_pro1121.Database.DBHelper;
import fpoly.htdshoes_pro1121.Model.KhachHang;

public class KhachHangDao {
    private DBHelper dbHelper;

    public KhachHangDao(Context context) {
        dbHelper = new DBHelper(context);
    }


    // Thêm khách hàng vào cơ sở dữ liệu
    public long insertKhachHang(KhachHang khachHang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen", khachHang.getHoTen());
        values.put("diachi", khachHang.getDiaChi());
        values.put("sdt", khachHang.getSdt());
        long result = db.insert("KhachHang", null, values);
        db.close();
        return result;
    }
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> khachHangList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM KhachHang", null);
        if (cursor.moveToFirst()) {
            do {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(cursor.getInt(cursor.getColumnIndex("maKH")));
                khachHang.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
                khachHang.setDiaChi(cursor.getString(cursor.getColumnIndex("diachi")));
                khachHang.setSdt(cursor.getString(cursor.getColumnIndex("sdt")));
                khachHangList.add(khachHang);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return khachHangList;
    }

    public int updateKhachHang(KhachHang khachHang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen", khachHang.getHoTen());
        values.put("diachi", khachHang.getDiaChi());
        values.put("sdt", khachHang.getSdt());
        return db.update("KhachHang", values,
                "maKH = ?", new String[]{String.valueOf(khachHang.getMaKH())});
    }

    public void deleteKhachHang(int maKH) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("KhachHang", "maKH = ?", new String[]{String.valueOf(maKH)});
        db.close();
    }
}

