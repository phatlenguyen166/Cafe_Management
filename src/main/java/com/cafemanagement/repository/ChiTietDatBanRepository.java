package com.cafemanagement.repository;

import com.cafemanagement.model.ChiTietDatBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietDatBanRepository extends JpaRepository<ChiTietDatBan, Integer> {

    ChiTietDatBan findByBanMaBanAndHoaDonIsNull(Integer maBanId);

    @Query("SELECT c FROM ChiTietDatBan c WHERE c.sdtKhachHang = :sdt")
    List<ChiTietDatBan> findByCustomerPhone(@Param("sdt") String sdt);

    @Query("SELECT c FROM ChiTietDatBan c WHERE c.tenKhachHang LIKE %:ten%")
    List<ChiTietDatBan> findByCustomerName(@Param("ten") String ten);

    ChiTietDatBan findByBanMaBanAndHoaDonTrangThai(Integer maBanId, Boolean trangThaiHoaDon);
}
