package com.nnt.banhang.service;

import com.nnt.banhang.entity.HoaDon;

import java.util.List;
import java.util.Optional;

public interface HoaDonService {
    List<HoaDon> findAll();

    Optional<HoaDon> findOne(int maHoaDon);

    HoaDon save(HoaDon hoaDon);

    long delete(int maHoaDon);

    String findMaHDNewest();
}
