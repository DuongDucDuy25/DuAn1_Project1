package fpoly.htdshoes_pro1121.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import fpoly.htdshoes_pro1121.Adapter.PhotoViewPagerAdapter;
import fpoly.htdshoes_pro1121.Model.Photo;
import fpoly.htdshoes_pro1121.R;

public class frg_tongquan extends Fragment {
    ViewPager viewPager;
    CircleIndicator circleIndicator;

    public frg_tongquan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
<<<<<<< HEAD
        View view = inflater.inflate(R.layout.fragment_frg_tongquan, container, false);

        // Ánh xạ các thành phần trong layout
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circleIndicator);
=======
         View view=  inflater.inflate(R.layout.fragment_frg_tongquan, container, false);
         //ánh sạ



        ActionViewFlipper();

         return view;
    }
    private void ActionViewFlipper() {
        List<Integer> mangquangcao= new ArrayList<>();
        mangquangcao.add(R.drawable.logogiay);
        mangquangcao.add(R.drawable.fpoly);
        mangquangcao.add(R.drawable.logo1);
        for (int i = 0; i <mangquangcao.size() ; i++) {
            ImageView imageView = new ImageView(requireActivity().getApplicationContext());


        }
>>>>>>> 5708aa47eb2f398b702a7e5951b23918d3babb69

        setupViewPager();

        return view;
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