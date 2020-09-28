package com.nnt.banhang.repository;

import com.nnt.banhang.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, String> {
    Optional<SanPham> findBymasanpham(String maSanPham);

    List<SanPham> findAll();

    SanPham save(SanPham sanPham);

    @Transactional
    long deleteBymasanpham(String maSanPham);
}
