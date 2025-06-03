package org.example.timestampapp.Controller.WebController;

import jakarta.servlet.http.HttpSession;
import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Model.DTO.EmployeeStatisticsDTO;
import org.example.timestampapp.Model.DTO.EmployeeStatusDTO;
import org.example.timestampapp.Model.Entity.Employee;
import org.example.timestampapp.Service.EmployeeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
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
}
