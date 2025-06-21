package com.cafemanagement.controller;

import com.cafemanagement.model.Ban;
import org.springframework.ui.Model;
import com.cafemanagement.service.BanService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BanController extends BaseController {

    private final BanService banService;

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
}