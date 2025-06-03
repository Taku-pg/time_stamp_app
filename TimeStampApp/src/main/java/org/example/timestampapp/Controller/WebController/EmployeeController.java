package org.example.timestampapp.Controller.WebController;

import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Model.Entity.Employee;
import org.example.timestampapp.Service.EmployeeService;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/main")
    public String employeeMain(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        EmployeeDTO employeeDTO = employeeService.getEmployeeStatusByEmail(userName);
        model.addAttribute("employee", employeeDTO);
        return "employee";
    }

    @GetMapping("/employee-statistics")
    public String employeeStatistics(Model model) {

        return "employee_statistics";
    }
}
