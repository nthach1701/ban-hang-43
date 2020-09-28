package com.nnt.banhang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "khachhangs")
public class KhachHang {
    @Id
    @Column(name = "MaKH", nullable = false)
    private String makhachhang;

    @Column(name = "HoTenKH", nullable = false)
    private String hotenkhachhang;

    @Column(name = "DiaChi", nullable = false)
    private String diachi;

    @Column(name = "DienThoai", nullable = false)
    private String dienthoai;

    @Override
    public String toString() {
        return "KhachHang{" +
                "makhachhang='" + makhachhang + '\'' +
                ", hotenkhachhang='" + hotenkhachhang + '\'' +
                ", diachi='" + diachi + '\'' +
                ", dienthoai='" + dienthoai + '\'' +
                '}';
    }

    public KhachHang(String makhachhang, String hotenkhachhang, String diachi, String dienthoai) {
        this.makhachhang = makhachhang;
        this.hotenkhachhang = hotenkhachhang;
        this.diachi = diachi;
        this.dienthoai = dienthoai;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getHotenkhachhang() {
        return hotenkhachhang;
    }

    public void setHotenkhachhang(String hotenkhachhang) {
        this.hotenkhachhang = hotenkhachhang;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }
}
