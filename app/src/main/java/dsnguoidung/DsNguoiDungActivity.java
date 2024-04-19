package dsnguoidung;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
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
        adapter.setOnUserDeleteListener(new DanhSachNguoiDungAdapter.OnUserDeleteListener() {
            @Override
            public void onUserDelete(int position) {
                deleteUser(position);
            }
        });
    }

    private void getDanhSachNguoiDung() {
        databaseHandler = new DatabaseHandler(this);
        list = new ArrayList<>();
        list = databaseHandler.getAllUser();
        adapter = new DanhSachNguoiDungAdapter(list);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);
    }
    private void deleteUser(int position) {
        UserData deletedUser = list.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa")
                .setMessage("Bạn có muốn xóa người dùng này không?")
                .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Thực hiện xóa người dùng từ CSDL
                        databaseHandler.removeNguoiDung(deletedUser.getId());

                        // Xóa người dùng khỏi danh sách hiển thị
                        adapter.removeUser(position);

                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}


