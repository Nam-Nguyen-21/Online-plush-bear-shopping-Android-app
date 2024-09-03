package com.example.bongsac.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.bongsac.R;
import com.example.bongsac.adapter.BongsacAdapter;
import com.example.bongsac.adapter.LoaiSpinnerAdapter;
import com.example.bongsac.dao.bongsacDAO;
import com.example.bongsac.dao.LoaiDAO;
import com.example.bongsac.model.Bongsac;
import com.example.bongsac.model.LoaiBongsac;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class BongsacFragment extends Fragment {

    ListView lv;
    ArrayList<Bongsac> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMabongsac, edTenbongsac, edGiaMua, edMoTa, edSoLuong, ed_search;
    Spinner spinner;
    ImageView img_anh, img_search;
    Button btnSave, btnCanl;

    static bongsacDAO dao;
    BongsacAdapter adapter;
    Bongsac item;
    LoaiSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiBongsac> listLoaibongsac;
    LoaiDAO loaibongsacDAO;
    int maLoai, positionG;
    final int REQUEST_CODE_FOLDER = 456;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nuochoa, container, false);
        lv = v.findViewById(R.id.lv_bongsac);
        fab = v.findViewById(R.id.fab_bongsac);
        ed_search = v.findViewById(R.id.ed_search_bongsac);
        img_search = v.findViewById(R.id.img_search_bongsac);
        dao = new bongsacDAO(getActivity());
        capNhatLv();

//        tim kiem bongsac theo ten bongsac
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_search.getText().length() == 0){
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin trước khi Search", Toast.LENGTH_SHORT).show();
                }
                capNhatTen_bongsac();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });

        return v;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.bongsac_dialog);
        edMabongsac = dialog.findViewById(R.id.edmabongsac_dialog);
        edTenbongsac = dialog.findViewById(R.id.edtenbongsac_dialog);
        edGiaMua = dialog.findViewById(R.id.edgiaMua_dialog);
        edSoLuong = dialog.findViewById(R.id.edSoLuong_dialog);
        edMoTa = dialog.findViewById(R.id.edmoTa_dialog);
        img_anh = dialog.findViewById(R.id.img_anh_dialog);
        spinner = dialog.findViewById(R.id.spLoaibongsac);
        btnCanl = dialog.findViewById(R.id.btnCancelbongsac);
        btnSave = dialog.findViewById(R.id.btnSavebongsac);

        listLoaibongsac = new ArrayList<LoaiBongsac>();
        loaibongsacDAO = new LoaiDAO(context);
        listLoaibongsac = (ArrayList<LoaiBongsac>) loaibongsacDAO.getAll();
        spinnerAdapter = new LoaiSpinnerAdapter(context,listLoaibongsac);
        spinner.setAdapter(spinnerAdapter);

//        lay ma Loai bongsac
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoai = listLoaibongsac.get(position).maLoai;
                Toast.makeText(context,"Chọn"+listLoaibongsac.get(position).tenLoai,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edMabongsac.setEnabled(false);
        if(type != 0) {
            edMabongsac.setText(String.valueOf(item.mabongsac));
            edTenbongsac.setText(item.tenbongsac);
            edGiaMua.setText(String.valueOf(item.giaMua));
            edMoTa.setText(item.moTa);
            edSoLuong.setText(String.valueOf(item.soLuong));
            for (int i = 0; i < listLoaibongsac.size(); i++) {
                if (item.maLoai == (listLoaibongsac.get(i).maLoai)) {
                    positionG = i;
                }
                spinner.setSelection(positionG);
            }
        }

        btnCanl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img_anh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
//===========
                item = new Bongsac();
                item.maLoai = maLoai;
                item.setMaLoai(maLoai);
                item.tenbongsac = edTenbongsac.getText().toString();
                item.giaMua = edGiaMua.getText().toString();
                item.moTa = edMoTa.getText().toString();
                item.soLuong = edSoLuong.getText().toString();
                item.hinh = hinhAnh;
                if(valible()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                        }

                    }else{

                        item.mabongsac = Integer.parseInt(edMabongsac.getText().toString());
                        if(dao.update(item)>0){
                            Toast.makeText(context,"Cập Nhật Thành Công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Cập Nhật Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });

        img_anh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_anh.setImageBitmap (bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void xoabongsac(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                Snackbar.make(getView(),"Bạn đã xóa thành công",Snackbar.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    void capNhatLv(){
        list = (ArrayList<Bongsac>) dao.getAll();
        adapter = new BongsacAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }

    void capNhatTen_bongsac(){
        list = (ArrayList<Bongsac>)dao.getSearch_bongsac(ed_search.getText().toString());
        adapter = new BongsacAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    public int valible(){
        int check = 1;
        if (edTenbongsac.getText().length() == 0 || edGiaMua.getText().length() == 0 || edMoTa.getText().length() == 0 || edSoLuong.getText().length() == 0 ){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}