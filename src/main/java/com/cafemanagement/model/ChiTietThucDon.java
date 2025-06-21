package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "chi_tiet_thuc_don", schema = "qlcafe")
public class ChiTietThucDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_chi_tiet_thuc_don", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "don_vi_tinh", length = 50)
    private String donViTinh;

    @NotNull
    @Column(name = "khoi_luong", nullable = false, precision = 18, scale = 2)
    private BigDecimal khoiLuong;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_hang_hoa", nullable = false)
    private HangHoa maHangHoa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_thuc_don", nullable = false)
    private ThucDon maThucDon;

}