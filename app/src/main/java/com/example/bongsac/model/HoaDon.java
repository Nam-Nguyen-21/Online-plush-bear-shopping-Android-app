package com.example.bongsac.model;

public class HoaDon {
    public int maHD;
    public int maNV;
    public int maKH;
    public int mabongsac;
    public String ngay;
    public String giaHD;
    public int trangThai;

    public HoaDon() {
    }

    public HoaDon(int maHD, int maNV, int maKH, int mabongsac, String ngay, String giaHD, int trangThai) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.mabongsac = mabongsac;
        this.ngay = ngay;
        this.giaHD = giaHD;
        this.trangThai = trangThai;
    }
}