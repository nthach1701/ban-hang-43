package com.nnt.banhang.controller;

import com.google.gson.Gson;
import com.nnt.banhang.entity.nhanviens;
import com.nnt.banhang.exception.ResourceNotFoundException;
import com.nnt.banhang.service.BanHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NhanVienRESTController {
    @Autowired
    private BanHangService banhangService;

    @GetMapping("/nhanvien/all")
    public String list(Model model) {
        Gson gson = new Gson();
        List<nhanviens> iterable = banhangService.findAll();
        return gson.toJson(iterable);
    }

    @PostMapping(value = "/nhanvien",produces = MediaType.APPLICATION_JSON_VALUE)
    public nhanviens saveNhanVien(@RequestBody nhanviens nhanvien){
        nhanviens saveResult = banhangService.save(nhanvien);
        return saveResult;
    }

    @GetMapping(value = "/nhanvien/")
    public  nhanviens findByID(@RequestParam("manv")String manv) throws ResourceNotFoundException {
        nhanviens result = banhangService.findOne(manv).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + manv));
        return result;
    }

    @PutMapping(value = "/nhanvien/")
    public String saveEdit(@RequestParam("manv")String manv,@RequestBody nhanviens nhanvien) throws ResourceNotFoundException {
        nhanviens result = banhangService.findOne(manv).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + manv));
        result.setHoNV(nhanvien.getHoNV());
        result.setTenNV(nhanvien.getTenNV());
        result.setGioiTinh(nhanvien.getGioiTinh());
        result.setNgaySinh(nhanvien.getNgaySinh());
        result.setDiaChi(nhanvien.getDiaChi());
        result.setDienThoai(nhanvien.getDienThoai());
        nhanviens save = banhangService.save(result);
        Gson gson = new Gson();
        return gson.toJson(save);
    }

    @DeleteMapping(value = "/nhanvien/")
    public String deleteNhanVien(@RequestParam(name = "manv") String manv){
        long delete = banhangService.delete(manv);
        String result = "";
        if(delete != 0){
           result += "success";
        }else{
            result += "fail";
        }
        return result;
    }
}
