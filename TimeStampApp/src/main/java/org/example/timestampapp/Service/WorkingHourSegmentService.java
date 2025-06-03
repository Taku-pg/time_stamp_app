package org.example.timestampapp.Service;

import org.example.timestampapp.Model.Entity.SegmentType;
import org.example.timestampapp.Model.Entity.WorkingHour;
import org.example.timestampapp.Model.Entity.WorkingHourSegment;
import org.example.timestampapp.Model.Repository.SegmentTypeRepository;
import org.example.timestampapp.Model.Repository.WorkingHourRepository;
import org.example.timestampapp.Model.Repository.WorkingHourSegmentRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkingHourSegmentService {

    private final CalculateWorkingHourSegmentService calculateWorkingHourSegmentService;
    private final WorkingHourSegmentRepository workingHourSegmentRepository;

    public WorkingHourSegmentService(
                                     CalculateWorkingHourSegmentService calculateWorkingHourSegmentService,
                                     WorkingHourSegmentRepository workingHourSegmentRepository) {
        this.calculateWorkingHourSegmentService = calculateWorkingHourSegmentService;
        this.workingHourSegmentRepository = workingHourSegmentRepository;
    }

    public List<WorkingHourSegment> getWorkingHourSegment(Long workingHourId) {
        return workingHourSegmentRepository.findWorkingHourSegmentsByWorkingHour_Id(workingHourId);
    }

    public void updateWorkingHourSegment(WorkingHour workingHour, LocalDateTime startTime, LocalDateTime endTime) {
        calculateWorkingHourSegmentService.calculateWorkingHourSegment(workingHour, startTime, endTime);
    }
}
