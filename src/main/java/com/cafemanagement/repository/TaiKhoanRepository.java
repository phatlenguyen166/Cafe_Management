package com.cafemanagement.repository;


import com.cafemanagement.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {

    @Query("SELECT MAX(t.id) FROM TaiKhoan t")
    Integer findMaxMaNhanVien();

    boolean existsByTenDangNhap(String tenDangNhap);
}
