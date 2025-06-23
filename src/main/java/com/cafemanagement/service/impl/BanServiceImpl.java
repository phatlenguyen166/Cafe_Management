package com.cafemanagement.service.impl;

import com.cafemanagement.model.Ban;
import com.cafemanagement.model.ChiTietDatBan;
import com.cafemanagement.repository.BanRepository;
import com.cafemanagement.repository.ChiTietDatBanRepository;
import com.cafemanagement.service.BanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BanServiceImpl implements BanService {

    private final BanRepository banRespository;
    private final ChiTietDatBanRepository chiTietDatBanRepository;

    @Override
    public List<Ban> getAllBans() {
        return banRespository.findAll();
    }

    @Override
    public List<Ban> getByTrangThai(String trangThai) {
        return banRespository.findByTinhTrang(trangThai);
    }

    @Override
    @Transactional // Thêm annotation này nếu chưa có
    public void chuyenBan(Integer maBanCu, Integer maBanMoi) {
        System.out.println("🔄 Bắt đầu chuyển bàn từ ID: " + maBanCu + " sang ID: " + maBanMoi);

        // Tìm chi tiết đặt bàn của BÀN CŨ
        ChiTietDatBan chiTietDatBanCu = chiTietDatBanRepository.findByBanMaBanAndHoaDonTrangThai(maBanCu, false);

        if (chiTietDatBanCu == null) {
            System.err.println("❌ Không tìm thấy thông tin đặt bàn cho bàn ID: " + maBanCu);
            throw new RuntimeException("Không tìm thấy thông tin đặt bàn cho bàn cũ!");
        }

        System.out.println("✅ Tìm thấy chi tiết đặt bàn ID: " + chiTietDatBanCu.getMaChiTietDatBan());

        // Lấy thông tin bàn cũ và bàn mới
        Ban banCu = banRespository.findById(maBanCu)
                .orElseThrow(() -> new RuntimeException("Bàn cũ không tồn tại!"));

        Ban banMoi = banRespository.findById(maBanMoi)
                .orElseThrow(() -> new RuntimeException("Bàn mới không tồn tại!"));

        System.out.println("📍 Bàn cũ: " + banCu.getTenBan() + " (" + banCu.getTinhTrang() + ")");
        System.out.println("📍 Bàn mới: " + banMoi.getTenBan() + " (" + banMoi.getTinhTrang() + ")");

        // Kiểm tra bàn mới có rảnh không
        if (!"Rảnh".equals(banMoi.getTinhTrang())) {
            System.err.println("❌ Bàn mới không rảnh: " + banMoi.getTinhTrang());
            throw new RuntimeException("Bàn mới không rảnh!");
        }

        // Chuyển chi tiết đặt bàn từ bàn cũ sang bàn mới
        chiTietDatBanCu.setBan(banMoi);
        chiTietDatBanRepository.save(chiTietDatBanCu);
        System.out.println("📝 Đã cập nhật chi tiết đặt bàn");

        // Cập nhật trạng thái bàn
        banCu.setTinhTrang("Rảnh");
        banRespository.save(banCu);
        System.out.println("✅ Bàn cũ đã chuyển thành Rảnh");

        banMoi.setTinhTrang("Đang sử dụng");
        banRespository.save(banMoi);
        System.out.println("✅ Bàn mới đã chuyển thành Đang sử dụng");

        System.out.println("🎉 Chuyển bàn hoàn tất!");
    }

}
