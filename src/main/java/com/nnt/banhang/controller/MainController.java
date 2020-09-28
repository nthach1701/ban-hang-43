package com.nnt.banhang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.nnt.banhang.dao.CartInfo;
import com.nnt.banhang.dao.CartLineInfo;
import com.nnt.banhang.dao.CustomerInfo;
import com.nnt.banhang.dao.ProductInfo;
import com.nnt.banhang.entity.ChiTietHoaDon;
import com.nnt.banhang.entity.HoaDon;
import com.nnt.banhang.entity.SanPham;
import com.nnt.banhang.entity.nhanviens;
import com.nnt.banhang.exception.ResourceNotFoundException;
import com.nnt.banhang.form.CustomerForm;
import com.nnt.banhang.service.*;
import com.nnt.banhang.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private FireBaseService fireBaseService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private ChiTietHoaDonService chiTietHoaDonService;

    @Autowired
    private BanHangService banHangService;

    @GetMapping("/")
    public String index(Model model) {
        List<SanPham> danhSachSanPham = sanPhamService.findAll();
        model.addAttribute("paginationProducts", danhSachSanPham);
        return "index";
    }

    @GetMapping("/admin") 
    public String admin() {
        return "admin";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/login") 
    public String getLogin() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @PostMapping("/upload")
    public String[] upload(@RequestParam("file") MultipartFile file) throws Exception{
        return fireBaseService.uploadFile(file);
    }

    @RequestMapping({ "/buyProduct" })
    public String listProductHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "masanpham", defaultValue = "") String code) {

        Optional<SanPham> sanPham = null;
        if (code != null && code.length() > 0) {
            sanPham = sanPhamService.findOne(code);
        }
        if (sanPham != null) {

            //
            CartInfo cartInfo = Utils.getCartInSession(request);

            ProductInfo productInfo = new ProductInfo(sanPham.get());

            cartInfo.addProduct(productInfo, 1);
        }

        return "redirect:/shoppingCart";
    }

    // POST: Cập nhập số lượng cho các sản phẩm đã mua.
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
                                        Model model, //
                                        @ModelAttribute("cartForm") CartInfo cartForm) {

        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);

        return "redirect:/shoppingCart";
    }

    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);
        if(myCart.getOrderNum()==0)
            return "redirect:/";
        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }

    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

        CartInfo cartInfo = Utils.getCartInSession(request);

        if (cartInfo.isEmpty()) {

            return "redirect:/shoppingCart";
        }
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();

        CustomerForm customerForm = new CustomerForm(customerInfo);

        model.addAttribute("customerForm", customerForm);

        return "shoppingCartCustomer";
    }

    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request, //
                                           Model model, //
                                           @ModelAttribute("customerForm") CustomerForm customerForm, //
                                           BindingResult result, //
                                           final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            customerForm.setValid(false);
            // Forward tới trang nhập lại.
            return "shoppingCartCustomer";
        }

        customerForm.setValid(true);
        CartInfo cartInfo = Utils.getCartInSession(request);
        CustomerInfo customerInfo = new CustomerInfo(customerForm);
        cartInfo.setCustomerInfo(customerInfo);

        return "redirect:/shoppingCartConfirmation";
    }

    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);

        if (cartInfo == null || cartInfo.isEmpty()) {

            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {

            return "redirect:/shoppingCartCustomer";
        }
        model.addAttribute("myCart", cartInfo);

        return "shoppingCartConfirmation";
    }

    // POST: Gửi đơn hàng (Save).
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)

    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model,@RequestParam(value = "manhanvien", defaultValue = "")String manhanvien) throws ResourceNotFoundException {


        CartInfo cartInfo = Utils.getCartInSession(request);

        if (cartInfo.isEmpty()) {

            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {

            return "redirect:/shoppingCartCustomer";
        }
        nhanviens result = banHangService.findOne(manhanvien).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + manhanvien));
        try {

            HoaDon hoaDon = new HoaDon();
            java.util.Date DateTmp = new java.util.Date();
            Date date = new Date(DateTmp.getTime());
            String dateTemp = date.toString().replace("-","");
            java.util.Date DateTmp3 = new SimpleDateFormat("yyyyMMdd").parse(dateTemp);
            date = new Date(DateTmp3.getTime());
            String MaHoaDon = hoaDonService.findMaHDNewest();
            java.util.Date DateTmp2 = new SimpleDateFormat("yyyyMMdd").parse(MaHoaDon.substring(0,8));
            Date date2 = new Date(DateTmp2.getTime());
            if(date.compareTo(date2)==0){
                Integer mahoadontieptuc = Integer.parseInt(MaHoaDon.substring(8));
                mahoadontieptuc++;
                hoaDon.setMahoadon(Integer.parseInt(MaHoaDon.substring(0,8).concat(mahoadontieptuc.toString()).replace("-","")));
            }else{
                String temp = date.toString().replace("-","").concat("1");
                hoaDon.setMahoadon(Integer.parseInt(temp));
            }
            hoaDon.setNgaylaphoadon(date);
            hoaDon.setNgaynhanhang(date);
            hoaDon.setMakhachhang(cartInfo.getCustomerInfo().getName());
            hoaDon.setManhanvien(result.getMaNV());
            hoaDon = hoaDonService.save(hoaDon);
            int i = Integer.parseInt(chiTietHoaDonService.findIDNewest().substring(16));
            for(CartLineInfo cartLineInfo : cartInfo.getCartLines()){
                i++;
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                SanPham sanPhamTmp = sanPhamService.findOne(cartLineInfo.getProductInfo().getCode()).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + cartLineInfo.getProductInfo().getCode()));
                chiTietHoaDon.setHoaDon(hoaDon);
                chiTietHoaDon.setSanPham(sanPhamTmp);
                chiTietHoaDon.setDongia(cartLineInfo.getProductInfo().getPrice());
                chiTietHoaDon.setId(hoaDon.getMahoadon()+"details"+ i);
                chiTietHoaDon.setSoluong(cartLineInfo.getQuantity());
                chiTietHoaDon = chiTietHoaDonService.save(chiTietHoaDon);
            }
            cartInfo.setOrderNum(hoaDon.getMahoadon());
        } catch (Exception e) {
            return "shoppingCartConfirmation";
        }

        // Xóa giỏ hàng khỏi session.
        Utils.removeCartInSession(request);

        // Lưu thông tin đơn hàng cuối đã xác nhận mua.
        Utils.storeLastOrderedCartInSession(request, cartInfo);

        return "redirect:/shoppingCartFinalize";
    }

    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {

        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);

        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }
        model.addAttribute("lastOrderedCart", lastOrderedCart);
        return "shoppingCartFinalize";
    }
}