package fpoly.htdshoes_pro1121.AdapterAdmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fpoly.htdshoes_pro1121.fragment_admin.ChoXacNhanFragment;
import fpoly.htdshoes_pro1121.fragment_admin.DangVanChuyenFragment;
import fpoly.htdshoes_pro1121.fragment_admin.HoanThanhDonFragment;
import fpoly.htdshoes_pro1121.fragment_admin.HuyDonHangFragment;
import fpoly.htdshoes_pro1121.fragment_admin.TraHangFragment;

public class DonHangTabLayoutAdapter extends FragmentStatePagerAdapter {


    public DonHangTabLayoutAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ChoXacNhanFragment();
            case 1:
                return new DangVanChuyenFragment();
            case 2:
                return new TraHangFragment();
            case 3:
                return new HuyDonHangFragment();
            case 4:
                return new HoanThanhDonFragment();
            default:
                return new HoanThanhDonFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title ="";
        switch (position){
            case 0:
                title ="Chờ Xác Nhận";
                break;
            case 1:
                title ="Đang Vận Chuyển";
                break;
            case 2:
                title ="Trả Hàng";
                break;
            case 3:
                title ="Hủy Đơn";
                break;
            case 4:
                title ="Đã Hoàn Thành";
                break;
        }
        return title;
    }
}
