package com.cafemanagement.service;

import com.cafemanagement.dto.request.AddNhanVienRequest;
import com.cafemanagement.dto.request.UpdateNhanVienRequest;
import com.cafemanagement.dto.response.NhanVienResponse;
import com.cafemanagement.model.NhanVien;

import java.util.List;

public interface NhanVienService {
    void addNhanVien(AddNhanVienRequest request);

    List<NhanVienResponse> getListNhanVien();

    List<NhanVienResponse> searchNhanVienByName(String keyword);

    NhanVien getNhanVienById(Integer maNhanVien);

    void updateNhanVien(UpdateNhanVienRequest request);

    void lockEmployee(Integer maNhanVien);
}

