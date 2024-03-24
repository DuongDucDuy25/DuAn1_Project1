package fpoly.htdshoes_pro1121.AdapterAdmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.htdshoes_pro1121.R;

public class TongQuanAdapter extends RecyclerView.Adapter<TongQuanAdapter.ViewHolder>{
    private List<String> data;

    public TongQuanAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TongQuanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rc_tongquan, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TongQuanAdapter.ViewHolder holder, int position) {
        String item = data.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView arrowleft;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            arrowleft = itemView.findViewById(R.id.arrowright);
        }
        public void bindData(String item) {
            // Thiết lập dữ liệu cho các thành phần trong ViewHolder
            textView.setText(item);
        }
    }
}
