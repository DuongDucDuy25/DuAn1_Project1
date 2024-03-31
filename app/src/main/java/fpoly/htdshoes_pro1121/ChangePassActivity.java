package fpoly.htdshoes_pro1121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.htdshoes_pro1121.Dao.TaiKhoanDao;
import fpoly.htdshoes_pro1121.Model.TaiKhoan;

public class ChangePassActivity extends AppCompatActivity {
    EditText edPassOld, edPassChange, edRePassChange;
    Button btnChangePass, btnCancleChangePass;
    TaiKhoanDao taiKhoanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        taiKhoanDao = new TaiKhoanDao(this);
        edPassOld = findViewById(R.id.edOldPass);
        edPassChange = findViewById(R.id.edNewPass);
        edRePassChange = findViewById(R.id.edReOldPass);
        btnChangePass = findViewById(R.id.btnChangePass);
        btnCancleChangePass = findViewById(R.id.btnCancelChangePass);

        btnCancleChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPassChange.setText("");
                edRePassChange.setText("");
            }
        });

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String username = pref.getString("username", "");

                if (validate() > 0) {
                    TaiKhoan taiKhoan = taiKhoanDao.getUser(username, ""); // Lấy thông tin tài khoản dựa trên tên đăng nhập

                    if (taiKhoan != null) {
                        taiKhoan.setMatkhau(edPassChange.getText().toString()); // Cập nhật mật khẩu mới

                        if (taiKhoanDao.updatePass(taiKhoan) > 0) { // Sử dụng phương thức updatePass thay vì updateTaiKhoan
                            Toast.makeText(ChangePassActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            edPassOld.setText("");
                            edPassChange.setText("");
                            edRePassChange.setText("");
                            startActivity(new Intent(ChangePassActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(ChangePassActivity.this, "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChangePassActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public int validate() {
        int check = 1;
        if (edPassOld.getText().length() == 0 || edPassChange.getText().length() == 0 || edRePassChange.getText().length() == 0) {
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences pref = getSharedPreferences("UserData", Context.MODE_PRIVATE);
            String passwordOld = pref.getString("password", "");
            String pass = edPassChange.getText().toString();
            String rePass = edRePassChange.getText().toString();
            if (!passwordOld.equals(edPassOld.getText().toString())) {
                Toast.makeText(this, "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)) {
                Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }

        return check;
    }
}