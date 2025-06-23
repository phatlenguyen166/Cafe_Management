package com.cafemanagement.service;

import com.cafemanagement.dto.request.ChiTietDatBanRequest;
import com.cafemanagement.model.ChiTietDatBan;

public interface ChiTietDatBanService {
    ChiTietDatBan createChiTietDatBan(ChiTietDatBanRequest request);

    void cancelDatBan(Integer maBan);
}
