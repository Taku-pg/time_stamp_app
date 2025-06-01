package org.example.timestampapp.Controller.ApiController;

import org.example.timestampapp.Model.DTO.DepartmentStatisticsDTO;
import org.example.timestampapp.Service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/department")
public class DepartmentApiController {

    private final DepartmentService departmentService;

    public DepartmentApiController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{name}/statistics/{year}/{month}")
    public ResponseEntity<DepartmentStatisticsDTO> getEmployeeWorkingStatistics(@PathVariable String name,
                                                                              @PathVariable int year,
                                                                              @PathVariable int month) {
        DepartmentStatisticsDTO statistics=departmentService.getDeptStatistics(name,year,month);
        System.out.println("from api "+statistics);
        if(statistics==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(statistics);
    }
}
