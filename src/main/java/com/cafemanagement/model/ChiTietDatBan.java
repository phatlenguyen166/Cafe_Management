package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "chi_tiet_dat_ban", schema = "qlcafe")
public class ChiTietDatBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_chi_tiet_dat_ban", nullable = false)
    private Integer maChiTietDatBan;

    @Column(name = "ngay_gio_dat")
    private Instant ngayGioDat;

    @Size(max = 15)
    @Column(name = "sdt_khach_hang", length = 15)
    private String sdtKhachHang;

    @Size(max = 100)
    @NotNull
    @Column(name = "ten_khach_hang", nullable = false, length = 100)
    private String tenKhachHang;

    @ManyToOne
    @JoinColumn(name = "ma_ban")
    private Ban ban;

    @ManyToOne
    @JoinColumn(name = "ma_hoa_don")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien")
    private NhanVien nhanVien;

}