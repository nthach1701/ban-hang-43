package com.nnt.banhang.service;

import com.nnt.banhang.entity.SanPham;
import com.nnt.banhang.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImplement implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamService;

    @Override
    public List<SanPham> findAll() {
        return sanPhamService.findAll();
    }

    @Override
    public Optional<SanPham> findOne(String maSanPham) {
        return sanPhamService.findBymasanpham(maSanPham);
    }

    @Override
    public SanPham save(SanPham sanPhamTemp) {
        return sanPhamService.save(sanPhamTemp);
    }

    @Override
    public long delete(String maSanPham) {
        return sanPhamService.deleteBymasanpham(maSanPham);
    }
}
