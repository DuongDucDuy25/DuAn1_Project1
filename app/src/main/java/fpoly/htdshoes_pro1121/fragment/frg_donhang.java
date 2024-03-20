package fpoly.htdshoes_pro1121.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import fpoly.htdshoes_pro1121.R;


public class frg_donhang extends Fragment {
    private TableLayout mTabLayout;

    private ViewPager viewPager;


    public frg_donhang() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_donhang, container, false);
        mTabLayout = view.findViewById(R.id.TabLayout);
        viewPager = view.findViewById(R.id.viewpager);
        return view;
    }
}