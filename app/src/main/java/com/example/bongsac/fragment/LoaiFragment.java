package com.example.bongsac.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.bongsac.R;
import com.example.bongsac.adapter.LoaiAdapter;
import com.example.bongsac.dao.LoaiDAO;
import com.example.bongsac.model.LoaiBongsac;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class LoaiFragment extends Fragment {

    ListView lv;
    ArrayList<LoaiBongsac> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoaibongsac, edTenLoaibongsac, ed_search;
    Button btnSaveLG, btnCancelLG;
    ImageView img_search;
    static LoaiDAO dao;
    LoaiAdapter adapter;
    LoaiBongsac item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loai_nuochoa, container, false);
        lv = v.findViewById(R.id.lvLoaibongsac);
        fab = v.findViewById(R.id.fab_LG);
        ed_search = v.findViewById(R.id.ed_search_loaibongsac);
        img_search = v.findViewById(R.id.img_search_loaibongsac);
        dao = new LoaiDAO(getActivity());
        capNhatLv();

//        tim kiem
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_search.getText().length() == 0){
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin trước khi Search", Toast.LENGTH_SHORT).show();
                }
                capNhatTen_loaibongsac();
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
                openDialog(getActivity(),1);  // 1 update
                return false;
            }
        });

        return v;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_nuochoa_dialog);
        edMaLoaibongsac = dialog.findViewById(R.id.ed_maLoai_dialog);
        edTenLoaibongsac = dialog.findViewById(R.id.edtenLoai_dialog);
        btnCancelLG = dialog.findViewById(R.id.btnCancelLG);
        btnSaveLG = dialog.findViewById(R.id.btnSaveLG);
        edMaLoaibongsac.setEnabled(false);
        if(type != 0){
            edMaLoaibongsac.setText(String.valueOf(item.maLoai));
            edTenLoaibongsac.setText(item.tenLoai);
        }

        btnCancelLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSaveLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiBongsac();
                item.tenLoai = edTenLoaibongsac.getText().toString();
                if(validate()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm Thương hiệu  Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.maLoai = Integer.parseInt(edMaLoaibongsac.getText().toString());
                        if(dao.update(item)>0){
                            Toast.makeText(context,"Cập nhật Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Cập nhật Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoaLoaibongsac(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xoá mục này không?");
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
        list = (ArrayList<LoaiBongsac>)dao.getAll();
        adapter = new LoaiAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    void capNhatTen_loaibongsac(){
        list = (ArrayList<LoaiBongsac>)dao.getSearch_loaibongsac(ed_search.getText().toString());
        adapter = new LoaiAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    public int validate(){
        int check = 1;
        if(edTenLoaibongsac.getText().length()==0){
            Toast.makeText(getContext(),"Bạn chưa nhập Tên ", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}