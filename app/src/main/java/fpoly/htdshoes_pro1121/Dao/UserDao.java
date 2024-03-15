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
    public UserDao(Context context){
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maAdmin",user.getMaAdmin());
        contentValues.put("hoTen",user.getName());
        contentValues.put("matKhau",user.getMatkhau());
        contentValues.put("round",user.getRound());
        return db.insert("Admin",null,contentValues);
    }
    public long updatePass(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",user.getName());
        contentValues.put("matKhau",user.getMatkhau());
        contentValues.put("round",user.getRound());
        return db.update("Admin",contentValues,"maAdmin=?",new String[]{String.valueOf(user.getMaAdmin())});
    }

    public List<User> getAll(){
        String sql = "SELECT * FROM Admin";
        return getData(sql);
    }

    public User getID(String id){
        String sql = "SELECT * FROM Admin WHERE maAdmin = ?";
        List<User> list = getData(sql,id);
        return list.get(0);
    }

    // check login
    public int checkLogin(String id , String password){
        String sql = "SELECT * FROM Admin WHERE maAdmin = ? AND matKhau = ?";
        List<User> list = getData(sql,id,password);
        if (list.size()==0){
            return -1;
        }
        return 1;
    }

    @SuppressLint("Range")
    private List<User> getData(String sql, String... selectionArgs){
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            User user = new User();
            user.setMaAdmin(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maAdmin"))));
            user.setName(cursor.getString(cursor.getColumnIndex("hoTen")));
            user.setMatkhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            user.setRound(Integer.parseInt(cursor.getString(cursor.getColumnIndex("round"))));
        }
        return list;
    }
}
