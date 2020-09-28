package com.nnt.banhang.service;

import java.util.List;
import java.util.Optional;

import com.nnt.banhang.entity.nhanviens;

public interface  BanHangService {
	 	List<nhanviens> findAll();

	    Optional<nhanviens> findOne(String id);

	    nhanviens save(nhanviens contact);

	    long delete(String id);
}
