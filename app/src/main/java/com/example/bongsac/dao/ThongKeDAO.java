package com.example.bongsac.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bongsac.database.DbHelper;
import com.example.bongsac.model.Bongsac;
import com.example.bongsac.model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

//    thong ke TOP5
    public List<Top> getTop(){
        String sqlTop = "SELECT mabongsac, count(mabongsac) as slTOP FROM HoaDon GROUP BY mabongsac ORDER BY slTOP DESC LIMIT 5";
        List<Top> list = new ArrayList<Top>();
        bongsacDAO bongsacDAO = new bongsacDAO(context);
        Cursor c = db.rawQuery(sqlTop, null);

        while (c.moveToNext()){
            Top top = new Top();
            Bongsac bongsac = bongsacDAO.getID(c.getString(c.getColumnIndex("mabongsac")));
            top.tenbongsac = bongsac.tenbongsac;
            top.slTOP = Integer.parseInt(c.getString(c.getColumnIndex("slTOP")));
            list.add(top);
        }
      return list;
    }

    public int getDanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(giaHD) as doanhThu FROM HoaDon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay,denNgay});

        while (c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        c.close();
        return list.get(0);
    }
}
