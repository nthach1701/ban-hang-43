package com.nnt.banhang.service;

import com.nnt.banhang.entity.ChiTietHoaDon;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChiTietHoaDonService {
    List<ChiTietHoaDon> findAll();

    Optional<ChiTietHoaDon> findByMaHoaDon(String id);

    ChiTietHoaDon save(ChiTietHoaDon chiTietHoaDon);

    long delete(String id);

    String findIDNewest();
}
