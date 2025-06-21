package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "chi_tieu", schema = "qlcafe")
public class ChiTieu {
    @Id
    @Column(name = "ma_chi_tieu", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "ngay_chi", nullable = false)
    private LocalDate ngayChi;

    @NotNull
    @Column(name = "so_tien", nullable = false, precision = 18, scale = 2)
    private BigDecimal soTien;

    @Size(max = 100)
    @Column(name = "ten_khoan_chi", length = 100)
    private String tenKhoanChi;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_tai_khoan", nullable = false)
    private TaiKhoan maTaiKhoan;

}