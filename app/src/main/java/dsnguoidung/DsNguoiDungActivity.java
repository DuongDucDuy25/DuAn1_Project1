package dsnguoidung;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;
import java.util.List;

import database.DatabaseHandler;
import fpoly.htdshoes_pro1121.databinding.ActivityDsNguoiDungBinding;
import model.UserData;

public class DsNguoiDungActivity extends AppCompatActivity {

    private ActivityDsNguoiDungBinding binding;
    List<UserData> list = new ArrayList<>();
    private DanhSachNguoiDungAdapter adapter;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDsNguoiDungBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDanhSachNguoiDung();
    }

    private void getDanhSachNguoiDung() {
        databaseHandler = new DatabaseHandler(this);
        list = new ArrayList<>();
        list = databaseHandler.getAllUser();
        adapter = new DanhSachNguoiDungAdapter(list);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);
    }
}


