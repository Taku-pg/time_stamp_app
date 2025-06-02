package org.example.timestampapp.Controller.WebController;

import org.example.timestampapp.Model.DTO.DepartmentStatisticsDTO;
import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Model.DTO.EmployeeStatisticsDTO;
import org.example.timestampapp.Model.DTO.FixRecordDTO;
import org.example.timestampapp.Model.Entity.WorkingHour;
import org.example.timestampapp.Service.DepartmentService;
import org.example.timestampapp.Service.EmployeeService;
import org.example.timestampapp.Service.WorkingHourService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
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

    @GetMapping("/main")
    public String adminMain() {
        return "admin";
    }

    @GetMapping("/employee-list")
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

    @GetMapping("/employee-statistics")
    public String getEmployeeStatistic(Model model, @RequestParam(name="employeeId") Long employeeId) {
        int year= LocalDate.now().getYear();
        int month= LocalDate.now().getMonthValue();
        EmployeeStatisticsDTO statistics=employeeService.getEmployeeWorkingStatistics(employeeId,year,month);
        System.out.println(statistics);
        model.addAttribute("statistics", statistics);
        return "employee_statistics";
    }

    @GetMapping("/department-statistics")
    public String getDepartmentStatistic(Model model) {
        int year= LocalDate.now().getYear();
        int month= LocalDate.now().getMonthValue();
        String dName=departmentService.getDeptName();
        DepartmentStatisticsDTO statistics=departmentService.getDeptStatistics(dName,year,month);
        System.out.println(statistics);
        model.addAttribute("statistics", statistics);
        List<String> departments=departmentService.getAllDepartmentName();
        model.addAttribute("departments", departments);
        return "department_statistics";
    }

    @GetMapping("/modify-employee")
    public String modifyEmployee(Model model, @ModelAttribute EmployeeDTO employee) {
        model.addAttribute("employee", employee);
        List<String> departments=departmentService.getAllDepartmentName();
        model.addAttribute("departments", departments);
        return "modify_employee";
    }

    @PostMapping("/modify-employee")
    public String modifyEmployee(@ModelAttribute EmployeeDTO employee) {
        System.out.println(employee);
        employeeService.updateEmployee(employee);
        return "admin";
    }

    @GetMapping("/modify-record")
    public String modifyRecord(Model model) {
        List<FixRecordDTO> records=workingHourService.getFixRecord();
        if(!records.isEmpty())
            model.addAttribute("records", records);
        return "modify_record";
    }

    @PostMapping("/modify-record")
    public String modifyRecord(Model model,
                               @RequestParam(name = "workingHourId") Long workingHourId,
                               @RequestParam(name = "startTime") LocalDateTime startTime,
                               @RequestParam(name = "endTime") LocalDateTime endTime) {
        workingHourService.updateWorkingHour(workingHourId,startTime,endTime);
        List<FixRecordDTO> records=workingHourService.getFixRecord();
        if(!records.isEmpty())
            model.addAttribute("records", records);
        return "modify_record";
    }


    @PostMapping("/delete-employee")
    public String deleteEmployee(@RequestParam(name = "employeeId") Long employeeId) {
        System.out.println(employeeId);
        employeeService.deleteEmployee(employeeId);
        return "admin";
    }
}
