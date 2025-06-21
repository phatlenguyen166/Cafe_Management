package com.cafemanagement.service;


import com.cafemanagement.dto.request.LoginRequest;
import com.cafemanagement.dto.response.LoginResponse;
import com.cafemanagement.model.TaiKhoan;

public interface TaiKhoanService {
    LoginResponse login(LoginRequest loginDTO);

    TaiKhoan createTaiKhoan(String tenDangNhap, String matKhau);

    TaiKhoan findTaiKhoanById(Integer id);
}