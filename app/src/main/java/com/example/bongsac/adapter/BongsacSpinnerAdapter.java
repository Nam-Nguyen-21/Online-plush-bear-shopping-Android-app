package com.example.bongsac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bongsac.R;
import com.example.bongsac.model.Bongsac;

import java.util.ArrayList;

public class BongsacSpinnerAdapter extends ArrayAdapter<Bongsac> {
    private Context context;
    private ArrayList<Bongsac> lists;
    TextView tvMabongsacsp, tvTenbongsacsp;

    public BongsacSpinnerAdapter(@NonNull Context context , ArrayList<Bongsac> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.bongsac_item_spinner,null);
        }
        final Bongsac item = lists.get(position);
        if(item != null){
            tvMabongsacsp = v.findViewById(R.id.tvMabongsacSp);
            tvMabongsacsp.setText(item.mabongsac+". ");
            tvTenbongsacsp = v.findViewById(R.id.tvTenbongsacSp);
            tvTenbongsacsp.setText(item.tenbongsac);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.bongsac_item_spinner,null);
        }
        final Bongsac item = lists.get(position);
        if(item != null){
            tvMabongsacsp = v.findViewById(R.id.tvMabongsacSp);
            tvMabongsacsp.setText(item.mabongsac+". ");
            tvTenbongsacsp = v.findViewById(R.id.tvTenbongsacSp);
            tvTenbongsacsp.setText(item.tenbongsac);
        }
        return v;
    }
}
