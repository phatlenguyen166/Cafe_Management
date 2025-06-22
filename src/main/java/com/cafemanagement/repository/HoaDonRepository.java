package com.cafemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafemanagement.model.HoaDon;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {

    // Define any custom query methods if needed
    // For example:
    // List<HoaDon> findByStatus(String status);
    // List<HoaDon> findByDateBetween(Date startDate, Date endDate);

}
