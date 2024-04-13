package fpoly.htdshoes_pro1121.d;



import static fpoly.htdshoes_pro1121.d.ShowMessageHelper.showMessage;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import database.DatabaseHandler;
import fpoly.htdshoes_pro1121.databinding.ActivitySignUpBinding;
import model.UserData;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private DatabaseHandler databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHandler = new DatabaseHandler(this);
        setOnClick();
    }

    private void setOnClick() {
        binding.btnSignUp.setOnClickListener(view -> {
            if (TextUtils.isEmpty(binding.edtUserName.getText())) {
                showMessage(this, "Không được để trống họ và tên");
            } else if (TextUtils.isEmpty(binding.edtFullName.getText())) {
                showMessage(this, "Không được để trống tài khoản");
            } else if (TextUtils.isEmpty(binding.edtPassword.getText())) {
                showMessage(this, "Không được để trống mật khẩu");
            } else if (TextUtils.isEmpty(binding.edtPhone.getText())) {
                showMessage(this, "Không được để trống số điện thoại");
            } else if (TextUtils.isEmpty(binding.edtAddress.getText())) {
                showMessage(this, "Không được để trống địa chỉ");
            } else {
                SignUp();
            }
        });
    }

    private void SignUp() {
        UserData user = new UserData();
        user.setRole("user");
        user.setFullName(Objects.requireNonNull(binding.edtFullName.getText()).toString());
        user.setUserName(Objects.requireNonNull(binding.edtUserName.getText()).toString());
        user.setPassword(Objects.requireNonNull(binding.edtPassword.getText()).toString());
        user.setPhone(Objects.requireNonNull(binding.edtPhone.getText()).toString());
        user.setAddress(Objects.requireNonNull(binding.edtAddress.getText()).toString());
        databaseHandler.addUser(user);
    }

}