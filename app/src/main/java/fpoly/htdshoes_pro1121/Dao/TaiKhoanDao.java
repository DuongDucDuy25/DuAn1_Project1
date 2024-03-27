package fpoly.htdshoes_pro1121.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.htdshoes_pro1121.Database.DBHelper;
import fpoly.htdshoes_pro1121.Model.TaiKhoan;

public class TaiKhoanDao {
    private SQLiteDatabase db;

    public TaiKhoanDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(TaiKhoan taiKhoan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", taiKhoan.getName());
        contentValues.put("matKhau", taiKhoan.getMatkhau());
        contentValues.put("role", taiKhoan.getRole());
        return db.insert("Admin", null, contentValues);
    }

    public long updatePass(TaiKhoan taiKhoan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", taiKhoan.getName());
        contentValues.put("matKhau", taiKhoan.getMatkhau());
        contentValues.put("role", taiKhoan.getRole());
        return db.update("Admin", contentValues, "maAdmin=?", new String[]{String.valueOf(taiKhoan.getMaAdmin())});
    }

    public TaiKhoan getUser(String username, String password) {
        String sql = "SELECT * FROM Admin WHERE hoTen = ? AND matKhau = ?";
        List<TaiKhoan> list = getData(sql, username, password);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    // check login
    // check login
    public int checkLogin(String username, String password) {
        TaiKhoan taiKhoan = getUser(username, password);
        return taiKhoan != null ? taiKhoan.getRole() : -1; // Trả về vai trò của người dùng
    }

    @SuppressLint("Range")
    private List<TaiKhoan> getData(String sql, String... selectionArgs) {
        List<TaiKhoan> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setMaAdmin(cursor.getInt(cursor.getColumnIndex("maAdmin")));
            taiKhoan.setName(cursor.getString(cursor.getColumnIndex("hoTen")));
            taiKhoan.setMatkhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            taiKhoan.setRole(cursor.getInt(cursor.getColumnIndex("role")));

            // Thêm user vào danh sách
            list.add(taiKhoan);
        }
        cursor.close(); // Đóng con trỏ sau khi sử dụng xong
        return list;
    }
}
