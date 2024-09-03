package com.example.bongsac.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bongsac.database.DbHelper;
import com.example.bongsac.model.Bongsac;

import java.util.ArrayList;
import java.util.List;

public class bongsacDAO {
    private SQLiteDatabase db;

    public bongsacDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(Bongsac ob){
        ContentValues values = new ContentValues();
        values.put("tenbongsac",ob.tenbongsac);
        values.put("giaMua",ob.giaMua);
        values.put("moTa",ob.moTa);
        values.put("soLuong",ob.soLuong);
        values.put("maLoai", ob.maLoai);
        values.put("hinh", ob.hinh);
        return db.insert("bongsac",null,values);
    }
    public int update(Bongsac ob){
        ContentValues values = new ContentValues();
        values.put("tenbongsac",ob.tenbongsac);
        values.put("giaMua",ob.giaMua);
        values.put("moTa",ob.moTa);
        values.put("soLuong",ob.soLuong);
        values.put("maLoai", ob.maLoai);
        values.put("hinh", ob.hinh);
        return db.update("bongsac",values,"mabongsac=?", new String[]{String.valueOf(ob.mabongsac)});
    }
    public int delete(String id){
        return db.delete("bongsac","mabongsac=?", new String[]{id});
    }

    public List<Bongsac> getAll(){
        String sql = "SELECT * FROM bongsac";
        return getData(sql);
    }

    public Bongsac getID(String id){
        Bongsac objbongsac = new Bongsac();
        String[] argss = new String[]{ id + ""};
    Cursor c = db.rawQuery("SELECT mabongsac, tenbongsac, giaMua, moTa, soLuong, maLoai, hinh FROM bongsac WHERE mabongsac=?", argss);
    if(c.moveToFirst()){
        objbongsac.mabongsac = c.getInt(0);
        objbongsac.tenbongsac = c.getString(1);
        objbongsac.giaMua = c.getString(2);
        objbongsac.moTa = c.getString(3);
        objbongsac.soLuong = c.getString(4);
        objbongsac.maLoai = c.getInt(5);
        objbongsac.hinh = c.getBlob(6);
    }
    return objbongsac;
}

//Get data nhieu tham so
   private List<Bongsac> getData(String sql, String...selectionArgs){
        List<Bongsac> list = new ArrayList<Bongsac>();
       Cursor c = db.rawQuery(sql, selectionArgs);
       while (c.moveToNext()){
          Bongsac ob = new Bongsac();
           ob.mabongsac = Integer.parseInt(c.getString(c.getColumnIndex("mabongsac")));
           ob.tenbongsac = c.getString(c.getColumnIndex("tenbongsac"));
           ob.giaMua = c.getString(c.getColumnIndex("giaMua"));
           ob.moTa = c.getString(c.getColumnIndex("moTa"));
           ob.soLuong = c.getString(c.getColumnIndex("soLuong"));
           ob.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
           ob.hinh = c.getBlob(c.getColumnIndex("hinh"));
           list.add(ob);
       }
       return list;
   }

   public List<Bongsac> getSearch_bongsac(String tenbongsac){
        String sql = "SELECT * FROM bongsac WHERE tenbongsac LIKE '%"+tenbongsac+"%' ";
        return getData(sql);
    }
}
