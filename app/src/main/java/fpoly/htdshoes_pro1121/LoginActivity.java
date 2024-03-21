package fpoly.htdshoes_pro1121;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fpoly.htdshoes_pro1121.Dao.TaiKhoanDao;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername, edPassword;
    private TextView tvDangKy;
    private Button btnLogin;

    private TaiKhoanDao taiKhoanDao;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        taiKhoanDao = new TaiKhoanDao(this);
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        tvDangKy = findViewById(R.id.tvRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                loginUser(username, password);
            }
        });

        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginUser(String username, String password) {
        int result = taiKhoanDao.checkLogin(username, password);

        if (result != -1) {
            // Lưu thông tin người dùng vào SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putInt("role", result); // Lưu vai trò dưới dạng int
            editor.apply();

            // Chuyển hướng đến màn hình phù hợp dựa trên vai trò của người dùng
            if (result == 0) { // Nếu là admin
                startActivity(new Intent(LoginActivity.this, manhinhchinh_bottom.class));
            } else { // Nếu là user
                startActivity(new Intent(LoginActivity.this, manhinhchinh_user.class));
            }
            finish(); // Kết thúc hoạt động sau khi chuyển hướng đến màn hình tiếp theo
        } else {
            // Sai tên người dùng hoặc mật khẩu
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

}
