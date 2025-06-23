package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tai_khoan", schema = "qlcafe")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_tai_khoan", nullable = false)
    private Integer maTaiKhoan;

    @Size(max = 255)
    @Column(name = "anh")
    private String anh;

    @Size(max = 255)
    @NotNull
    @Column(name = "mat_khau", nullable = false)
    private String matKhau;

    @Size(max = 255)
    @Column(name = "quyen_han")
    private String quyenHan;

    @Size(max = 50)
    @NotNull
    @Column(name = "ten_dang_nhap", nullable = false, length = 50)
    private String tenDangNhap;

    @OneToMany(mappedBy = "taiKhoan")
    private List<ChiTieu> chiTieus;

    @OneToOne(mappedBy = "taiKhoan")
    private NhanVien nhanVien;
}