package com.nnt.banhang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "hoadons")
public class HoaDon {
    @Id
    @Column(name = "MaHD", nullable = false)
    private int mahoadon;

    @Column(name = "MaKH", nullable = false)
    private String makhachhang;

    @Column(name = "MaNV", nullable = false)
    private String manhanvien;

    public HoaDon() {
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "mahoadon=" + mahoadon +
                ", makhachhang='" + makhachhang + '\'' +
                ", manhanvien='" + manhanvien + '\'' +
                ", ngaylaphoadon=" + ngaylaphoadon +
                ", ngaynhanhang=" + ngaynhanhang +
                '}';
    }

    public HoaDon(int mahoadon, String makhachhang, String manhanvien, Date ngaylaphoadon, Date ngaynhanhang) {
        this.mahoadon = mahoadon;
        this.makhachhang = makhachhang;
        this.manhanvien = manhanvien;
        this.ngaylaphoadon = ngaylaphoadon;
        this.ngaynhanhang = ngaynhanhang;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }

    public Date getNgaylaphoadon() {
        return ngaylaphoadon;
    }

    public void setNgaylaphoadon(Date ngaylaphoadon) {
        this.ngaylaphoadon = ngaylaphoadon;
    }

    public Date getNgaynhanhang() {
        return ngaynhanhang;
    }

    public void setNgaynhanhang(Date ngaynhanhang) {
        this.ngaynhanhang = ngaynhanhang;
    }

    @Column(name = "NgayLapHoaDon", nullable = false)
    private Date ngaylaphoadon;

    @Column(name = "NgayNhanHang", nullable = false)
    private Date ngaynhanhang;
}
