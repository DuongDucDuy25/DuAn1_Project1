package fpoly.htdshoes_pro1121.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.Database.DBHelper;
import fpoly.htdshoes_pro1121.Model.SanPham;

public class SanPhamUserDao {
    private DBHelper dbHelper;

    private SQLiteDatabase database;

    public SanPhamUserDao(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<SanPham> getlistdata(){
        ArrayList<SanPham> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM SanPham", null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    list.add(new SanPham(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getInt(3),
                            cursor.getInt(4),
                            cursor.getInt(5)));
                }while (cursor.moveToNext());

            }
        }catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }
        return list;
    }
    public boolean addSanPham(SanPham sanPham){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSanPham",sanPham.getTenSanPham());
        contentValues.put("maCTSP",sanPham.getMaCTSP());
        contentValues.put("maTL",sanPham.getMaTL());
        contentValues.put("soLuong",sanPham.getSoLuong());
        contentValues.put("giaTien",sanPham.getGiaTien());
        long check = sqLiteDatabase.insert("SanPham",null,contentValues);
        if (check==-1){
            return false;
        }else {
            return true;
        }
    }

    public boolean updateSanPham(SanPham sanPham){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maSanPham",sanPham.getMaSanPham());
        values.put("tenSanPham",sanPham.getTenSanPham());
        values.put("maCTSP",sanPham.getMaCTSP());
        values.put("maTL",sanPham.getMaTL());
        values.put("soLuong",sanPham.getSoLuong());
        values.put("giaTien",sanPham.getGiaTien());

        int check = sqLiteDatabase.update("SanPham",values,"maSanPham = ?", new String[]{String.valueOf(sanPham.getMaSanPham())});
        if (check<=0){
            return false;
        }
        return true;
    }

    public boolean DeleteSanPham(int maSanPham){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("SanPham","maSanPham = ?", new String[]{String.valueOf(maSanPham)});
        if (check<=0){
            return false;
        }
        return true;
    }
}
