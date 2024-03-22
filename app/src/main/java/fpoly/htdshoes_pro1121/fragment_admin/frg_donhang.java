package fpoly.htdshoes_pro1121.fragment_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import fpoly.htdshoes_pro1121.AdapterAdmin.DonHangAdminAdapter;
import fpoly.htdshoes_pro1121.AdapterAdmin.DonHangTabLayoutAdapter;
import fpoly.htdshoes_pro1121.Dao.DonHangDao;
import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.R;;

public class frg_donhang extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private DonHangTabLayoutAdapter donHangTabLayoutAdapter;
    private RecyclerView recyclerView;
    private DonHangAdminAdapter donHangAdapter;

    public frg_donhang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_donhang, container, false);
        mTabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        recyclerView = view.findViewById(R.id.recyclerView);

        // Khởi tạo adapter và thiết lập ViewPager
        donHangTabLayoutAdapter = new DonHangTabLayoutAdapter(getChildFragmentManager());
        viewPager.setAdapter(donHangTabLayoutAdapter);
        mTabLayout.setupWithViewPager(viewPager);

        // Khởi tạo adapter cho RecyclerView và thiết lập layout manager
        donHangAdapter = new DonHangAdminAdapter(new ArrayList<>());
        recyclerView.setAdapter(donHangAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Lấy danh sách đơn hàng tương ứng với mỗi trạng thái và cập nhật adapter
        getDonHangByTrangThai(1); // Ví dụ: Trạng thái chờ xác nhận
        getDonHangByTrangThai(2);
        getDonHangByTrangThai(3);
        getDonHangByTrangThai(4);
        getDonHangByTrangThai(5);

        return view;
    }

    // Hàm giả lập lấy danh sách đơn hàng theo trạng thái
    private void getDonHangByTrangThai(int trangThai) {
        DonHangDao donHangDao = new DonHangDao(getActivity());
        List<DonHang> donHangList = donHangDao.getDonHangByTrangThai(trangThai);
        if (donHangList != null) {
            donHangAdapter.updateDonHangList(donHangList);
        }
    }
}