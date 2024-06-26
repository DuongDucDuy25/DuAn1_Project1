package cart;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import database.DatabaseHandler;
import fpoly.htdshoes_pro1121.d.SharedPref;
import fpoly.htdshoes_pro1121.d.ShowMessageHelper;
import fpoly.htdshoes_pro1121.databinding.FragmentCartBinding;
import model.CartModel;
import model.UserData;


public class CartFragment extends Fragment implements CartAdapter.OnItemClickListener {
    private FragmentCartBinding binding;
    private UserData userData;
    private List<CartModel> list = new ArrayList<>();
    private CartAdapter adapter;
    private DatabaseHandler databaseHandler;

    int phuongthucthanhtoan = -1;

    public CartFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // lấy danh sách các mục giỏ hàng ở cơ sở dữ liêu
    private void getListCart() {
        if (userData != null) {
            list = databaseHandler.getAllCart(userData.getId());
            adapter = new CartAdapter(list);
            binding.recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(this);
            binding.recyclerView.setHasFixedSize(true);
        }
        if (list.size() > 0) {
            binding.linear.setVisibility(View.VISIBLE);
            binding.btnOrder.setVisibility(View.VISIBLE);
        } else {
            binding.linear.setVisibility(View.GONE);
            binding.btnOrder.setVisibility(View.GONE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOnClick();
        databaseHandler = new DatabaseHandler(getContext());
        userData = new Gson().fromJson(SharedPref.read(SharedPref.USER_DATA, ""), UserData.class);
        binding.diachinhanhang.setText("Địa chỉ nhận hàng: " + userData.getAddress());
        binding.sodienthoai.setText("Số điện thoại: " + userData.getPhone());
        binding.nguoinhan.setText("Họ tên: " + userData.getFullName());
        list.clear();
        getListCart();
    }

    private void setOnClick() {
        binding.btnOrder.setOnClickListener(v -> {
            final boolean[] canOrder = new boolean[1];
            list.forEach(new Consumer<CartModel>() {
                @Override
                public void accept(CartModel cartModel) {
                    if (cartModel.isCheck()) {
                        canOrder[0] = true;
                    }
                }
            });
            if (canOrder[0]) {
                new AlertDialog.Builder(getContext()).setMessage("Bạn có chắc chắn muốn đặt hàng không ?").setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        getListFood().forEach(new Consumer<CartModel>() {
                            @Override
                            public void accept(CartModel cartModel) {
                                if (databaseHandler.getSoLuongTrongKho(cartModel.getFoodID()) == 0) {
                                    Toast.makeText(getContext(), "Số lượng hàng trong kho đã hết", Toast.LENGTH_SHORT).show();
                                } else if (databaseHandler.getSoLuongTrongKho(cartModel.getFoodID()) < cartModel.getAmount()) {
                                    Toast.makeText(getContext(), "Không đủ số lượng " + cartModel.getName(), Toast.LENGTH_SHORT).show();
                                } else {
                                    order();
                                }

                            }
                        });

                    }
                }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setIcon(android.R.drawable.ic_dialog_alert).show();

            } else {
                ShowMessageHelper.showMessage(getContext(), "Bạn chưa chọn sản phẩm");
            }
        });
        binding.thanhToanKhiNhanHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phuongthucthanhtoan = 0;
            }
        });
        binding.thanhToanOnLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phuongthucthanhtoan = 1;
            }
        });
    }

    /// check quá trình đặt hàng
    @SuppressLint("SimpleDateFormat")
    private void order() {
        String date_order = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
        int userID = userData.getId();
        String data = new Gson().toJson(getListFood());
        long result = databaseHandler.createOrder(date_order, userID, data, phuongthucthanhtoan, userData.getAddress());
        if (result == -1) {
            ShowMessageHelper.showMessage(getContext(), "Order thất bại");
        } else {
            ShowMessageHelper.showMessage(getContext(), "Order thành công");
            List<CartModel> listCheck = getListFood();
            listCheck.forEach(cartModel -> {
                databaseHandler.removeCart(cartModel.getId());
                databaseHandler.updateSoLuong(cartModel.getFoodID(), cartModel.getAmount());
                list.remove(cartModel);
            });
            adapter.notifyDataSetChanged();
            if (list.size() > 0) {
                binding.linear.setVisibility(View.VISIBLE);
                binding.btnOrder.setVisibility(View.VISIBLE);
            } else {
                binding.linear.setVisibility(View.GONE);
                binding.btnOrder.setVisibility(View.GONE);
            }
        }
    }


    private List<CartModel> getListFood() {
        List<CartModel> listCheck = new ArrayList<>();
        if (list.size() > 0) {
            list.forEach(cartModel -> {
                if (cartModel.isCheck()) {
                    listCheck.add(cartModel);
                }
            });
        }
        return listCheck;
    }

    @Override
    public void onClick(CartModel cartModel) {
        if (cartModel != null) {
            new AlertDialog.Builder(getContext()).setMessage("Bạn có muốn xóa không ?").setPositiveButton("Có", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    removeCart(cartModel);
                    setTotalPrice();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setIcon(android.R.drawable.ic_dialog_alert).show();
        }
    }

    // xóa sản phẩm khỏi giỏ hàng
    private void removeCart(CartModel cartModel) {
        if (cartModel != null) {
            int result = databaseHandler.removeCart(cartModel.getId());
            if (result == -1) {
                ShowMessageHelper.showMessage(getContext(), "Xóa giỏ hàng thất bại");
            } else {
                ShowMessageHelper.showMessage(getContext(), "Xóa giỏ hàng thành công");
                list.remove(cartModel);
                adapter.notifyDataSetChanged();
                if (list.size() > 0) {
                    binding.linear.setVisibility(View.VISIBLE);
                    binding.btnOrder.setVisibility(View.VISIBLE);
                } else {
                    binding.linear.setVisibility(View.GONE);
                    binding.btnOrder.setVisibility(View.GONE);
                }
            }
        }
    }

    // tính toán giá và trả về
    private Long getTotalPrice() {
        long total = 0L;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                total += list.get(i).getPrice() * list.get(i).getAmount();
            }
        }
        return total;
    }

    // cập nhật giá
    @Override
    public void setTotalPrice() {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String totalPrice = "Tổng tiền: " + formatter.format(getTotalPrice()) + " VNĐ";
        binding.tvTotal.setText(totalPrice);
        binding.tvSoLuongChon.setText("Đã chọn: " + getListFood().size());
    }
}