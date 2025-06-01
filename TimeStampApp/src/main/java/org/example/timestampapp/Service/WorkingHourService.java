package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.EmployeeWorkingStatisticsDTO;
import org.example.timestampapp.Model.Entity.WorkingHour;
import org.example.timestampapp.Model.Repository.WorkingHourRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingHourService {
    private final WorkingHourRepository workingHourRepository;
    private final WorkingHourMapper workingHourMapper;
    public WorkingHourService(WorkingHourRepository workingHourRepository,
                              WorkingHourMapper workingHourMapper) {
        this.workingHourRepository = workingHourRepository;
        this.workingHourMapper = workingHourMapper;
    }

    public EmployeeWorkingStatisticsDTO getWorkingHourStatistics(Long employeeId,int year,int month) {
        List<WorkingHour> monthlyRecords=workingHourRepository.findDetailWorkingHourByEmployeeId(employeeId,year,month);
        return workingHourMapper.mapStatistics(monthlyRecords,year,month,employeeId);
    }

}
