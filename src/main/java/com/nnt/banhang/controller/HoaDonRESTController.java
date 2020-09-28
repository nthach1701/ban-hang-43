package com.nnt.banhang.controller;
import com.google.gson.Gson;
import com.nnt.banhang.entity.HoaDon;
import com.nnt.banhang.entity.nhanviens;
import com.nnt.banhang.exception.ResourceNotFoundException;
import com.nnt.banhang.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class HoaDonRESTController {

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/hoadon/all")
    public String DanhSachHoaDon(){
        Gson gson = new Gson();
        List<HoaDon> iterable = hoaDonService.findAll();
        return gson.toJson(iterable);
    }

    @PostMapping(value = "/hoadon",produces = MediaType.APPLICATION_JSON_VALUE)
    public HoaDon saveNhanVien(@RequestBody HoaDon hoaDon){
        HoaDon saveResult = hoaDonService.save(hoaDon);
        return saveResult;
    }

    @GetMapping(value = "/hoadon/")
    public  HoaDon findByID(@RequestParam("mahoadon")String mahoadon) throws ResourceNotFoundException {
        HoaDon result = hoaDonService.findOne(Integer.parseInt(mahoadon)).orElseThrow(() -> new ResourceNotFoundException("Hoa don not found for this id :: " + mahoadon));
        return result;
    }

    @PutMapping(value = "/hoadon/")
    public String saveEdit(@RequestParam("mahoadon")String mahoadon,@RequestBody HoaDon hoaDon) throws ResourceNotFoundException {
        HoaDon result = hoaDonService.findOne(Integer.parseInt(mahoadon)).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + mahoadon));
        result.setMakhachhang(hoaDon.getMakhachhang());
        result.setManhanvien(hoaDon.getManhanvien());
        result.setNgaylaphoadon(hoaDon.getNgaylaphoadon());
        result.setNgaynhanhang(hoaDon.getNgaynhanhang());
        HoaDon save = hoaDonService.save(result);
        Gson gson = new Gson();
        return gson.toJson(save);
    }

    @DeleteMapping(value = "/hoadon/")
    public String deleteNhanVien(@RequestParam(name = "mahoadon") String mahoadon){
        long delete = hoaDonService.delete(Integer.parseInt(mahoadon));
        String result = "";
        if(delete != 0){
            result += "success";
        }else{
            result += "fail";
        }
        return result;
    }

    @GetMapping("/viewSessionData")
    public java.util.Map<String, Integer> start(HttpServletRequest request) {
        Integer integer = (Integer) request.getSession()
                .getAttribute("hitCounter");        // create session if not exists and get attribute
        if (integer == null) {
            integer = new Integer(0);
            integer++;
            request.getSession().setAttribute("hitCounter", integer);           // replace session attribute
        } else {
            integer++;
            request.getSession().setAttribute("hitCounter", integer);            // replace session attribute
        }
        java.util.Map<String, Integer> hitCounter = new HashMap<>();
        hitCounter.put("Hit Counter", integer);
        return hitCounter;
    }
}
