package com.cafemanagement.controller;


import com.cafemanagement.dto.request.LoginRequest;
import com.cafemanagement.dto.response.LoginResponse;
import com.cafemanagement.service.TaiKhoanService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TaiKhoanController {

    private final TaiKhoanService taiKhoanService;

    @GetMapping("/")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute LoginRequest loginRequest,
                              Model model, HttpSession session) {
        try {
            LoginResponse thongTinUser = taiKhoanService.login(loginRequest);

            if (thongTinUser != null && thongTinUser.getMatKhau().equals(loginRequest.getPassword())) {
                // Lưu thông tin user vào session
                session.setAttribute("userInfo", thongTinUser);
                session.setAttribute("isLoggedIn", true);
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
                model.addAttribute("loginRequest", loginRequest);
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Tài khoản của bạn đã bị khóa");
            model.addAttribute("loginRequest", loginRequest);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}