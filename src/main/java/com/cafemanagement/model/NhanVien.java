package com.cafemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "nhan_vien", schema = "qlcafe")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nhan_vien", nullable = false)
    private Integer id;

    @Size(max = 200)
    @Column(name = "dia_chi", length = 200)
    private String diaChi;

    @Size(max = 100)
    @NotNull
    @Column(name = "ho_ten", nullable = false, length = 100)
    private String hoTen;

    @NotNull
    @ColumnDefault("b'0'")
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Size(max = 15)
    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_chuc_vu", nullable = false)
    private ChucVu maChucVu;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_tai_khoan", nullable = false)
    private TaiKhoan maTaiKhoan;

    @OneToMany(mappedBy = "maNhanVien")
    private Set<ChiTietDatBan> chiTietDatBans = new LinkedHashSet<>();

    @OneToMany(mappedBy = "maNhanVien")
    private Set<DonNhap> donNhaps = new LinkedHashSet<>();

    @OneToMany(mappedBy = "maNhanVien")
    private Set<DonXuat> donXuats = new LinkedHashSet<>();

}