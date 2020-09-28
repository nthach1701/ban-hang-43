package com.nnt.banhang.repository;

import com.nnt.banhang.entity.ChiTietHoaDon;
import com.nnt.banhang.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, String> {
    Optional<ChiTietHoaDon> findByhoaDon_mahoadon(String term);

    List<ChiTietHoaDon> findAll();

    ChiTietHoaDon save(ChiTietHoaDon chiTietHoaDon);

    @Transactional
    long deleteByid(String id);

    @Query(nativeQuery = true, value ="select ID from chitiethoadons order by ID desc LIMIT 1")
    String findIDNewest();
}
