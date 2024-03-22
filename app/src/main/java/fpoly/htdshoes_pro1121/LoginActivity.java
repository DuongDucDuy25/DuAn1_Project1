package fpoly.htdshoes_pro1121;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fpoly.htdshoes_pro1121.Dao.UserDao;
import fpoly.htdshoes_pro1121.Model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername, edPassword;
    private Button btnLogin;

    private UserDao userDao;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDao = new UserDao(this);
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        edUsername = findViewById(R.id.edUserRegister);
        edPassword = findViewById(R.id.edPassRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String username = edUsername.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        // Kiểm tra xem thông tin đăng nhập có hợp lệ không
        int loginResult = userDao.checkLogin(username, password);

        if (loginResult == 1) {
            // Lấy thông tin của người dùng từ cơ sở dữ liệu
            User user = userDao.getID(username);

            // Lưu thông tin người dùng vào SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putString("name", user.getName());
            editor.putInt("role", user.getRound());
            editor.apply();

            // Kiểm tra vai trò của người dùng và chuyển hướng đến màn hình tương ứng
            if ("admin".equals(user.getRound())) {
                // Đăng nhập thành công và là admin, chuyển hướng đến màn hình admin
                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Đăng nhập thành công và là user, chuyển hướng đến màn hình user
                Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            // Đăng nhập không thành công
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}