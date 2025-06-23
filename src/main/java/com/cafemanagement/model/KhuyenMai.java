package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "khuyen_mai", schema = "qlcafe")
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_khuyen_mai", nullable = false)
    private Integer maKhuyenMai;

    @NotNull
    @Column(name = "gia_tri_giam", nullable = false, precision = 18, scale = 2)
    private BigDecimal giaTriGiam;

    @Size(max = 50)
    @NotNull
    @Column(name = "loai_khuyen_mai", nullable = false, length = 50)
    private String loaiKhuyenMai;

    @Size(max = 255)
    @Column(name = "mo_ta")
    private String moTa;

    @NotNull
    @Column(name = "ngay_bat_dau", nullable = false)
    private LocalDate ngayBatDau;

    @NotNull
    @Column(name = "ngay_ket_thuc", nullable = false)
    private LocalDate ngayKetThuc;

    @Size(max = 100)
    @NotNull
    @Column(name = "ten_khuyen_mai", nullable = false, length = 100)
    private String tenKhuyenMai;

    @NotNull
    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai = false;

    @OneToMany(mappedBy = "khuyenMai")
    private List<HoaDon> hoaDons;

}