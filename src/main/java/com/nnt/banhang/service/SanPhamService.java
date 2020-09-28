package com.nnt.banhang.service;

import com.nnt.banhang.entity.SanPham;
import com.nnt.banhang.entity.nhanviens;

import java.util.List;
import java.util.Optional;

public interface SanPhamService {
    List<SanPham> findAll();

    Optional<SanPham> findOne(String maSanPham);

    SanPham save(SanPham sanPhamTemp);

    long delete(String id);
}
