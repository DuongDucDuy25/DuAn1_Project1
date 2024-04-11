package fpoly.htdshoes_pro1121.d;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dsnguoidung.DsNguoiDungActivity;
import fpoly.htdshoes_pro1121.databinding.FragmentQuanLiBinding;
import quanlitheloai.QuanLiTheLoaiActivity;
import thongke.ThongKeDoanhThuActivity;


public class QuanLiFragment extends Fragment {

    private FragmentQuanLiBinding binding;

    public QuanLiFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuanLiBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.quanlinguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DsNguoiDungActivity.class);
                startActivity(intent);
            }
        });
        binding.quanlitheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuanLiTheLoaiActivity.class);
                startActivity(intent);
            }
        });
        binding.thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThongKeDoanhThuActivity.class);
                startActivity(intent);
            }
        });
    }
}