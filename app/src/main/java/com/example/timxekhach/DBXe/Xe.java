package com.example.timxekhach.DBXe;

public class Xe {
    private int IdXe;
    private String TenXe, BienSo, LoaiXe, MauXe, Sdt, GiaVe, Tuyen, ThoiGian, LichTrinh;
    private int AnhXe, SoGhe;

    public Xe() {
    }

    public Xe(int IdXe, String TenXe, String BienSo, String LoaiXe, String MauXe, String Sdt, String GiaVe, String Tuyen, String ThoiGian, String LichTrinh, int AnhXe, int SoGhe) {
        this.IdXe = IdXe;
        this.TenXe = TenXe;
        this.BienSo = BienSo;
        this.LoaiXe = LoaiXe;
        this.MauXe = MauXe;
        this.Sdt = Sdt;
        this.GiaVe = GiaVe;
        this.Tuyen = Tuyen;
        this.ThoiGian = ThoiGian;
        this.LichTrinh = LichTrinh;
        this.AnhXe = AnhXe;
        this.SoGhe = SoGhe;
    }

    public Xe(String TenXe, String BienSo, String LoaiXe, String MauXe, String Sdt, String GiaVe, String Tuyen, String ThoiGian, String LichTrinh, int AnhXe, int SoGhe) {
        this.TenXe = TenXe;
        this.BienSo = BienSo;
        this.LoaiXe = LoaiXe;
        this.MauXe = MauXe;
        this.Sdt = Sdt;
        this.GiaVe = GiaVe;
        this.Tuyen = Tuyen;
        this.ThoiGian = ThoiGian;
        this.LichTrinh = LichTrinh;
        this.AnhXe = AnhXe;
        this.SoGhe = SoGhe;
    }

    public int getIdXe() {
        return IdXe;
    }
    public void setIdXe(int IdXe) {
        this.IdXe = IdXe;
    }
    public String getTenXe() {
        return TenXe;
    }
    public void setTenXe(String TenXe) {
        this.TenXe = TenXe;
    }
    public String getBienSo() {
        return BienSo;
    }
    public void setBienSo(String BienSo) {
        this.BienSo = BienSo;
    }
    public String getLoaiXe() {
        return LoaiXe;
    }
    public void setLoaiXe(String LoaiXe) {
        this.LoaiXe = LoaiXe;
    }
    public String getMauXe() {
        return MauXe;
    }
    public void setMauXe(String MauXe) {
        this.MauXe = MauXe;
    }
    public String getSdt() {
        return Sdt;
    }
    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }
    public String getGiaVe() {
        return GiaVe;
    }
    public void setGiaVe(String GiaVe) {
        this.GiaVe = GiaVe;
    }
    public String getTuyen() {
        return Tuyen;
    }
    public void setTuyen(String Tuyen) {
        this.Tuyen = Tuyen;
    }
    public String getThoiGian() {
        return ThoiGian;
    }
    public void setThoiGian(String ThoiGian) {
        this.ThoiGian = ThoiGian;
    }
    public String getLichTrinh() {
        return LichTrinh;
    }
    public void setLichTrinh(String LichTrinh) {
        this.LichTrinh = LichTrinh;
    }
    public int getAnhXe() {
        return AnhXe;
    }
    public void setAnhXe(int AnhXe) {
        this.AnhXe = AnhXe;
    }
    public int getSoGhe() {
        return SoGhe;
    }
    public void setSoGhe(int SoGhe) {
        this.SoGhe = SoGhe;
    }

}