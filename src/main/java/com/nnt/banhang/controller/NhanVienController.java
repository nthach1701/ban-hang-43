package com.nnt.banhang.controller;

import com.nnt.banhang.entity.HoaDon;
import com.nnt.banhang.entity.SanPham;
import com.nnt.banhang.entity.nhanviens;
import com.nnt.banhang.service.HoaDonService;
import com.nnt.banhang.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.nnt.banhang.service.BanHangService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class NhanVienController {
	 	@Autowired
	    private BanHangService banhangService;

		@Autowired
		private SanPhamService sanPhamService;

		@Autowired
		private HoaDonService hoaDonService;

	    @GetMapping("/nhanvien")
	    public String list(Model model) {
			model.addAttribute("nhanvien", new nhanviens());
	        model.addAttribute("contacts", banhangService.findAll());
	        return "list";
	    }

		@GetMapping("/sanpham")
		public String sanPhamPage(Model model) {
			model.addAttribute("sanpham", new SanPham());
			model.addAttribute("contacts", sanPhamService.findAll());
			return "sanPhamPage";
		}

		@GetMapping("/thanhtoan")
		public String thanhToanPage(Model model) {
			model.addAttribute("hoadon", new HoaDon());
			model.addAttribute("contacts", hoaDonService.findAll());
			return "thanhToanPage";
		}
}
