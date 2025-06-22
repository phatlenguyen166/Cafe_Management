package com.cafemanagement.service;

import com.cafemanagement.dto.request.ChiTietDatBanRequest;
import com.cafemanagement.model.ChiTietDatBan;
import java.util.List;

public interface ChiTietDatBanService {
    ChiTietDatBan createChiTietDatBan(ChiTietDatBanRequest request);

    List<ChiTietDatBan> getChiTietDatBanByBan(Integer maBan);

    List<ChiTietDatBan> getChiTietDatBanByHoaDon(Integer maHoaDon);

    void cancelDatBan(Integer maBan);

    boolean isTableReserved(Integer maBan);
}
