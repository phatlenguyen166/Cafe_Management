package com.cafemanagement.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController extends BaseController {

    // ============= DASHBOARD =============

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("activeTab", "home");
        model.addAttribute("username", session.getAttribute("username"));

        // Thêm dữ liệu cho dashboard
        model.addAttribute("totalOrders", 150);
        model.addAttribute("totalRevenue", 25000000);
        model.addAttribute("totalCustomers", 45);
        model.addAttribute("todayOrders", 12);

        return "home";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("activeTab", "profile");
        model.addAttribute("username", session.getAttribute("username"));

        // Thêm thông tin profile (thay thế bằng dữ liệu thực)
        model.addAttribute("fullName", "Nguyễn Văn Admin");
        model.addAttribute("email", "admin@cafe.com");
        model.addAttribute("phone", "0123456789");
        model.addAttribute("position", "Quản lý");

        return "home";
    }

    // ========== OTHER MODULE ENDPOINTS ==========
    // Employee management đã được tách sang NhanVienController

    @GetMapping("/device")
    public String device(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("activeTab", "device");
        model.addAttribute("username", session.getAttribute("username"));

        // Thêm dữ liệu kho hàng
        model.addAttribute("totalProducts", 50);
        model.addAttribute("lowStockItems", 5);
        model.addAttribute("outOfStockItems", 2);

        return "home";
    }

    @GetMapping("/marketing")
    public String marketing(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("activeTab", "marketing");
        model.addAttribute("username", session.getAttribute("username"));

        // Thêm dữ liệu marketing
        model.addAttribute("activeCampaigns", 3);
        model.addAttribute("totalCustomers", 1200);
        model.addAttribute("loyaltyMembers", 800);

        return "home";
    }

    @GetMapping("/reports")
    public String reports(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("activeTab", "reports");
        model.addAttribute("username", session.getAttribute("username"));

        // Thêm dữ liệu báo cáo
        model.addAttribute("monthlyRevenue", 75000000);
        model.addAttribute("monthlyOrders", 450);
        model.addAttribute("growthRate", 15.5);

        return "home";
    }

    @GetMapping("/about")
    public String about(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("activeTab", "about");
        model.addAttribute("username", session.getAttribute("username"));

        // Thêm thông tin về ứng dụng
        model.addAttribute("appVersion", "1.0.0");
        model.addAttribute("buildDate", "2025-06-18");
        model.addAttribute("developer", "Việt Trí Đạo");

        return "home";
    }
}

