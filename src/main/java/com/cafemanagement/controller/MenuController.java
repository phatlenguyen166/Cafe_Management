package com.cafemanagement.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MenuController extends BaseController {

    @GetMapping("/menu")
    public String menu(Model model, HttpSession session,
                       @RequestParam(value = "search", required = false) String searchKeyword,
                       @RequestParam(value = "category", required = false) String category) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("activeTab", "menu");
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("selectedCategory", category);

        // Thêm dữ liệu thực đơn
        model.addAttribute("totalMenuItems", 45);
        model.addAttribute("activeItems", 40);
        model.addAttribute("draftItems", 3);
        model.addAttribute("outOfStockItems", 2);

        return "home";
    }
}
