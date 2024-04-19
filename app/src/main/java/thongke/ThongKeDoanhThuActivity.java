package thongke;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import database.DatabaseHandler;
import fpoly.htdshoes_pro1121.R;
import fpoly.htdshoes_pro1121.databinding.ActivityThongKeDoanhThuBinding;
import model.CartModel;
import model.OrderHistoryModel;

public class ThongKeDoanhThuActivity extends AppCompatActivity {

    private ActivityThongKeDoanhThuBinding binding;
    private DatabaseHandler databaseHandler;
    private List<OrderHistoryModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThongKeDoanhThuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHandler = new DatabaseHandler(this);
        list = databaseHandler.getAllOrder();
        showStatistics();
    }

    private void showStatistics() {
        // định dạng số tiê thành chuỗi
        DecimalFormat formatter = new DecimalFormat("#,### VNĐ");
        int totalRevenue = calculateTotalRevenue();
        setSpannableStringBuilder("Doanh thu: ", formatter.format(totalRevenue), binding.textView5);
        // tính tổng doanh thu và tổng số lượng sản phẩm
        int totalQuantitySold = calculateTotalQuantitySold();
        // hiển thị lên giao diện
        setSpannableStringBuilder("Số lượng sản phẩm đã bán: ", totalQuantitySold + " sản phẩm", binding.textView6);
        showProductStatisticsByDay();
    }

    // tính tổng số sản phẩm đã bán lặp lại danh sah đơn hàng
    // sau đó nhân số lượng mặt hanàng với giá để tính tổng doanh thu
    private int calculateTotalRevenue() {
        int totalRevenue = 0;
        for (OrderHistoryModel orderHistoryModel : list) {
            if (orderHistoryModel.getFoods() != null) {
                for (CartModel cartModel : orderHistoryModel.getFoods()) {
                    totalRevenue += cartModel.getAmount() * cartModel.getPrice();
                }
            }
        }
        return totalRevenue;
    }

    // tính tổng số sản phẩm đã bán sau đó cộng dồn số lượng mặt hàng
    private int calculateTotalQuantitySold() {
        int totalQuantitySold = 0;
        for (OrderHistoryModel orderHistoryModel : list) {
            if (orderHistoryModel.getFoods() != null) {
                for (CartModel cartModel : orderHistoryModel.getFoods()) {
                    totalQuantitySold += cartModel.getAmount();
                }
            }
        }
        return totalQuantitySold;
    }

    // hiển thị thông tin theo từng ngày
    private void showProductStatisticsByDay() {
        for (OrderHistoryModel orderHistoryModel : list) {
            String date = orderHistoryModel.getDate_order();
            int quantitySold = calculateQuantitySoldByDate(orderHistoryModel.getDate_order());

            TextView textView = new TextView(this);
            textView.setText("Ngày " + date + ": " + quantitySold + " sản phẩm");
            binding.linearLayout.addView(textView);
        }
    }

    //tính tổng số lượng đã bán trong một ngày cụ thể
    private int calculateQuantitySoldByDate(String date) {
        int quantitySold = 0;
        for (OrderHistoryModel orderHistoryModel : list) {
            if (orderHistoryModel.getDate_order().equals(date) && orderHistoryModel.getFoods() != null) {
                for (CartModel cartModel : orderHistoryModel.getFoods()) {
                    quantitySold += cartModel.getAmount();
                }
            }
        }
        return quantitySold;
    }

    // xây dựng chuỗi văn bản có thể tùy chỉnh
    private void setSpannableStringBuilder(String textStart, String textEnd, TextView textView) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString str1 = new SpannableString(textStart);
        str1.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str1.length(), 0);
        builder.append(str1);
        SpannableString str2 = new SpannableString(textEnd);
        str2.setSpan(new ForegroundColorSpan(Color.RED),
                0, str2.length(), 0);
        builder.append(str2);
        textView.setText(builder);
    }
}