package fpoly.htdshoes_pro1121.fragment_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import fpoly.htdshoes_pro1121.AdapterAdmin.PhotoViewPagerAdapter;
import fpoly.htdshoes_pro1121.AdapterAdmin.TongQuanAdapter;
import fpoly.htdshoes_pro1121.KhachHangActivity;
import fpoly.htdshoes_pro1121.Model.Photo;
import fpoly.htdshoes_pro1121.R;
import me.relex.circleindicator.CircleIndicator;

public class frg_tongquan extends Fragment {
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    LinearLayout btnKhachHang;
    RecyclerView rcTongQuan;

    public frg_tongquan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frg_tongquan, container, false);
        // Ánh xạ các thành phần trong layout
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circleIndicator);
        btnKhachHang = view.findViewById(R.id.btnKhachHang);
        rcTongQuan = view.findViewById(R.id.rcTongQuan);

        List<String> defaultData = new ArrayList<>();
        defaultData.add("Chờ Xác Nhận");
        defaultData.add("Đang Vận Chuyển");
        defaultData.add("Trả Hàng");
        defaultData.add("Hủy Đơn Hàng");
        defaultData.add("Hoàn Thành Đơn Hàng");
// Khởi tạo LayoutManager và Adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcTongQuan.setLayoutManager(layoutManager);
        TongQuanAdapter adapter = new TongQuanAdapter(defaultData);
        rcTongQuan.setAdapter(adapter);
// Thêm các mục dữ liệu khác nếu cần

        btnKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), KhachHangActivity.class));
            }
        });

        ActionViewFlipper();

        return view;
    }
    private void ActionViewFlipper() {
        List<Integer> mangquangcao = new ArrayList<>();
        mangquangcao.add(R.drawable.logogiay);
        mangquangcao.add(R.drawable.fpoly);
        mangquangcao.add(R.drawable.logo1);

        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(requireActivity().getApplicationContext());
            // Thêm logic xử lý ở đây nếu cần thiết
        }

        setupViewPager();
    }

    private void setupViewPager() {
        List<Photo> photoList = new ArrayList<>();
        photoList.add(new Photo(R.drawable.img_1));
        photoList.add(new Photo(R.drawable.img_2));
        photoList.add(new Photo(R.drawable.img_3));
        photoList.add(new Photo(R.drawable.img_4));
        photoList.add(new Photo(R.drawable.img_5));

        PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter(photoList);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
    }


}