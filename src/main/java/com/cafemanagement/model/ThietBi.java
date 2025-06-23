package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "thiet_bi", schema = "qlcafe")
public class ThietBi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_thiet_bi", nullable = false)
    private Integer maThietBi;

    @NotNull
    @Column(name = "don_gia_mua", nullable = false, precision = 18, scale = 2)
    private BigDecimal donGiaMua;

    @Size(max = 255)
    @Column(name = "ghi_chu")
    private String ghiChu;

    @NotNull
    @Column(name = "ngay_mua", nullable = false)
    private LocalDate ngayMua;

    @NotNull
    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @Size(max = 100)
    @NotNull
    @Column(name = "ten_thiet_bi", nullable = false, length = 100)
    private String tenThietBi;

    @OneToMany(mappedBy = "thietBi")
    private List<DonNhap> donNhaps;
}