package fpoly.htdshoes_pro1121.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpoly.htdshoes_pro1121.R;


public class frg_tongquan extends Fragment {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;

    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;

    public frg_tongquan() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=  inflater.inflate(R.layout.fragment_frg_tongquan, container, false);
         //ánh sạ
        viewFlipper= view.findViewById(R.id.viewfliper);
        recyclerViewmanhinhchinh= view.findViewById(R.id.recycleview);

        drawerLayout=view.findViewById(R.id.drawerlatout);


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


    }


    private void Anhsa() {



    }
}