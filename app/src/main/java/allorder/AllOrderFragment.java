package allorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import database.DatabaseHandler;
import detail.OrderHistoryDetailActivity;
import fpoly.htdshoes_pro1121.databinding.FragmentAllOrderBinding;
import history.OrderHistoryAdapter;
import model.OrderHistoryModel;


public class AllOrderFragment extends Fragment implements OrderHistoryAdapter.OnItemClickListener {

    private FragmentAllOrderBinding binding;

    private OrderHistoryAdapter adapter;
    private List<OrderHistoryModel> list = new ArrayList<>();
    private DatabaseHandler databaseHandler;
    String[] country = {"Tất cả", "Chờ xác nhận", "Đã xác nhận", "Đang vận chuyển", "Đã hoàn thành"};
    public AllOrderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getListOrder();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    // lọc đơn hàng theo trạng thái được chọn
    private void getOrder(int position) {
        List<OrderHistoryModel> listFilter = new ArrayList<>();
        list.forEach(new Consumer<OrderHistoryModel>() {
            @Override
            public void accept(OrderHistoryModel orderHistoryModel) {
                if (orderHistoryModel.getTrangthaidonhang() == position) {
                    listFilter.add(orderHistoryModel);
                }
                adapter.replace(listFilter);
            }
        });
    }

    private void getListOrder() {
        databaseHandler = new DatabaseHandler(getContext());
        list = new ArrayList<>();
        list = databaseHandler.getAllOrder();
        adapter = new OrderHistoryAdapter(list);
        adapter.setOnItemClickListener(this);
        binding.recyclerView.setAdapter(adapter);


        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    list = databaseHandler.getAllOrder();
                    adapter.replace(list);
                } else {
                    getOrder(position - 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(aa);
    }

    // chuyển đổi sang chi tiết đơn hàng
    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getContext(), OrderHistoryDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", list.get(position).getId());
        bundle.putInt("trangthaidonhang", list.get(position).getTrangthaidonhang());
        bundle.putSerializable("data", (Serializable) list.get(position).getFoods());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
