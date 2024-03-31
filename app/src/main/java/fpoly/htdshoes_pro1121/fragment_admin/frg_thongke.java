package fpoly.htdshoes_pro1121.fragment_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

//import com.jjoe64.graphview.GraphView;

import fpoly.htdshoes_pro1121.R;

public class frg_thongke extends Fragment {

    private Spinner spinnerThang;
    private TextView txtDoanhThu;
//    private GraphView graphView;
    private Button btnTong, btnClear;

    public frg_thongke() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frg_thongke, container, false);

        spinnerThang = rootView.findViewById(R.id.spinnerThang);
        txtDoanhThu = rootView.findViewById(R.id.txtDoanhThu);
//        graphView = rootView.findViewById(R.id.graphView);
        btnTong = rootView.findViewById(R.id.btnTong);
        btnClear = rootView.findViewById(R.id.btnClear);

        // Tạo danh sách các tháng

// Tạo danh sách các thể loại đơn hàng
        String[] theLoaiList = new String[]{
                "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5"
        };

// Tạo ArrayAdapter và thiết lập cho Spinner
        ArrayAdapter<String> theLoaiAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, theLoaiList);
        theLoaiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThang.setAdapter(theLoaiAdapter);

        // Thiết lập sự kiện cho nút "Tổng"
        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy tháng đã chọn từ Spinner
                String selectedMonth = spinnerThang.getSelectedItem().toString();

                // Tính toán doanh thu theo tháng
                double doanhThu = tinhDoanhThuTheoThang(selectedMonth);

                // Hiển thị doanh thu
                txtDoanhThu.setText(String.valueOf(doanhThu));

                // Hiển thị biểu đồ cột
                hienThiBieuDo(doanhThu);
            }
        });

        // Thiết lập sự kiện cho nút "Clear"
//        btnClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Xóa dữ liệu doanh thu và biểu đồ
//                txtDoanhThu.setText("");
//                graphView.removeAllSeries();
//            }
//        });

        return rootView;
    }

    private double tinhDoanhThuTheoThang(String selectedMonth) {
        // TODO: Thực hiện tính toán doanh thu theo tháng và trả về giá trị doanh thu
        // Ở đây bạn cần viết mã logic để tính toán doanh thu dựa trên tháng đã chọn
        // Tính toán và trả về giá trị doanh thu

        // Ví dụ:
        if (selectedMonth.equals("Tháng 1")) {
            return 1000.0;
        } else if (selectedMonth.equals("Tháng 2")) {
            return 2000.0;
        } else if (selectedMonth.equals("Tháng 3")) {
            return 3000.0;
        }
        // ...

        return 0.0;
    }

    private void hienThiBieuDo(double doanhThu) {
        // TODO: Hiển thị biểu đồ cột dựa trên giá trị doanh thu
        // Ở đây bạn cần viết mã logic để hiển thị biểu đồ cột dựa trên giá trị doanh thu
    }
}