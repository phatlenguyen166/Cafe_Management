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
    private Integer id;

    @NotNull
    @Column(name = "ngay_nhap", nullable = false)
    private LocalDate ngayNhap;

    @NotNull
    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @NotNull
    @Column(name = "tong_tien", nullable = false, precision = 18, scale = 2)
    private BigDecimal tongTien;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_hang_hoa", nullable = false)
    private HangHoa maHangHoa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_nhan_vien", nullable = false)
    private NhanVien maNhanVien;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_thiet_bi", nullable = false)
    private ThietBi maThietBi;

}