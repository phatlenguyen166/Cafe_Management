package com.cafemanagement.service.impl;

import com.cafemanagement.dto.request.ChiTietDatBanRequest;
import com.cafemanagement.model.Ban;
import com.cafemanagement.model.ChiTietDatBan;
import com.cafemanagement.model.HoaDon;
import com.cafemanagement.model.NhanVien;
import com.cafemanagement.repository.BanRepository;
import com.cafemanagement.repository.ChiTietDatBanRepository;
import com.cafemanagement.repository.HoaDonRepository;
import com.cafemanagement.repository.NhanVienRepository;
import com.cafemanagement.service.ChiTietDatBanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChiTietDatBanServiceImpl implements ChiTietDatBanService {

    private final ChiTietDatBanRepository chiTietDatBanRepository;
    private final BanRepository banRepository;
    private final HoaDonRepository hoaDonRepository;
    private final NhanVienRepository nhanVienRepository;

    @Override
    @Transactional
    public ChiTietDatBan createChiTietDatBan(ChiTietDatBanRequest request) {
        try {
            // 1. Validate dữ liệu đầu vào
            validateRequest(request);

            // 2. Kiểm tra bàn có tồn tại và có thể đặt không
            Ban ban = banRepository.findById(request.getMaBan())
                    .orElseThrow(() -> new RuntimeException("Bàn không tồn tại!"));

            if (!"Rảnh".equals(ban.getTinhTrang().trim())) {
                throw new RuntimeException("Bàn hiện tại không rảnh, không thể đặt!");
            }

            // 3. Kiểm tra bàn đã được đặt chưa
            if (isTableReserved(request.getMaBan())) {
                throw new RuntimeException("Bàn này đã được đặt trước!");
            }

            // 4. Lấy thông tin nhân viên (nếu có)
            NhanVien nhanVien = null;
            if (request.getMaNhanVien() != null) {
                nhanVien = nhanVienRepository.findById(request.getMaNhanVien())
                        .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại!"));
            }

            // 5. Lấy thông tin hóa đơn (nếu có)
            // HoaDon hoaDon = null;
            // if (request.getMaHoaDon() != null) {
            // hoaDon = hoaDonRepository.findById(request.getMaHoaDon())
            // .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại!"));
            // }

            // 6. Tạo chi tiết đặt bàn mới
            ChiTietDatBan chiTietDatBan = new ChiTietDatBan();
            chiTietDatBan.setMaBan(ban);
            chiTietDatBan.setMaHoaDon(null);
            chiTietDatBan.setMaNhanVien(nhanVien);
            chiTietDatBan.setNgayGioDat(request.getNgayGioDat());
            chiTietDatBan.setSdtKhachHang(request.getSdtKhachHang().trim());
            chiTietDatBan.setTenKhachHang(request.getTenKhachHang().trim());

            // 7. Lưu chi tiết đặt bàn
            ChiTietDatBan savedChiTietDatBan = chiTietDatBanRepository.save(chiTietDatBan);

            // 8. Cập nhật trạng thái bàn thành "Đã đặt"
            ban.setTinhTrang("Đã đặt");
            banRepository.save(ban);

            System.out.println("Created reservation for table: " + ban.getTenBan() +
                    " by customer: " + request.getTenKhachHang());

            return savedChiTietDatBan;

        } catch (Exception e) {
            System.err.println("Error creating table reservation: " + e.getMessage());
            throw new RuntimeException("Không thể đặt bàn: " + e.getMessage());
        }
    }

    @Override
    public List<ChiTietDatBan> getChiTietDatBanByBan(Integer maBan) {
        if (maBan == null) {
            throw new RuntimeException("Mã bàn không được để trống!");
        }
        return chiTietDatBanRepository.findByMaBanId(maBan);
    }

    @Override
    public List<ChiTietDatBan> getChiTietDatBanByHoaDon(Integer maHoaDon) {
        if (maHoaDon == null) {
            throw new RuntimeException("Mã hóa đơn không được để trống!");
        }
        return chiTietDatBanRepository.findByMaHoaDonId(maHoaDon);
    }

    @Override
    @Transactional
    public void cancelDatBan(Integer maBan) {
        try {
            // 1. Kiểm tra bàn có tồn tại không
            Ban ban = banRepository.findById(maBan)
                    .orElseThrow(() -> new RuntimeException("Bàn không tồn tại!"));

            // 2. Lấy danh sách chi tiết đặt bàn
            List<ChiTietDatBan> danhSachDatBan = getChiTietDatBanByBan(maBan);

            if (danhSachDatBan.isEmpty()) {
                throw new RuntimeException("Không tìm thấy thông tin đặt bàn!");
            }

            // 3. Xóa tất cả chi tiết đặt bàn
            chiTietDatBanRepository.deleteAll(danhSachDatBan);

            // 4. Cập nhật trạng thái bàn về "Rảnh"
            ban.setTinhTrang("Rảnh");
            banRepository.save(ban);

            System.out.println("Cancelled reservation for table: " + ban.getTenBan());

        } catch (Exception e) {
            System.err.println("Error cancelling table reservation: " + e.getMessage());
            throw new RuntimeException("Không thể hủy đặt bàn: " + e.getMessage());
        }
    }

    @Override
    public boolean isTableReserved(Integer maBan) {
        if (maBan == null) {
            return false;
        }

        List<ChiTietDatBan> reservations = getChiTietDatBanByBan(maBan);
        return !reservations.isEmpty();
    }

    /**
     * Validate dữ liệu đầu vào
     */
    private void validateRequest(ChiTietDatBanRequest request) {
        if (request == null) {
            throw new RuntimeException("Dữ liệu đặt bàn không được để trống!");
        }

        if (request.getMaBan() == null) {
            throw new RuntimeException("Mã bàn không được để trống!");
        }

        if (request.getTenKhachHang() == null || request.getTenKhachHang().trim().isEmpty()) {
            throw new RuntimeException("Tên khách hàng không được để trống!");
        }

        if (request.getSdtKhachHang() == null || request.getSdtKhachHang().trim().isEmpty()) {
            throw new RuntimeException("Số điện thoại không được để trống!");
        }

        // Validate phone number format
        String phonePattern = "^[0-9]{10,11}$";
        if (!request.getSdtKhachHang().trim().matches(phonePattern)) {
            throw new RuntimeException("Số điện thoại không hợp lệ (10-11 số)!");
        }

        if (request.getNgayGioDat() == null) {
            throw new RuntimeException("Ngày giờ đặt không được để trống!");
        }

        // Validate datetime not in the past
        if (request.getNgayGioDat().isBefore(Instant.now())) {
            throw new RuntimeException("Ngày giờ đặt không được trong quá khứ!");
        }
    }
}
