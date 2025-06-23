package com.cafemanagement.repository;

import com.cafemanagement.model.Ban;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanRepository extends JpaRepository<Ban, Integer> {

    List<Ban> findByTinhTrang(String tinhTrang);
}
