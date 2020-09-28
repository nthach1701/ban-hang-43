package com.nnt.banhang.controller;


import com.google.gson.Gson;
import com.nnt.banhang.entity.SanPham;
import com.nnt.banhang.exception.ResourceNotFoundException;
import com.nnt.banhang.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@RestController
public class SanPhamRESTController{
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/sanpham/all")
    public String list(Model model) {
        Gson gson = new Gson();
        List<SanPham> listSanPham = sanPhamService.findAll();
        return gson.toJson(listSanPham);
    }

    @PostMapping(value = "/sanpham")
    public SanPham saveSanPham(@RequestBody SanPham sanPham){
        SanPham saveResult = sanPhamService.save(sanPham);
        return saveResult;
    }

    @GetMapping(value = "/sanpham/")
    public  SanPham findByID(@RequestParam("masanpham")String maSanPham) throws ResourceNotFoundException {
        SanPham result = sanPhamService.findOne(maSanPham).orElseThrow(() -> new ResourceNotFoundException("SanPham not found for this id :: " + maSanPham));
        return result;
    }

    @PutMapping(value = "/sanpham/")
    public String editSanPham(@RequestParam("masanpham")String maSanPham,@RequestBody SanPham sanPham) throws ResourceNotFoundException {
        SanPham result = sanPhamService.findOne(maSanPham).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + maSanPham));
        result.setTensanpham(sanPham.getTensanpham());
        result.setMaloaisanpham(sanPham.getMaloaisanpham());
        result.setManhanhieu(sanPham.getManhanhieu());
        result.setDonvisanpham(sanPham.getDonvisanpham());
        result.setDongia(sanPham.getDongia());
        SanPham save = sanPhamService.save(result);
        Gson gson = new Gson();
        return gson.toJson(save);
    }

    @DeleteMapping(value = "/sanpham/")
    public String deleteSanPham(@RequestParam(name = "masanpham") String maSanPham){
        long delete = sanPhamService.delete(maSanPham);
        String result = "";
        if(delete != 0){
            result += "success";
        }else{
            result += "fail";
        }
        return result;
    }
}
