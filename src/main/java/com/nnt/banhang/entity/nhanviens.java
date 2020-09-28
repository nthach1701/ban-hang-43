package com.nnt.banhang.entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "nhanviens")
public class nhanviens {
		@Id
	    @Column(name = "MaNV", nullable = false)
	    private String manv;

	    @Column(name = "HoNV", nullable = false)
	    private String honv;

	    @Column(name = "TenNV")
	    private String tennv;

	    @Column(name = "GioiTinh")
	    private String gioitinh;
	    
	    @Column(name = "NgaySinh")
	    private Date ngaysinh;
	    
	    @Column(name = "DiaChi")
	    private String diachi;
	    
	    @Column(name = "DienThoai")
	    private String dienthoai;

	public nhanviens(String manv, String honv, String tennv, String gioitinh, Date ngaysinh, String diachi, String dienthoai) {
		this.manv = manv;
		this.honv = honv;
		this.tennv = tennv;
		this.gioitinh = gioitinh;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.dienthoai = dienthoai;
	}

	public nhanviens() {
	}

	public String getMaNV() {
			return manv;
		}

		public void setMaNV(String maNV) {
			manv = maNV;
		}

		public String getHoNV() {
			return honv;
		}

		public void setHoNV(String hoNV) {
			honv = hoNV;
		}

		public String getTenNV() {
			return tennv;
		}

		public void setTenNV(String tenNV) {
			tennv = tenNV;
		}

		public String getGioiTinh() {
			return gioitinh;
		}

		public void setGioiTinh(String gioiTinh) {
			gioitinh = gioiTinh;
		}

		public Date getNgaySinh() {
			return ngaysinh;
		}

		public void setNgaySinh(Date ngaySinh) {
			ngaysinh = ngaySinh;
		}

		public String getDiaChi() {
			return diachi;
		}

		public void setDiaChi(String diaChi) {
			diachi = diaChi;
		}

	@Override
	public String toString() {
		return "nhanviens{" +
				"manv='" + manv + '\'' +
				", honv='" + honv + '\'' +
				", tennv='" + tennv + '\'' +
				", gioitinh='" + gioitinh + '\'' +
				", ngaysinh=" + ngaysinh +
				", diachi='" + diachi + '\'' +
				", dienthoai='" + dienthoai + '\'' +
				'}';
	}

	public String getDienThoai() {
			return dienthoai;
		}

		public void setDienThoai(String dienThoai) {
			dienthoai = dienThoai;
		}
}
