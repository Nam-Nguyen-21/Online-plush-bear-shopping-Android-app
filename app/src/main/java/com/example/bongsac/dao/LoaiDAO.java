package com.example.bongsac.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bongsac.database.DbHelper;
import com.example.bongsac.model.LoaiBongsac;

import java.util.ArrayList;
import java.util.List;

public class LoaiDAO {
    private SQLiteDatabase db;

    public LoaiDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiBongsac ob){
        ContentValues values = new ContentValues();
        values.put("tenLoai", ob.tenLoai);
        return db.insert("Loaibongsac",null,values);
    }

    public int update(LoaiBongsac ob){
        ContentValues values = new ContentValues();
        values.put("tenLoai",ob.tenLoai);
        return db.update("Loaibongsac",values,"maLoai=?", new String[]{String.valueOf(ob.maLoai)});
    }

    public int delete(String id){
        return db.delete("Loaibongsac","maLoai=?", new String[]{id});
    }

    public List<LoaiBongsac> getAll(){
        String sql = "SELECT * FROM Loaibongsac";
        return getData(sql);
    }

    public LoaiBongsac getID(String id){
        LoaiBongsac objLoaibongsac = new LoaiBongsac();
        String[] args = new String[]{id + ""};
        Cursor c = db.rawQuery("SELECT maLoai, tenLoai FROM Loaibongsac WHERE maLoai=?", args);
        if(c.moveToFirst()){
            objLoaibongsac.maLoai = c.getInt(0);
            objLoaibongsac.tenLoai = c.getString(1);
        }
        return objLoaibongsac;
    }

   private List<LoaiBongsac> getData(String sql, String...selectionArgs){
        List<LoaiBongsac> list = new ArrayList<LoaiBongsac>();
       Cursor c = db.rawQuery(sql, selectionArgs);
       while (c.moveToNext()){
           LoaiBongsac ob = new LoaiBongsac();
           ob.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
           ob.tenLoai = c.getString(c.getColumnIndex("tenLoai"));
           list.add(ob);
       }
       return list;
   }

   public List<LoaiBongsac> getSearch_loaibongsac(String tenLoai){
        String sql = "SELECT * FROM Loaibongsac WHERE tenLoai LIKE '%"+tenLoai+"%' ";
        return getData(sql);
   }

}
