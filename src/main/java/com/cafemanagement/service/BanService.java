package com.cafemanagement.service;

import com.cafemanagement.model.Ban;

import java.util.List;

public interface BanService {
    List<Ban> getAllBans();

    List<Ban> getByTrangThai(String trangThai);

    void chuyenBan(Integer maBanCu, Integer maBanMoi);
}
