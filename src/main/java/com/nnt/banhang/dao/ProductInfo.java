package com.nnt.banhang.dao;

import com.nnt.banhang.entity.SanPham;

public class ProductInfo {
    private String code;
    private String name;
    private double price;

    public ProductInfo() {
    }

    public ProductInfo(SanPham product) {
        this.code = product.getMasanpham();
        this.name = product.getTensanpham();
        this.price = product.getDongia();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
