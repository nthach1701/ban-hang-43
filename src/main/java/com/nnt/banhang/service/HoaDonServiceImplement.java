package com.nnt.banhang.service;

import com.nnt.banhang.entity.HoaDon;
import com.nnt.banhang.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImplement implements HoaDonService{
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public Optional<HoaDon> findOne(int maHoaDon) {
        return hoaDonRepository.findBymahoadon(maHoaDon);
    }

    @Override
    public HoaDon save(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public long delete(int maHoaDon) {
        return hoaDonRepository.deleteBymahoadon(maHoaDon);
    }

    @Override
    public String findMaHDNewest() {
        return hoaDonRepository.findMaHDNewest();
    }
}
