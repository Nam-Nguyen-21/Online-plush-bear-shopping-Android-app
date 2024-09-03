package com.example.bongsac.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bongsac.R;
import com.example.bongsac.dao.LoaiDAO;
import com.example.bongsac.fragment.BongsacFragment;
import com.example.bongsac.model.Bongsac;
import com.example.bongsac.model.LoaiBongsac;

import java.util.ArrayList;

public class BongsacAdapter extends ArrayAdapter<Bongsac> {

    private Context context;
    BongsacFragment fragment;
    private ArrayList<Bongsac> lists;
    TextView tvMabongsac, tvTenbongsac, tvGiaMua, tvMaLoaibongsac, tvMoTa, tvSoLuong;
    ImageView imgDeletebongsac, imgHinh;

    public BongsacAdapter(@NonNull Context context, BongsacFragment fragment, ArrayList<Bongsac> lists){
        super(context,0,lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.bongsac_item,null);
        }
        final Bongsac item = lists.get(position);
        if(item != null){
            LoaiDAO loaibongsacDAO = new LoaiDAO(context);
            LoaiBongsac loaibongsac = loaibongsacDAO.getID(String.valueOf(item.maLoai));

            tvMabongsac = v.findViewById(R.id.tv_mabongsac_item);
            tvMabongsac.setText("Mã: "+item.mabongsac);

            tvTenbongsac = v.findViewById(R.id.tv_tenbongsac_item);
            tvTenbongsac.setText("Tên: "+item.tenbongsac);

            tvSoLuong = v.findViewById(R.id.tv_soLuongG_item);
            tvSoLuong.setText("Số Lượng: "+item.soLuong);

            tvGiaMua = v.findViewById(R.id.tv_giaMua_item);
            tvGiaMua.setText("Giá bán: "+item.giaMua+" VNĐ");

            tvMaLoaibongsac = v.findViewById(R.id.tv_maLoaibongsac_item);
            tvMaLoaibongsac.setText("Thương hiệu: "+loaibongsac.tenLoai);

            tvMoTa = v.findViewById(R.id.tv_moTa_item);
            tvMoTa.setText("Mô tả "+item.moTa);

            imgHinh = v.findViewById(R.id.img_bongsac_item);
            byte[] hinhbongsac = item.hinh;
            Bitmap bitmap;
            bitmap = BitmapFactory.decodeByteArray(hinhbongsac, 0, hinhbongsac.length);
            imgHinh.setImageBitmap(bitmap);
            imgDeletebongsac = v.findViewById(R.id.img_delete_bongsac_item);
        }
        imgDeletebongsac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.xoabongsac(String.valueOf(item.mabongsac));
            }
        });

//        if (position % 2 == 0){
//
//        }

        return v;

    }

}
