package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "chi_tiet_hoa_don", schema = "qlcafe")
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_chi_tiet_hoa_don", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "gia_tai_thoi_diem_ban", nullable = false, precision = 18, scale = 2)
    private BigDecimal giaTaiThoiDiemBan;

    @NotNull
    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @NotNull
    @Column(name = "thanh_tien", nullable = false, precision = 18, scale = 2)
    private BigDecimal thanhTien;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_hoa_don", nullable = false)
    private HoaDon maHoaDon;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_thuc_don", nullable = false)
    private ThucDon maThucDon;

}