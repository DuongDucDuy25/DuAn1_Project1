package fpoly.htdshoes_pro1121.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.htdshoes_pro1121.Database.DBHelper;
import fpoly.htdshoes_pro1121.Model.User;

public class UserDao {
    private SQLiteDatabase db;

    public UserDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", user.getName());
        contentValues.put("matKhau", user.getMatkhau());
        contentValues.put("round", user.getRole());
        return db.insert("Admin", null, contentValues);
    }

    public long updatePass(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", user.getName());
        contentValues.put("matKhau", user.getMatkhau());
        contentValues.put("round", user.getRole());
        return db.update("Admin", contentValues, "maAdmin=?", new String[]{String.valueOf(user.getMaAdmin())});
    }

    public User getUser(String username, String password) {
        String sql = "SELECT * FROM Admin WHERE hoTen = ? AND matKhau = ?";
        List<User> list = getData(sql, username, password);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    // check login
    // check login
    public int checkLogin(String username, String password) {
        User user = getUser(username, password);
        return user != null ? user.getRole() : -1; // Trả về vai trò của người dùng
    }

    @SuppressLint("Range")
    private List<User> getData(String sql, String... selectionArgs) {
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setMaAdmin(cursor.getInt(cursor.getColumnIndex("maAdmin")));
            user.setName(cursor.getString(cursor.getColumnIndex("hoTen")));
            user.setMatkhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            user.setRole(cursor.getInt(cursor.getColumnIndex("round")));

            // Thêm user vào danh sách
            list.add(user);
        }
        cursor.close(); // Đóng con trỏ sau khi sử dụng xong
        return list;
    }
}
