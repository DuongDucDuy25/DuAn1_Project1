package fpoly.htdshoes_pro1121.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.Database.DBHelper;
import fpoly.htdshoes_pro1121.Model.KhachHang;
import fpoly.htdshoes_pro1121.Model.SanPham;

public class KhachHangDao {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public KhachHangDao(Context context){
        dbHelper = new DBHelper(context);
    }
    public ArrayList<KhachHang> getlistdata(){
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

    public boolean addKhachHang(KhachHang khachHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",khachHang.getTenKH());
        contentValues.put("diaChi",khachHang.getDiaChi());
        contentValues.put("sdt",khachHang.getSdt());
        long check = sqLiteDatabase.insert("KhachHang",null,contentValues);
        if (check==-1){
            return false;
        }else {
            return true;
        }
    }

}
