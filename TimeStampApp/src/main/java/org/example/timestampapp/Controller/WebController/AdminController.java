package org.example.timestampapp.Controller.WebController;

import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Service.DepartmentService;
import org.example.timestampapp.Service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public AdminController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/admin/main")
    public String adminMain() {
        return "admin";
    }

    @GetMapping("/admin/employee-list")
    public String getEmployeeList(Model model) {
        List<EmployeeDTO> employees=employeeService.getAllEmployees();
        if(employees.isEmpty()) {
            System.out.println("No employee found");
        }
        for (EmployeeDTO employee : employees) {
            System.out.println(employee);
        }
        model.addAttribute("employees", employees);
        return "all_employee";
    }

    @GetMapping("/admin/modify-employee")
    public String modifyEmployee(Model model, @ModelAttribute EmployeeDTO employee) {
        System.out.println(employee);
        model.addAttribute("employee", employee);
        List<String> departments=departmentService.getAllDepartmentName();
        for(String str:departments) {
            System.out.println(str);
        }
        System.out.println(departments.size());
        model.addAttribute("departments", departments);
        return "modify_employee";
    }

    @PostMapping("/admin/modify-employee")
    public String modifyEmployee(@ModelAttribute EmployeeDTO employee) {
        System.out.println(employee);
        employeeService.updateEmployee(employee);
        return "admin";
    }
}
