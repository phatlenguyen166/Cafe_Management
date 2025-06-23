package com.cafemanagement.service.impl;

import com.cafemanagement.model.Ban;
import com.cafemanagement.repository.BanRepository;
import com.cafemanagement.service.BanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BanServiceImpl implements BanService {

    private final BanRepository banRespository;

    @Override
    public List<Ban> getAllBans() {
        return banRespository.findAll();
    }

    @Override
    public List<Ban> getByTrangThai(String trangThai) {
        return banRespository.findByTinhTrang(trangThai);
    }

    @Override
    public void chuyenBan(Integer maBanCu, Integer maBanMoi) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chuyenBan'");
    }

}
