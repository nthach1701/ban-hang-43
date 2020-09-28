package com.nnt.banhang.repository;

import com.nnt.banhang.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon,String> {
    Optional<HoaDon> findBymahoadon(int maHoaDon);

    List<HoaDon> findAll();

    HoaDon save(HoaDon hoaDon);

    @Transactional
    long deleteBymahoadon(int maHoaDon);

    @Query(nativeQuery = true, value ="select MaHD from hoadons order by MaHD desc LIMIT 1")
    String findMaHDNewest();
}
