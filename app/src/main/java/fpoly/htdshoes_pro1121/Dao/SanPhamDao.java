package fpoly.htdshoes_pro1121.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.Database.DBHelper;
import fpoly.htdshoes_pro1121.Model.SanPham;

public class SanPhamDao {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public SanPhamDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<SanPham> getlistdata() {
        ArrayList<SanPham> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor = null; // Initialize cursor
        try {
            cursor = database.rawQuery("SELECT * FROM SanPham", null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    list.add(new SanPham(cursor.getInt(0),
                            cursor.getString(1),
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

    public ArrayList<SanPham> getListDataByCategoryId(int categoryId) {
        ArrayList<SanPham> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor = null; // Initialize cursor
        try {
            // Query to select products based on category ID
            cursor = database.rawQuery("SELECT * FROM SanPham WHERE maTL = ?", new String[]{String.valueOf(categoryId)});
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    list.add(new SanPham(cursor.getInt(0),
                            cursor.getString(1),
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

    public boolean addSanPham(SanPham sanPham) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSanPham", sanPham.getTenSanPham());
        contentValues.put("maCTSP", sanPham.getMaCTSP());
        contentValues.put("maTL", sanPham.getMaTL());
        contentValues.put("soLuong", sanPham.getSoLuong());
        contentValues.put("giaTien", sanPham.getGiaTien());
        long check = sqLiteDatabase.insert("SanPham", null, contentValues);
        return check != -1;
    }

    public boolean updateSanPham(SanPham sanPham) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maSanPham", sanPham.getMaSanPham());
        values.put("tenSanPham", sanPham.getTenSanPham());
        values.put("maCTSP", sanPham.getMaCTSP());
        values.put("maTL", sanPham.getMaTL());
        values.put("soLuong", sanPham.getSoLuong());
        values.put("giaTien", sanPham.getGiaTien());
        int check = sqLiteDatabase.update("SanPham", values, "maSanPham = ?", new String[]{String.valueOf(sanPham.getMaSanPham())});
        return check > 0;
    }

    public boolean DeleteSanPham(int maSanPham) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("SanPham", "maSanPham = ?", new String[]{String.valueOf(maSanPham)});
        return check > 0;
    }
}
