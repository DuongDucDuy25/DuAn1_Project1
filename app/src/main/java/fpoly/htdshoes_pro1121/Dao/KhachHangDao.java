package fpoly.htdshoes_pro1121.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import fpoly.htdshoes_pro1121.Database.DBHelper;
import fpoly.htdshoes_pro1121.Model.KhachHang;

public class KhachHangDao {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

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
        ArrayList<KhachHang> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM KhachHang", null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    list.add(new KhachHang(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)));
                }while (cursor.moveToNext());

            }
        }catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }
        return list;
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

    public boolean deleteKhachHang(int maKH) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("KhachHang","maKH = ?", new String[]{String.valueOf(maKH)});
        if (check<=0){
            return false;
        }
        return true;
    }
}

