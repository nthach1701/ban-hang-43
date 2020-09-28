package com.nnt.banhang.service;

import com.nnt.banhang.entity.ChiTietHoaDon;
import com.nnt.banhang.repository.ChiTietHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChiTietHoaDonServiceImplement implements ChiTietHoaDonService{

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Override
    public List<ChiTietHoaDon> findAll() {
        return chiTietHoaDonRepository.findAll();
    }

    @Override
    public Optional<ChiTietHoaDon> findByMaHoaDon(String mahoadon) {
        return chiTietHoaDonRepository.findByhoaDon_mahoadon(mahoadon);
    }

    @Override
    public ChiTietHoaDon save(ChiTietHoaDon chiTietHoaDon) {
        return chiTietHoaDonRepository.save(chiTietHoaDon);
    }

    @Override
    public long delete(String id) {
        return chiTietHoaDonRepository.deleteByid(id);
    }

    @Override
    public String findIDNewest() {
        return chiTietHoaDonRepository.findIDNewest();
    }
}
