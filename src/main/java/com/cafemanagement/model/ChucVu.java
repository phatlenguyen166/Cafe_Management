package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "chuc_vu", schema = "qlcafe")
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_chuc_vu", nullable = false)
    private Integer maChucVu;

    @NotNull
    @Column(name = "luong", nullable = false, precision = 18, scale = 2)
    private BigDecimal luong;

    @Size(max = 100)
    @NotNull
    @Column(name = "ten_chuc_vu", nullable = false, length = 100)
    private String tenChucVu;

    @OneToMany(mappedBy = "chucVu")
    private List<NhanVien> nhanViens;

}