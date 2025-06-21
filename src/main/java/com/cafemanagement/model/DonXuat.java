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
@Table(name = "don_xuat", schema = "qlcafe")
public class DonXuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_xuat", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "ngay_xuat", nullable = false)
    private LocalDate ngayXuat;

    @NotNull
    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @NotNull
    @Column(name = "tong_tien_xuat", nullable = false, precision = 18, scale = 2)
    private BigDecimal tongTienXuat;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_hang_hoa", nullable = false)
    private HangHoa maHangHoa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_nhan_vien", nullable = false)
    private NhanVien maNhanVien;

}