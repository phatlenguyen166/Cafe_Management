package com.cafemanagement.controller;

import com.cafemanagement.dto.request.AddNhanVienRequest;
import com.cafemanagement.dto.request.UpdateNhanVienRequest;
import com.cafemanagement.dto.response.NhanVienResponse;
import com.cafemanagement.model.ChucVu;
import com.cafemanagement.model.NhanVien;
import com.cafemanagement.service.ChucVuService;
import com.cafemanagement.service.NhanVienService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class NhanVienController extends BaseController {

    private final NhanVienService nhanVienService;
    private final ChucVuService chucVuService;

    // ========== EMPLOYEE MANAGEMENT ENDPOINTS ==========

    @GetMapping
    public String employeesList(Model model, HttpSession session,
                                @RequestParam(value = "search", required = false) String searchKeyword,
                                @RequestParam(value = "success", required = false) String success,
                                @RequestParam(value = "error", required = false) String error) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("activeTab", "employees");
        model.addAttribute("employeeAction", "list");
        model.addAttribute("username", session.getAttribute("username"));

        try {
            List<NhanVienResponse> employees;

            // Check if search keyword is provided
            if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
                employees = nhanVienService.searchNhanVienByName(searchKeyword.trim());
                model.addAttribute("searchKeyword", searchKeyword.trim());

                // Add search statistics
                int totalFound = employees != null ? employees.size() : 0;
                model.addAttribute("searchResultCount", totalFound);

                if (totalFound == 0) {
                    model.addAttribute("infoMessage",
                            "Không tìm thấy nhân viên nào với từ khóa: \"" + searchKeyword.trim() + "\"");
                }
            } else {
                employees = nhanVienService.getListNhanVien();
            }

            model.addAttribute("employees", employees);

            // Calculate statistics
            int totalEmployees = employees != null ? employees.size() : 0;
            model.addAttribute("totalEmployees", totalEmployees);
            model.addAttribute("activeEmployees", totalEmployees); // Assuming all are active since isDeleted = 0
            model.addAttribute("inactiveEmployees", 0);

            // Success/error messages
            if (success != null) {
                switch (success) {
                    case "add" -> model.addAttribute("successMessage", "Thêm nhân viên thành công!");
                    case "edit" -> model.addAttribute("successMessage", "Cập nhật nhân viên thành công!");
                    case "delete" -> model.addAttribute("successMessage", "Xóa nhân viên thành công!");
                }
            }
            if (error != null) {
                model.addAttribute("errorMessage", "Có lỗi xảy ra: " + error);
            }

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Không thể tải danh sách nhân viên: " + e.getMessage());
            model.addAttribute("totalEmployees", 0);
            model.addAttribute("activeEmployees", 0);
            model.addAttribute("inactiveEmployees", 0);
            model.addAttribute("employees", List.of());
        }

        return "home";
    }

    @GetMapping("/add")
    public String employeesAdd(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        List<ChucVu> listChucVu = chucVuService.getListChucVu();
        model.addAttribute("listChucVu", listChucVu);
        model.addAttribute("activeTab", "employees");
        model.addAttribute("employeeAction", "add");
        model.addAttribute("username", session.getAttribute("username"));

        // Thêm object để bind form
        if (!model.containsAttribute("addNhanVienRequest")) {
            model.addAttribute("addNhanVienRequest", new AddNhanVienRequest());
        }

        return "home";
    }

    @GetMapping("/edit")
    public String employeesEdit(Model model, HttpSession session,
                                @RequestParam(value = "id", required = false) Integer id) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        // Kiểm tra ID có được truyền không
        if (id == null) {
            return "redirect:/employees?error=missing-id";
        }

        try {
            // Lấy thông tin nhân viên theo ID
            NhanVien employee = nhanVienService.getNhanVienById(id);
            System.out.println("Editing employee: " + employee.getHoTen() + " (ID: " + id + ")");

            // Lấy danh sách chức vụ để hiển thị trong dropdown
            List<ChucVu> listChucVu = chucVuService.getListChucVu();

            model.addAttribute("employee", employee);
            model.addAttribute("listChucVu", listChucVu);
            model.addAttribute("activeTab", "employees");
            model.addAttribute("employeeAction", "edit");
            model.addAttribute("username", session.getAttribute("username"));

            return "home";
        } catch (Exception e) {
            System.err.println("Error loading employee: " + e.getMessage());
            return "redirect:/employees?error=employee-not-found";
        }
    }

    @GetMapping("/delete")
    public String employeesDelete(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("activeTab", "employees");
        model.addAttribute("employeeAction", "delete");
        model.addAttribute("username", session.getAttribute("username"));

        return "home";
    }

    // ========== POST ENDPOINTS FOR EMPLOYEE ACTIONS ==========

    @PostMapping("/add")
    public String employeesAddPost(@ModelAttribute AddNhanVienRequest request,
                                   Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        try {
            // Validate dữ liệu cơ bản
            if (request.getHoTen() == null || request.getHoTen().trim().isEmpty()) {
                throw new RuntimeException("Họ tên không được để trống!");
            }

            if (request.getSoDienThoai() == null || request.getSoDienThoai().trim().isEmpty()) {
                throw new RuntimeException("Số điện thoại không được để trống!");
            }

            if (request.getMaChucVu() == null) {
                throw new RuntimeException("Vui lòng chọn chức vụ!");
            }

            if (request.getLuong() == null || request.getLuong() <= 0) {
                throw new RuntimeException("Lương phải lớn hơn 0!");
            }

            // Thêm nhân viên
            nhanVienService.addNhanVien(request);
            System.out.println("---------------------Thêm nhân viên thành công----------------: " + request.getHoTen());
            return "redirect:/employees?success=add";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("addNhanVienRequest", request);
            return employeesAdd(model, session);
        }
    }

    @PostMapping("/edit/{id}")
    public String employeesEditPost(@PathVariable Integer id,
                                    @RequestParam String hoTen,
                                    @RequestParam(required = false) String diaChi,
                                    @RequestParam Integer maChucVu,
                                    @RequestParam String soDienThoai,
                                    @RequestParam(required = false) String tenDangNhap,
                                    @RequestParam(required = false) String matKhauMoi,
                                    Model model, HttpSession session) {

        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        try {
            // Tạo request object để update nhân viên
            UpdateNhanVienRequest request = new UpdateNhanVienRequest();
            request.setMaNhanVien(id);
            request.setHoTen(hoTen);
            request.setDiaChi(diaChi);
            request.setMaChucVu(maChucVu);
            request.setSoDienThoai(soDienThoai);
            request.setTenDangNhap(tenDangNhap);
            request.setMatKhauMoi(matKhauMoi);

            // Gọi service để update
            nhanVienService.updateNhanVien(request);

            System.out.println("Successfully updated employee with ID: " + id);
            return "redirect:/employees?success=edit";

        } catch (Exception e) {
            System.err.println("Error updating employee: " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "redirect:/employees/edit?id=" + id + "&error=" + e.getMessage();
        }
    }

    @PostMapping("/lock/{id}")
    public String lockEmployee(@PathVariable Integer id,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        try {
            System.out.println("Attempting to lock employee with ID: " + id);
            nhanVienService.lockEmployee(id);
            redirectAttributes.addFlashAttribute("successMessage", "xóa nhân viên thành công!");
            System.out.println("Successfully locked employee with ID: " + id);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa nhân viên: " + e.getMessage());
            System.err.println("Error locking employee: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/employees";
    }
}
