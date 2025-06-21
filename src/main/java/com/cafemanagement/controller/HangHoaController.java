package com.cafemanagement.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HangHoaController extends BaseController {

    @GetMapping("/inventory")
    public String inventory(Model model, HttpSession session,
                            @RequestParam(value = "search", required = false) String searchKeyword,
                            @RequestParam(value = "category", required = false) String category) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("activeTab", "inventory");
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("selectedCategory", category);

        // Thêm dữ liệu kho hàng
        model.addAttribute("totalProducts", 50);
        model.addAttribute("inStockItems", 40);
        model.addAttribute("lowStockItems", 7);
        model.addAttribute("outOfStockItems", 3);

        return "home";
    }
}
