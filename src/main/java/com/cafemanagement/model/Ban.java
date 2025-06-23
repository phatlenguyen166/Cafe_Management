package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ban", schema = "qlcafe")
public class Ban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ban", nullable = false)
    private Integer maBan;

    @Size(max = 50)
    @NotNull
    @Column(name = "ten_ban", nullable = false, length = 50)
    private String tenBan;

    @Size(max = 50)
    @NotNull
    @Column(name = "tinh_trang", nullable = false, length = 50)
    private String tinhTrang;

    @OneToMany(mappedBy = "ban", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChiTietDatBan> chiTietDatBans;

}