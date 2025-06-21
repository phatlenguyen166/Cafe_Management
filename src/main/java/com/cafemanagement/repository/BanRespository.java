package com.cafemanagement.repository;


import com.cafemanagement.model.Ban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanRespository extends JpaRepository<Ban, Integer> {


}
