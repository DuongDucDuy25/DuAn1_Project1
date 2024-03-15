package fpoly.htdshoes_pro1121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import fpoly.htdshoes_pro1121.Dao.UserDao;
import fpoly.htdshoes_pro1121.Model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText edUserRegister, edPassRegister, edRePassRegister, edName;
    private Spinner spinnerUserRole;
    private Button btnRegister;

    private UserDao userDao;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userDao = new UserDao(this);
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
        String name = edName.getText().toString().trim();
        String role = spinnerUserRole.getSelectedItem().toString().trim();

        if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(rePassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new User object
        User user = new User();
        user.setMaAdmin(0); // Set the ID as 0 or handle it according to your logic
        user.setName(name);
        user.setMatkhau(password);
        user.setRound(0); // Set the round as 0 or handle it according to your logic

        // Insert the user into the database
        long result = userDao.insert(user);

        if (result != -1) {
            // Lưu thông tin người dùng vào SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putString("name", name);
            editor.putString("role", role);
            editor.apply();

            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
            finish(); // Finish the activity or navigate to the login screen
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

}