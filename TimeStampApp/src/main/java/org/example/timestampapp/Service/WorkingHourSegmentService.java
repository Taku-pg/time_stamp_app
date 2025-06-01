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

@Service
public class WorkingHourSegmentService {

    private final WorkingHourSegmentRepository workingHourSegmentRepository;
    private final WorkingHourRepository workingHourRepository;
    private final CalculateWorkingHourSegmentService calculateWorkingHourSegmentService;

    public WorkingHourSegmentService(WorkingHourSegmentRepository workingHourSegmentRepository,
                                     WorkingHourRepository workingHourRepository,
                                     CalculateWorkingHourSegmentService calculateWorkingHourSegmentService) {
        this.workingHourSegmentRepository = workingHourSegmentRepository;
        this.workingHourRepository = workingHourRepository;
        this.calculateWorkingHourSegmentService = calculateWorkingHourSegmentService;
    }

    public void updateWorkingHourSegment(WorkingHour workingHour, LocalDateTime startTime, LocalDateTime endTime) {

    }
}
