package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "hang_hoa", schema = "qlcafe")
public class HangHoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hang_hoa", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "don_gia", nullable = false, precision = 18, scale = 2)
    private BigDecimal donGia;

    @NotNull
    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @Size(max = 100)
    @NotNull
    @Column(name = "ten_hang_hoa", nullable = false, length = 100)
    private String tenHangHoa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_don_vi_tinh", nullable = false)
    private DonViTinh maDonViTinh;

    @OneToMany(mappedBy = "maHangHoa")
    private Set<ChiTietThucDon> chiTietThucDons = new LinkedHashSet<>();

    @OneToMany(mappedBy = "maHangHoa")
    private Set<DonNhap> donNhaps = new LinkedHashSet<>();

    @OneToMany(mappedBy = "maHangHoa")
    private Set<DonXuat> donXuats = new LinkedHashSet<>();

}