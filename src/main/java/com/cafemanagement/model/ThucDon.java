package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "thuc_don", schema = "qlcafe")
public class ThucDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_thuc_don", nullable = false)
    private Integer maThucDon;

    @NotNull
    @Column(name = "gia_tien_hien_tai", nullable = false, precision = 18, scale = 2)
    private BigDecimal giaTienHienTai;

    @NotNull
    @ColumnDefault("b'0'")
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Size(max = 50)
    @NotNull
    @Column(name = "loai_mon", nullable = false, length = 50)
    private String loaiMon;

    @Size(max = 100)
    @NotNull
    @Column(name = "ten_mon", nullable = false, length = 100)
    private String tenMon;

    @OneToMany(mappedBy = "thucDon")
    private List<ChiTietHoaDon> chiTietHoaDons;

    @OneToMany(mappedBy = "thucDon")
    private List<ChiTietThucDon> chiTietThucDons;
}