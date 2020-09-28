package com.nnt.banhang.entity;

import javax.persistence.*;

@Entity
@Table(name = "chitiethoadons")
public class ChiTietHoaDon {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "SoLuong", nullable = false)
    private int soluong;

    @Column(name = "DonGia", nullable = false)
    private double dongia;

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaHD", nullable = false, //
            foreignKey = @ForeignKey(name = "hoadons_chitiethoadons_FK"))
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaSP", nullable = false, //
            foreignKey = @ForeignKey(name = "sanphams_chitiethoadons_FK"))
    private SanPham sanPham;

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
