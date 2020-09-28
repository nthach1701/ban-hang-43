package com.nnt.banhang.repository;
import com.nnt.banhang.entity.nhanviens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BanHangRepository extends JpaRepository<nhanviens, String> {
	Optional<nhanviens> findBymanv(String term);
	
	List<nhanviens> findAll();

	nhanviens save(nhanviens nhanvien);

	@Transactional
	long deleteBymanv(String id);

}
