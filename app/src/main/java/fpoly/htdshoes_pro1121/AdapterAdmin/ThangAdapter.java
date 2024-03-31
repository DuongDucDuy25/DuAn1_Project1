package fpoly.htdshoes_pro1121.AdapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fpoly.htdshoes_pro1121.R;

public class ThangAdapter extends ArrayAdapter<String> {

    private List<String> itemList;
    private LayoutInflater inflater;

    public ThangAdapter(Context context, List<String> itemList) {
        super(context, android.R.layout.simple_spinner_item, itemList);
        this.itemList = itemList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(itemList.get(position));

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(itemList.get(position));

        return convertView;
    }
}