package com.cafemanagement.repository;

import com.cafemanagement.dto.response.LoginResponse;
import com.cafemanagement.dto.response.NhanVienResponse;
import com.cafemanagement.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {

       @Query(value = """
                     SELECT nv.ma_nhan_vien,
                            nv.ho_ten,
                               cv.ten_chuc_vu,
                               nv.dia_chi,
                               nv.so_dien_thoai,
                               cv.Luong,
                               tk.ten_dang_nhap,
                               tk.mat_khau
                        FROM nhan_vien nv
                        INNER JOIN chuc_vu cv ON nv.ma_chuc_vu = cv.ma_chuc_vu
                        INNER JOIN tai_khoan tk ON nv.ma_tai_khoan = tk.ma_tai_khoan
                        WHERE tk.ten_dang_nhap = :username AND nv.is_deleted = 0
                        """, nativeQuery = true)
       LoginResponse findThongTinDangNhap(@Param("username") String username);

       @Query(value = """
                     SELECT nv.ma_nhan_vien as maNhanVien,
                            nv.ho_ten as hoTen,
                            cv.ten_chuc_vu as tenChucVu,
                            cv.Luong as luong
                     FROM nhan_vien nv
                     JOIN chuc_vu cv ON nv.ma_chuc_vu = cv.ma_chuc_vu
                     WHERE nv.is_deleted = 0
                     """, nativeQuery = true)
       List<NhanVienResponse> getListNhanVien();

       @Query(value = """
                     SELECT nv.ma_nhan_vien as maNhanVien,
                            nv.ho_ten as hoTen,
                            cv.ten_chuc_vu as tenChucVu,
                            cv.Luong as luong
                     FROM nhan_vien nv
                     JOIN chuc_vu cv ON nv.ma_chuc_vu = cv.ma_chuc_vu
                     WHERE nv.is_deleted = 0
                     AND LOWER(nv.ho_ten) LIKE LOWER(CONCAT('%', :keyword, '%'))
                     """, nativeQuery = true)
       List<NhanVienResponse> searchNhanVienByName(@Param("keyword") String keyword);

       @Query("SELECT MAX(n.id) FROM NhanVien n")
       Integer findMaxMaNhanVien();

       @Query(value = """
                     SELECT nv.ma_nhan_vien as maNhanVien,
                            nv.ho_ten as hoTen,
                            cv.ten_chuc_vu as tenChucVu,
                            cv.Luong as luong
                     FROM nhan_vien nv
                     JOIN chuc_vu cv ON nv.ma_chuc_vu = cv.ma_chuc_vu
                     WHERE nv.ma_nhan_vien = :maNhanVien AND nv.is_deleted = 0
                     """, nativeQuery = true)
       NhanVienResponse getNhanVienResponseById(@Param("maNhanVien") Integer maNhanVien);
}
