package com.nnt.banhang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sanphams")
public class SanPham {
    @Id
    @Column(name = "MaSP", nullable = false)
    private String masanpham;

    @Column(name = "TenSP", nullable = false)
    private String tensanpham;

    @Column(name = "MaLoaiSP", nullable = false)
    private String maloaisanpham;

    @Column(name = "MaNhanHieu", nullable = false)
    private String manhanhieu;

    @Column(name = "DonViSP", nullable = false)
    private String donvisanpham;

    @Column(name = "DonGia", nullable = false)
    private Double dongia;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Column(name = "file")
    private String file;

    @Override
    public String toString() {
        return "SanPham{" +
                "masanpham='" + masanpham + '\'' +
                ", tensanpham='" + tensanpham + '\'' +
                ", maloaisanpham='" + maloaisanpham + '\'' +
                ", manhanhieu='" + manhanhieu + '\'' +
                ", donvisanpham='" + donvisanpham + '\'' +
                ", dongia='" + dongia + '\'' +
                '}';
    }

    public SanPham() {
    }

    public SanPham(String masanpham, String tensanpham, String maloaisanpham, String manhanhieu, String donvisanpham, Double dongia, String file) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.maloaisanpham = maloaisanpham;
        this.manhanhieu = manhanhieu;
        this.donvisanpham = donvisanpham;
        this.dongia = dongia;
        this.file = file;
    }

    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getMaloaisanpham() {
        return maloaisanpham;
    }

    public void setMaloaisanpham(String maloaisanpham) {
        this.maloaisanpham = maloaisanpham;
    }

    public String getManhanhieu() {
        return manhanhieu;
    }

    public void setManhanhieu(String manhanhieu) {
        this.manhanhieu = manhanhieu;
    }

    public String getDonvisanpham() {
        return donvisanpham;
    }

    public void setDonvisanpham(String donvisanpham) {
        this.donvisanpham = donvisanpham;
    }

    public Double getDongia() {
        return dongia;
    }

    public void setDongia(Double dongia) {
        this.dongia = dongia;
    }
}
