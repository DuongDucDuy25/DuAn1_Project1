package fpoly.htdshoes_pro1121.d;



import static fpoly.htdshoes_pro1121.d.SharedPref.USER_DATA;
import static fpoly.htdshoes_pro1121.d.ShowMessageHelper.showMessage;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.Objects;

import database.DatabaseHandler;
import fpoly.htdshoes_pro1121.databinding.ActivityLoginBinding;
import model.UserData;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHandler = new DatabaseHandler(this);
        setOnClick();
        SharedPref.init(getApplicationContext());
    }

    private void setOnClick() {
        binding.btnLogin.setOnClickListener(view -> {
            String userName = Objects.requireNonNull(binding.edtUserName.getText()).toString();
            String password = Objects.requireNonNull(binding.edtPassword.getText()).toString();
            if (databaseHandler.login(userName, password)) {
                UserData userData = databaseHandler.getUserData(userName, password);
                SharedPref.write(USER_DATA, new Gson().toJson(userData));
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                this.finish();

            } else {
                showMessage(this, "Tên tài khoản hoặc mật khẩu không chính xác");
            }
        });
        binding.btnSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

    }
}