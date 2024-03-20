package fpoly.htdshoes_pro1121.Adapter;

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

public class SanPhamAdminAdapter extends RecyclerView.Adapter<SanPhamAdminAdapter.ViewHolder> implements Filterable {
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

    public SanPhamAdminAdapter(Context context, ArrayList<SanPham> list, SanPhamDao dao) {
        this.context = context;
        this.listSanPham = new ArrayList<>(list);
        this.list = list;
        this.dao = dao;

    }

    @NonNull
    @Override
    public SanPhamAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdminAdapter.ViewHolder holder, int position) {
        holder.tvMaSanPham.setText("Mã :" + String.valueOf(list.get(position).getMaSanPham()));
        holder.tvTenSanPham.setText("Tên :" +list.get(position).getTenSanPham());
        holder.tvMaCTSP.setText("Mã CTSP :" +String.valueOf(list.get(position).getMaCTSP()));
        holder.tvMaTL.setText("Mã TL :" +String.valueOf(list.get(position).getMaTL()));
        holder.tvSoLuong.setText("Số Lượng :" +String.valueOf(list.get(position).getSoLuong()));
        holder.tvGiaTien.setText("Giá :" + String.valueOf(list.get(position).getGiaTien()));

        holder.SanPhamAdmin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialogUpdate(list.get(holder.getAdapterPosition()));
                return false;
            }
        });
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
            tvMaSanPham = itemView.findViewById(R.id.tvMaSanPham);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);
            tvMaCTSP = itemView.findViewById(R.id.tvMaCTSP);
            tvMaTL = itemView.findViewById(R.id.tvMaTL);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTien);
            SanPhamAdmin = itemView.findViewById(R.id.SanPhamAdmin);

        }
    }
    private void showDialogUpdate(SanPham sanPham){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_sanpham,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edMaSanPham = view.findViewById(R.id.edMaSanPham);
        EditText edTenSanPham = view.findViewById(R.id.edNameSanPham);
        EditText edMaCTSP = view.findViewById(R.id.edMaCTSP);
        EditText edMaTL = view.findViewById(R.id.edMaTL);
        EditText edSoLuong = view.findViewById(R.id.edSoLuong);
        EditText edGiaTien = view.findViewById(R.id.edGiaTien);
        Button btnUpdate = view.findViewById(R.id.btnUpdateSanPham);
        Button btnCancel = view.findViewById(R.id.btnCancelSanPham);

        edMaSanPham.setText(String.valueOf(sanPham.getMaSanPham()));
        edTenSanPham.setText(sanPham.getTenSanPham());
        edMaCTSP.setText(String.valueOf(sanPham.getMaCTSP()));
        edMaTL.setText(String.valueOf(sanPham.getMaTL()));
        edSoLuong.setText(String.valueOf(sanPham.getSoLuong()));
        edGiaTien.setText(String.valueOf(sanPham.getGiaTien()));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masanpham = sanPham.getMaSanPham();
                String tensach = edTenSanPham.getText().toString();
                int mactsp = Integer.parseInt(edMaCTSP.getText().toString());
                int matl = Integer.parseInt(edMaTL.getText().toString());
                int soluong = Integer.parseInt(edSoLuong.getText().toString());
                int giatien = Integer.parseInt(edGiaTien.getText().toString());

                SanPham sanphamupdate = new SanPham(masanpham, tensach, mactsp,matl,soluong,giatien);
                boolean check = dao.updateSanPham(sanphamupdate);
                if (check){
                    Toast.makeText(context, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list = dao.getlistdata();
                    notifyDataSetChanged();
                    alertDialog.dismiss();
                }
                else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
