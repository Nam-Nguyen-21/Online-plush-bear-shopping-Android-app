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
import com.example.bongsac.model.LoaiBongsac;

import java.util.ArrayList;

public class LoaiSpinnerAdapter extends ArrayAdapter<LoaiBongsac> {
    private Context context;
    private ArrayList<LoaiBongsac> lists;
    TextView tvMaLoaibongsacSP, tvTenLoaibongsacSP;

    public LoaiSpinnerAdapter(@NonNull Context context , ArrayList<LoaiBongsac> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_nuochoa_item_spinner,null);
        }
        final LoaiBongsac item = lists.get(position);
        if(item != null){
            tvMaLoaibongsacSP = v.findViewById(R.id.tvMaLoaibongsacSp);
            tvMaLoaibongsacSP.setText(item.maLoai+". ");
            tvTenLoaibongsacSP = v.findViewById(R.id.tvTenLoaibongsacSp);
            tvTenLoaibongsacSP.setText(item.tenLoai);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_nuochoa_item_spinner,null);
        }
        final LoaiBongsac item = lists.get(position);
        if(item != null){
            tvMaLoaibongsacSP = v.findViewById(R.id.tvMaLoaibongsacSp);
            tvMaLoaibongsacSP.setText(item.maLoai+". ");
            tvTenLoaibongsacSP = v.findViewById(R.id.tvTenLoaibongsacSp);
            tvTenLoaibongsacSP.setText(item.tenLoai);
        }
        return v;
    }
}
