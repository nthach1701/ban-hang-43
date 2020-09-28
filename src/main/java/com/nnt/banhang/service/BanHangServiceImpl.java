package com.nnt.banhang.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnt.banhang.entity.nhanviens;
import com.nnt.banhang.repository.BanHangRepository;

@Service
public class BanHangServiceImpl implements BanHangService {

	 @Autowired
	private BanHangRepository banhangRepository;
	
	public List<nhanviens> findAll() {
		// TODO Auto-generated method stub
		return banhangRepository.findAll();
	}


	public Optional<nhanviens> findOne(String id) {
		// TODO Auto-generated method stub
		return banhangRepository.findBymanv(id);
	}

	public nhanviens save(nhanviens nhanvien) {
		// TODO Auto-generated method stub
		return banhangRepository.save(nhanvien);
	}

	public long delete(String id) {
		// TODO Auto-generated method stub
		return banhangRepository.deleteBymanv(id);
	}

}
