package com.cafemanagement.controller;

import com.cafemanagement.dto.request.ChiTietDatBanRequest;
import com.cafemanagement.dto.response.LoginResponse;
import com.cafemanagement.model.Ban;
import org.springframework.ui.Model;
import com.cafemanagement.service.BanService;
import com.cafemanagement.service.ChiTietDatBanService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BanController extends BaseController {

    private final BanService banService;
    private final ChiTietDatBanService chiTietDatBanService;

    @GetMapping("/sales")
    public String sales(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        // Lấy danh sách bàn từ database
        List<Ban> listBan = banService.getAllBans();
        System.out.println("Danh sách bàn: " + listBan);
        model.addAttribute("listBan", listBan);
        model.addAttribute("activeTab", "sales");
        model.addAttribute("username", session.getAttribute("username"));

        // Tính toán thống kê động từ dữ liệu bàn
        long banRanh = listBan.stream()
                .filter(ban -> "Rảnh".equals(ban.getTinhTrang().trim()))
                .count();

        long banDangSuDung = listBan.stream()
                .filter(ban -> "Đang sử dụng".equals(ban.getTinhTrang().trim()))
                .count();

        long banDaDat = listBan.stream()
                .filter(ban -> "Đã đặt".equals(ban.getTinhTrang().trim()))
                .count();

        // Thêm thống kê bàn
        model.addAttribute("banRanh", banRanh);
        model.addAttribute("banDangSuDung", banDangSuDung);
        model.addAttribute("banDaDat", banDaDat);
        model.addAttribute("tongBan", listBan.size());

        // Thêm dữ liệu bán hàng
        model.addAttribute("todaySales", 2500000);
        model.addAttribute("todayOrders", 25);
        model.addAttribute("avgOrderValue", 100000);

        return "home";
    }

    @PostMapping("/dat-ban")
    public String datBan(@ModelAttribute ChiTietDatBanRequest request,
            Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        try {
            // Lấy thông tin nhân viên từ session
            LoginResponse userInfo = (LoginResponse) session.getAttribute("userInfo");
            if (userInfo != null) {
                request.setMaNhanVien(userInfo.getMaNhanVien());
            }

            // Tạo chi tiết đặt bàn
            chiTietDatBanService.createChiTietDatBan(request);

            return "redirect:/sales?success=dat-ban";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/sales?error=" + e.getMessage();
        }
    }

    @PostMapping("/huy-dat-ban/{maBan}")
    public String huyDatBan(@PathVariable Integer maBan, Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        try {
            chiTietDatBanService.cancelDatBan(maBan);
            return "redirect:/sales?success=huy-dat-ban";

        } catch (Exception e) {
            return "redirect:/sales?error=" + e.getMessage();
        }
    }

    @PostMapping("/chuyen-ban")
    public String chuyenBan(@RequestParam Integer maBanCu,
            @RequestParam Integer maBanMoi,
            @RequestParam(required = false) String note,
            HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        try {
            // Validation
            if (maBanCu == null || maBanMoi == null) {
                throw new RuntimeException("Thiếu thông tin bàn!");
            }

            if (maBanCu.equals(maBanMoi)) {
                throw new RuntimeException("Không thể chuyển bàn cùng một bàn!");
            }

            // Log thông tin chuyển bàn
            System.out.println("🔄 Chuyển bàn từ ID: " + maBanCu + " sang ID: " + maBanMoi);
            if (note != null && !note.trim().isEmpty()) {
                System.out.println("📝 Ghi chú: " + note);
            }

            // Thực hiện chuyển bàn
            banService.chuyenBan(maBanCu, maBanMoi);

            System.out.println("✅ Chuyển bàn thành công!");
            return "redirect:/sales?success=chuyen-ban";

        } catch (Exception e) {
            System.err.println("❌ Lỗi chuyển bàn: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/sales?error=" + e.getMessage();
        }
    }
}