package fpoly.htdshoes_pro1121.fragment_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import fpoly.htdshoes_pro1121.AdapterAdmin.DonHangTabLayoutAdapter;
import fpoly.htdshoes_pro1121.R;


public class frg_donhang extends Fragment {
    private TabLayout mTablayout;
    private ViewPager mviewpager;

    private DonHangTabLayoutAdapter donHangTabLayoutAdapter;


    public frg_donhang() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_donhang, container, false);
        mTablayout = view.findViewById(R.id.TabLayout);
        mviewpager = view.findViewById(R.id.viewpager);

        donHangTabLayoutAdapter = new DonHangTabLayoutAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewpager.setAdapter(donHangTabLayoutAdapter);

        mTablayout.setupWithViewPager(mviewpager);
        return view;
    }
}