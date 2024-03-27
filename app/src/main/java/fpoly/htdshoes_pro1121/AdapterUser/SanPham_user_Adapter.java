package fpoly.htdshoes_pro1121.AdapterUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.R;

public class SanPham_user_Adapter extends RecyclerView.Adapter<SanPham_user_Adapter.ViewHolder> {

    private Context context;
    private ArrayList<SanPham> productList;

    public SanPham_user_Adapter(Context context, ArrayList<SanPham> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham product = productList.get(position);
        holder.tvTenSanPham.setText(product.getTenSanPham());
        holder.tvMaTL.setText(getCategoryName(product.getMaTL()));
        holder.tvSoLuong.setText("Số lượng: " + product.getSoLuong());
        holder.tvGiaTien.setText("Giá: " + product.getGiaTien());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    private String getCategoryName(int categoryId) {
        // Chuyển đổi mã thể loại thành tên thể loại tương ứng
        switch (categoryId) {
            case 1:
                return "Thể Loại: Nike";
            case 2:
                return "Thể Loại: Vans";
            case 3:
                return "Thể Loại: Converse";
            case 4:
                return "Thể Loại: Adidas";
            case 5:
                return "Thể Loại: MLB";
            default:
                return "Unknown";
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSanPham, tvMaTL, tvSoLuong, tvGiaTien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);
            tvMaTL = itemView.findViewById(R.id.tvMaTL);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTien);
        }
    }
}
