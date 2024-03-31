package fpoly.htdshoes_pro1121.fragment_admin;

import static android.content.Context.MODE_PRIVATE;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import fpoly.htdshoes_pro1121.ChangePassActivity;
import fpoly.htdshoes_pro1121.LoginActivity;
import fpoly.htdshoes_pro1121.Model.TaiKhoan;
import fpoly.htdshoes_pro1121.R;


public class frg_taikhoan extends Fragment {


    private TextView tvHoten,tvRole,tvThongKe,tvChangePass,tvGioiThieu,tvDangXuat;
    private AlertDialog logoutDialog;
    public frg_taikhoan() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_taikhoan, container, false);
        tvHoten = view.findViewById(R.id.tvTenTaiKhoan);
        tvRole = view.findViewById(R.id.tvRole);
        tvThongKe = view.findViewById(R.id.tvThongKe);
        tvChangePass = view.findViewById(R.id.tvChangePass);
        tvGioiThieu = view.findViewById(R.id.tvGioiThieu);
        tvDangXuat = view.findViewById(R.id.tvDangXuat);


        TaiKhoan taiKhoan = new TaiKhoan();
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserData", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        tvHoten.setText(name);
        int role = taiKhoan.getRole();
        if (role == 0) {
            tvRole.setText("Admin");
        } else if (role == 1) {
            tvRole.setText("User");
        }
        tvDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });
        tvChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), ChangePassActivity.class));
            }
        });

        return view;
    }
    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Đăng xuất và chuyển đến LoginActivity
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
                requireActivity().finish(); // Đóng Fragment hiện tại sau khi chuyển đến LoginActivity
            }
        });
        builder.setNegativeButton("No", null);
        logoutDialog = builder.create();
        logoutDialog.show();
    }
}