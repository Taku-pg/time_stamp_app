package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.DepartmentStatisticsDTO;
import org.example.timestampapp.Model.DTO.EmployeeHistoryDTO;
import org.example.timestampapp.Model.DTO.EmployeeStatisticsDTO;
import org.example.timestampapp.Model.DTO.FixRecordDTO;
import org.example.timestampapp.Model.Entity.Break;
import org.example.timestampapp.Model.Entity.WorkingHour;
import org.example.timestampapp.Model.Entity.WorkingHourSegment;
import org.example.timestampapp.Model.Repository.WorkingHourRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkingHourService {
    private final WorkingHourRepository workingHourRepository;
    private final WorkingHourMapper workingHourMapper;
    private final WorkingHourSegmentService workingHourSegmentService;

    public WorkingHourService(WorkingHourRepository workingHourRepository,
                              WorkingHourMapper workingHourMapper,
                              WorkingHourSegmentService workingHourSegmentService) {
        this.workingHourRepository = workingHourRepository;
        this.workingHourMapper = workingHourMapper;
        this.workingHourSegmentService = workingHourSegmentService;
    }


    public List<FixRecordDTO> getFixRecord() {
        return workingHourMapper.mapFixRecord(workingHourRepository.findWorkingHourWithAutoLeave());
    }

    public List<EmployeeHistoryDTO> getEmployeeHistory(Long employeeId, int year, int month) {
        List<EmployeeHistoryDTO> history = new ArrayList<>();
        List<WorkingHour> monthlyRecord=workingHourRepository.findDetailWorkingHourByEmployeeId(employeeId,year,month);
        for (WorkingHour workingHour : monthlyRecord) {
            List<WorkingHourSegment> segments=workingHourSegmentService.getWorkingHourSegment(workingHour.getId());
            history.add(workingHourMapper.mapEmployeeHistoryDTO(workingHour, segments));
        }
        return history;
    }

    @Transactional
    public void updateWorkingHour(Long workingHourId,LocalDateTime startTime,LocalDateTime endTime) {
        WorkingHour target=workingHourRepository.findById(workingHourId).orElse(null);
        if(target==null)
            throw new RuntimeException("Internal Error");
        target.getBreaks().removeIf(break_ -> break_.getEndTime().isAfter(endTime));
        target.setStartTime(startTime);
        target.setEndTime(endTime);
        target.setAutoLeave(false);
        target.getSegments().clear();
        workingHourSegmentService.updateWorkingHourSegment(target,startTime,endTime);
        workingHourRepository.save(target);
    }

}
