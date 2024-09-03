package com.example.bongsac.model;

public class Bongsac {
//    them thuoc tinh hinh anh vao day
    public int mabongsac;
    public String tenbongsac;
    public String giaMua;
    public String moTa;
    public String soLuong;
    public int maLoai;
    public byte[] hinh;

    public Bongsac() {
    }

    public Bongsac(int mabongsac, String tenbongsac, String giaMua, String moTa, String soLuong, int maLoai, byte[] hinh) {
        this.mabongsac = mabongsac;
        this.tenbongsac = tenbongsac;
        this.giaMua = giaMua;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.maLoai = maLoai;
        this.hinh = hinh;
    }

    public int getMabongsac() {
        return mabongsac;
    }

    public void setMabongsac(int mabongsac) {
        this.mabongsac = mabongsac;
    }

    public String getTenbongsac() {
        return tenbongsac;
    }

    public void setTenbongsac(String tenbongsac) {
        this.tenbongsac = tenbongsac;
    }

    public String getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(String giaMua) {
        this.giaMua = giaMua;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}