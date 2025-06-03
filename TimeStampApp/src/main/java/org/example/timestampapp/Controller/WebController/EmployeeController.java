package org.example.timestampapp.Controller.WebController;

import jakarta.servlet.http.HttpSession;
import org.example.timestampapp.Model.DTO.*;
import org.example.timestampapp.Model.Entity.Employee;
import org.example.timestampapp.Service.EmployeeService;
import org.example.timestampapp.Service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserService userService;

    public EmployeeController(EmployeeService employeeService, UserService userService) {
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @GetMapping("/main")
    public String employeeMain(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        EmployeeStatusDTO employeeDTO = employeeService.getEmployeeStatusByEmail(userName);
        model.addAttribute("employee", employeeDTO);
        session.setAttribute("employeeId", employeeDTO.getEmployeeId());
        return "employee";
    }

    @GetMapping("/employee-statistics")
    public String employeeStatistics(Model model,HttpSession session) {
        int year= LocalDate.now().getYear();
        int month= LocalDate.now().getMonthValue();

        EmployeeStatisticsDTO statistics=
                employeeService.getEmployeeWorkingStatistics(
                        (Long)session.getAttribute("employeeId"),
                        year,
                        month);
        model.addAttribute("statistics", statistics);
        return "employee_statistics";
    }

    @GetMapping("/monthly-history")
    public String employeeHistory(Model model,HttpSession session) {
        int year= LocalDate.now().getYear();
        int month= LocalDate.now().getMonthValue();
        List<EmployeeHistoryDTO> history =
                employeeService.getEmployeeHistory((
                        Long)session.getAttribute("employeeId"),
                        year,
                        month);
        EmployeeMonthlyHistoryDTO employeeMonthlyHistoryDTO =
                new EmployeeMonthlyHistoryDTO(year,month,history);
        model.addAttribute("monthlyHistory", employeeMonthlyHistoryDTO);
        return "monthly_history";
    }

    @GetMapping("/personal-information")
    public String personalInfo(Model model,HttpSession session) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById((Long)session.getAttribute("employeeId"));
        System.out.println(employeeDTO);
        model.addAttribute("employee", employeeDTO);
        return "personal_information";
    }

    @GetMapping("/password-change")
    public String changePassword(Model model) {
        model.addAttribute("passwordForm", new PasswordChangeDTO());
        return "password_change";
    }

    @PostMapping("/password-change")
    public String changePassword(@ModelAttribute PasswordChangeDTO passwordChangeDTO) {
        System.out.println(passwordChangeDTO);
        if (!passwordChangeDTO.getNewPassword().equals(passwordChangeDTO.getConfirmPassword())) {
            return "redirect:/employee/password-change";
        }
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        try{
            userService.changePassword(userName, passwordChangeDTO.getCurrentPassword(),passwordChangeDTO.getNewPassword());
        }catch (Exception e){
            return "redirect:/employee/password-change";
        }
        return "redirect:/employee/main";
    }
}
