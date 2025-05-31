package org.example.timestampapp.Controller.WebController;

import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Model.DTO.EmployeeWorkingStatisticsDTO;
import org.example.timestampapp.Service.DepartmentService;
import org.example.timestampapp.Service.EmployeeService;
import org.example.timestampapp.Service.WorkingHourService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AdminController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final WorkingHourService workingHourService;

    public AdminController(EmployeeService employeeService,
                           DepartmentService departmentService,
                           WorkingHourService workingHourService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.workingHourService = workingHourService;
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

    @GetMapping("/admin/employee-statistics")
    public String getEmployeeStatistic(Model model, @RequestParam(name="employeeId") Long employeeId) {
        int year= LocalDate.now().getYear();
        int month= LocalDate.now().getMonthValue();
        EmployeeWorkingStatisticsDTO statistics=employeeService.getEmployeeWorkingStatistics(employeeId,2025,4);
        System.out.println(statistics);
        model.addAttribute("statistics", statistics);
        return "employee_statistics";
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

    @PostMapping("/admin/delete-employee")
    public String deleteEmployee(@RequestParam(name = "employeeId") Long employeeId) {
        System.out.println(employeeId);
        employeeService.deleteEmployee(employeeId);
        return "admin";
    }
}
