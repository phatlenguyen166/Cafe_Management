package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "don_vi_tinh", schema = "qlcafe")
public class DonViTinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_vi_tinh", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "ten_don_vi", nullable = false, length = 50)
    private String tenDonVi;

    @OneToMany(mappedBy = "maDonViTinh")
    private Set<HangHoa> hangHoas = new LinkedHashSet<>();

}