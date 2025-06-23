package com.cafemanagement.service.impl;

import java.util.List;

import com.cafemanagement.dto.request.AddNhanVienRequest;
import com.cafemanagement.dto.request.UpdateNhanVienRequest;
import com.cafemanagement.dto.response.NhanVienResponse;
import com.cafemanagement.model.ChucVu;
import com.cafemanagement.model.NhanVien;
import com.cafemanagement.model.TaiKhoan;
import com.cafemanagement.repository.ChucVuRepository;
import com.cafemanagement.repository.NhanVienRepository;
import com.cafemanagement.repository.TaiKhoanRepository;
import com.cafemanagement.service.NhanVienService;
import com.cafemanagement.service.TaiKhoanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NhanVienServiceImpl implements NhanVienService {

    private final NhanVienRepository nhanVienRepository;
    private final ChucVuRepository chucVuRepository;
    private final TaiKhoanRepository taiKhoanRepository;
    private final TaiKhoanService taiKhoanService;

    @Override
    public List<NhanVienResponse> getListNhanVien() {
        return nhanVienRepository.getListNhanVien();
    }

    @Override
    public List<NhanVienResponse> searchNhanVienByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getListNhanVien();
        }
        return nhanVienRepository.searchNhanVienByName(keyword.trim());
    }

    @Override
    @Transactional
    public void addNhanVien(AddNhanVienRequest request) {
        try {
            if (request.getHoTen() == null || request.getHoTen().trim().isEmpty()) {
                throw new RuntimeException("Họ tên không được để trống!");
            }

            ChucVu chucVu = chucVuRepository.findById(request.getMaChucVu())
                    .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại!"));

            TaiKhoan taiKhoan = null;
            if (request.getTenDangNhap() != null && !request.getTenDangNhap().trim().isEmpty() &&
                    request.getMatKhau() != null && !request.getMatKhau().trim().isEmpty()) {

                if (taiKhoanRepository.existsByTenDangNhap(request.getTenDangNhap().trim())) {
                    throw new RuntimeException("Tên đăng nhập đã tồn tại!");
                }

                System.out.println("Creating account for: " + request.getTenDangNhap());
                taiKhoan = taiKhoanService.createTaiKhoan(
                        request.getTenDangNhap().trim(),
                        request.getMatKhau().trim());
                System.out.println("Account created with ID: " + taiKhoan.getMaTaiKhoan());
            }

            int maxId = nhanVienRepository.findMaxMaNhanVien();
            int newId = maxId + 1;

            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNhanVien(newId); // Giả sử ID được tạo ngẫu nhiên, có thể thay đổi theo logic của
            // bạn
            nhanVien.setHoTen(request.getHoTen().trim());
            nhanVien.setDiaChi(request.getDiaChi());
            nhanVien.setSoDienThoai(request.getSoDienThoai());
            nhanVien.setChucVu(chucVu);
            nhanVien.setIsDeleted(false);

            // Set tài khoản nếu có
            if (taiKhoan != null) {
                nhanVien.setTaiKhoan(taiKhoan);
            }

            // 5. Lưu nhân viên
            System.out.println("Saving employee: " + nhanVien.getHoTen());
            NhanVien savedNhanVien = nhanVienRepository.save(nhanVien);
            System.out.println("Employee saved with ID: " + savedNhanVien.getMaNhanVien());

        } catch (Exception e) {
            System.err.println("Error adding employee: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi thêm nhân viên: " + e.getMessage());
        }
    }

    @Override
    public NhanVien getNhanVienById(Integer maNhanVien) {
        return nhanVienRepository.findById(maNhanVien)
                .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại!"));
    }

    @Override
    @Transactional
    public void updateNhanVien(UpdateNhanVienRequest request) {
        try {
            // 1. Tìm nhân viên cần update
            NhanVien nhanVien = nhanVienRepository.findById(request.getMaNhanVien())
                    .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại!"));

            System.out.println("Updating employee ID: " + request.getMaNhanVien());

            // 2. Validation dữ liệu
            if (request.getHoTen() == null || request.getHoTen().trim().isEmpty()) {
                throw new RuntimeException("Họ tên không được để trống!");
            }

            if (request.getSoDienThoai() == null || request.getSoDienThoai().trim().isEmpty()) {
                throw new RuntimeException("Số điện thoại không được để trống!");
            }

            if (request.getMaChucVu() == null) {
                throw new RuntimeException("Vui lòng chọn chức vụ!");
            }

            // 3. Cập nhật thông tin cơ bản trong bảng NhanVien
            nhanVien.setHoTen(request.getHoTen().trim());
            nhanVien.setDiaChi(request.getDiaChi() != null ? request.getDiaChi().trim() : null);
            nhanVien.setSoDienThoai(request.getSoDienThoai().trim());

            // 4. Cập nhật chức vụ
            ChucVu chucVu = chucVuRepository.findById(request.getMaChucVu())
                    .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại!"));
            nhanVien.setChucVu(chucVu);

            // 5. Xử lý cập nhật thông tin tài khoản
            updateTaiKhoanInfo(nhanVien, request);

            // 6. Lưu nhân viên
            nhanVienRepository.save(nhanVien);

            System.out.println("Successfully updated employee: " + nhanVien.getHoTen());

        } catch (Exception e) {
            System.err.println("Error updating employee: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi cập nhật nhân viên: " + e.getMessage());
        }
    }

    private void updateTaiKhoanInfo(NhanVien nhanVien, UpdateNhanVienRequest request) {
        // Kiểm tra xem có thông tin tài khoản để cập nhật không
        boolean hasAccountInfo = (request.getTenDangNhap() != null && !request.getTenDangNhap().trim().isEmpty()) ||
                (request.getMatKhauMoi() != null && !request.getMatKhauMoi().trim().isEmpty());

        if (!hasAccountInfo) {
            // Không có thông tin tài khoản để cập nhật
            return;
        }

        TaiKhoan taiKhoan = nhanVien.getTaiKhoan();

        if (taiKhoan != null) {
            // Cập nhật tài khoản hiện tại
            System.out.println("Updating existing account for employee: " + nhanVien.getHoTen());

            // Cập nhật tên đăng nhập nếu có
            if (request.getTenDangNhap() != null && !request.getTenDangNhap().trim().isEmpty()) {
                String newUsername = request.getTenDangNhap().trim();

                // Kiểm tra tên đăng nhập đã tồn tại chưa (trừ tài khoản hiện tại)
                if (!newUsername.equals(taiKhoan.getTenDangNhap()) &&
                        taiKhoanRepository.existsByTenDangNhap(newUsername)) {
                    throw new RuntimeException("Tên đăng nhập '" + newUsername + "' đã tồn tại!");
                }

                taiKhoan.setTenDangNhap(newUsername);
            }

            // Cập nhật mật khẩu nếu có
            if (request.getMatKhauMoi() != null && !request.getMatKhauMoi().trim().isEmpty()) {
                taiKhoan.setMatKhau(request.getMatKhauMoi().trim());
            }

            taiKhoanRepository.save(taiKhoan);
            System.out.println("Updated account: " + taiKhoan.getTenDangNhap());

        } else {
            // Tạo tài khoản mới nếu nhân viên chưa có tài khoản
            if (request.getTenDangNhap() != null && !request.getTenDangNhap().trim().isEmpty()) {
                System.out.println("Creating new account for employee: " + nhanVien.getHoTen());

                // Kiểm tra tên đăng nhập đã tồn tại chưa
                if (taiKhoanRepository.existsByTenDangNhap(request.getTenDangNhap().trim())) {
                    throw new RuntimeException("Tên đăng nhập '" + request.getTenDangNhap() + "' đã tồn tại!");
                }

                String password = request.getMatKhauMoi() != null && !request.getMatKhauMoi().trim().isEmpty()
                        ? request.getMatKhauMoi().trim()
                        : "123456"; // Mật khẩu mặc định

                TaiKhoan newTaiKhoan = taiKhoanService.createTaiKhoan(
                        request.getTenDangNhap().trim(),
                        password);

                nhanVien.setTaiKhoan(newTaiKhoan);
                System.out.println("Created new account: " + newTaiKhoan.getTenDangNhap());
            }
        }
    }

    @Override
    @Transactional
    public void lockEmployee(Integer maNhanVien) {
        try {
            System.out.println("Service: Locking employee with ID: " + maNhanVien);

            // Tìm nhân viên cần khóa
            NhanVien nhanVien = nhanVienRepository.findById(maNhanVien)
                    .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại!"));

            // Set isDeleted = true (soft delete)
            nhanVien.setIsDeleted(true);

            // Lưu nhân viên
            nhanVienRepository.save(nhanVien);

            System.out.println("Successfully locked employee: " + nhanVien.getHoTen() + " (ID: " + maNhanVien + ")");

        } catch (Exception e) {
            System.err.println("Error locking employee: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi khóa nhân viên: " + e.getMessage());
        }
    }
}
