package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "don_nhap", schema = "qlcafe")
public class DonNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_nhap", nullable = false)
    private Integer maDonNhap;

    @NotNull
    @Column(name = "ngay_nhap", nullable = false)
    private LocalDate ngayNhap;

    @NotNull
    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @NotNull
    @Column(name = "tong_tien", nullable = false, precision = 18, scale = 2)
    private BigDecimal tongTien;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "ma_hang_hoa")
    private HangHoa hangHoa;

    @ManyToOne
    @JoinColumn(name = "ma_thiet_bi")
    private ThietBi thietBi;

}