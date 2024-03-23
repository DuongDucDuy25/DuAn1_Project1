package fpoly.htdshoes_pro1121.AdapterUser;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.Dao.SanPhamDao;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.R;

public class SanPham_user_Adapter extends RecyclerView.Adapter<SanPham_user_Adapter.ViewHolder> implements Filterable {
    private Context context;
    private ArrayList<SanPham> list;
    private ArrayList<SanPham> listSanPham;
    private SanPhamDao dao;
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    list = new ArrayList<>(listSanPham);
                } else {
                    ArrayList<SanPham> sachArrayList = new ArrayList<>();
                    for (SanPham sach : listSanPham) {
                        if (sach.getTenSanPham().toLowerCase().contains(strSearch.toLowerCase())) {
                            sachArrayList.add(sach);
                        }
                    }
                    list = sachArrayList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<SanPham>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public SanPham_user_Adapter(Context context, ArrayList<SanPham> list, SanPhamDao dao) {
        this.context = context;
        this.listSanPham = new ArrayList<>(list);
        this.list = list;
        this.dao = dao;

    }

    @NonNull
    @Override
    public SanPham_user_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPham_user_Adapter.ViewHolder holder, int position) {

        holder.tvTenSanPham.setText("Tên :" +list.get(position).getTenSanPham());
        if (list.get(position).getMaTL()==1) {
            holder.tvMaTL.setText("Thể Loại :Nike"  );
        }else {
            holder.tvMaTL.setText("Thể loại :Jodan");
        }


        holder.tvSoLuong.setText("Số Lượng :" +String.valueOf(list.get(position).getSoLuong()));
        holder.tvGiaTien.setText("Giá :" + String.valueOf(list.get(position).getGiaTien()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMaSanPham,tvTenSanPham,tvMaCTSP,tvMaTL,tvSoLuong,tvGiaTien;
        LinearLayout SanPhamAdmin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);

            tvMaTL = itemView.findViewById(R.id.tvMaTL);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTien);


        }
    }
}
