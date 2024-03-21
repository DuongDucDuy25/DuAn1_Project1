package fpoly.htdshoes_pro1121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import fpoly.htdshoes_pro1121.Dao.TaiKhoanDao;
import fpoly.htdshoes_pro1121.Model.TaiKhoan;

public class RegisterActivity extends AppCompatActivity {
    private EditText edUserRegister, edPassRegister, edRePassRegister, edName;
    private Spinner spinnerUserRole;
    private Button btnRegister;

    private TaiKhoanDao taiKhoanDao;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        taiKhoanDao = new TaiKhoanDao(this);
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        edUserRegister = findViewById(R.id.edUserRegister);
        edPassRegister = findViewById(R.id.edPassRegister);
        edRePassRegister = findViewById(R.id.edRePassRegister);
        edName = findViewById(R.id.edName);
        spinnerUserRole = findViewById(R.id.spinnerUserRole);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = edUserRegister.getText().toString().trim();
        String password = edPassRegister.getText().toString().trim();
        String rePassword = edRePassRegister.getText().toString().trim();
        int role = spinnerUserRole.getSelectedItemPosition(); // Lấy vị trí được chọn trong spinner

        if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(rePassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra xem tên người dùng đã tồn tại chưa
        int result = taiKhoanDao.checkLogin(username, password);

        if (result == -1) {
            // Tên người dùng chưa tồn tại, tiến hành đăng ký
            TaiKhoan taiKhoan = new TaiKhoan(username, password, role);
            long insertResult = taiKhoanDao.insert(taiKhoan);

            if (insertResult != -1) {
                // Đăng ký thành công
                Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
                // Tiến hành đăng nhập ngay sau khi đăng ký
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
                finish();
            } else {
                // Đăng ký thất bại
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Tên người dùng đã tồn tại
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
        }
    }


}