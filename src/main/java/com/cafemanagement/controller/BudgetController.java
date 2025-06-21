package com.cafemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class BudgetController extends BaseController {

    @GetMapping("/budget")
    public String budget(Model model, HttpSession session,
                         @RequestParam(value = "fromDate", required = false) String fromDate,
                         @RequestParam(value = "toDate", required = false) String toDate) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("activeTab", "budget");
        model.addAttribute("username", session.getAttribute("username"));

        if (fromDate == null || fromDate.isEmpty()) {
            fromDate = LocalDate.now().minusDays(2).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        if (toDate == null || toDate.isEmpty()) {
            toDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);

        return "home";
    }
}