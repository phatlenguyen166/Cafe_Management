package com.cafemanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "nhan_vien", schema = "qlcafe")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maNhanVien;

    private String hoTen;
    private String diaChi;
    private String soDienThoai;
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "ma_chuc_vu")
    private ChucVu chucVu;

    @OneToOne
    @JoinColumn(name = "ma_tai_khoan")
    private TaiKhoan taiKhoan;

    @OneToMany(mappedBy = "nhanVien")
    private List<ChiTietDatBan> chiTietDatBans;

    @OneToMany(mappedBy = "nhanVien")
    private List<DonNhap> donNhaps;

    @OneToMany(mappedBy = "nhanVien")
    private List<DonXuat> donXuats;
}