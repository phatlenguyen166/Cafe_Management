package com.cafemanagement.service.impl;

import com.cafemanagement.dto.request.LoginRequest;
import com.cafemanagement.dto.response.LoginResponse;
import com.cafemanagement.model.TaiKhoan;
import com.cafemanagement.repository.NhanVienRepository;
import com.cafemanagement.repository.TaiKhoanRepository;
import com.cafemanagement.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaiKhoanServiceImpl implements TaiKhoanService {

    private final NhanVienRepository nhanVienRepository;
    private final TaiKhoanRepository taiKhoanRepository;

    @Override
    public LoginResponse login(LoginRequest loginDTO) {
        return nhanVienRepository.findThongTinDangNhap(loginDTO.getUsername());
    }

    @Override
    public TaiKhoan createTaiKhoan(String tenDangNhap, String matKhau) {
        TaiKhoan taiKhoan = new TaiKhoan();
        int maxId = taiKhoanRepository.findMaxMaNhanVien();
        int newId = maxId + 1;
        taiKhoan.setMaTaiKhoan(newId);
        // ID will be auto-generated due to @GeneratedValue annotation
        taiKhoan.setTenDangNhap(tenDangNhap);
        taiKhoan.setMatKhau(matKhau);

        // Set default values for other required fields
        taiKhoan.setQuyenHan("USER"); // or whatever default role you want
        // taiKhoan.setAnh(null); // This can be null if the column allows it

        // Log để debug
        System.out.println("Creating TaiKhoan: " + tenDangNhap);

        TaiKhoan savedTaiKhoan = taiKhoanRepository.save(taiKhoan);

        System.out.println("Saved TaiKhoan with ID: " + savedTaiKhoan.getMaTaiKhoan());

        return savedTaiKhoan;
    }

    @Override
    public TaiKhoan findTaiKhoanById(Integer id) {
        return taiKhoanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaiKhoan not found with id: " + id));
    }
}