package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "hoa_don", schema = "qlcafe")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hoa_don", nullable = false)
    private Integer id;

    @Column(name = "ngay_gio_tao")
    private Instant ngayGioTao;

    @NotNull
    @Column(name = "tong_tien", nullable = false, precision = 18, scale = 2)
    private BigDecimal tongTien;

    @NotNull
    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_khuyen_mai")
    private KhuyenMai maKhuyenMai;

    @OneToMany(mappedBy = "maHoaDon")
    private Set<ChiTietDatBan> chiTietDatBans = new LinkedHashSet<>();

    @OneToMany(mappedBy = "maHoaDon")
    private Set<ChiTietHoaDon> chiTietHoaDons = new LinkedHashSet<>();

}