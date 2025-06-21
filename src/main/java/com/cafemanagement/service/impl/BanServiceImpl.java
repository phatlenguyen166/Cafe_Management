package com.cafemanagement.service.impl;

import com.cafemanagement.model.Ban;
import com.cafemanagement.repository.BanRespository;
import com.cafemanagement.service.BanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BanServiceImpl implements BanService {

    private final BanRespository banRespository;

    @Override
    public List<Ban> getAllBans() {
        return banRespository.findAll();
    }

}
