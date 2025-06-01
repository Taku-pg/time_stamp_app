package org.example.timestampapp.Service;

import org.example.timestampapp.Model.Entity.SegmentType;
import org.example.timestampapp.Model.Entity.WorkingHour;
import org.example.timestampapp.Model.Entity.WorkingHourSegment;
import org.example.timestampapp.Model.Repository.SegmentTypeRepository;
import org.example.timestampapp.Model.Repository.WorkingHourRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateWorkingHourSegmentService {

    private final SegmentTypeRepository segmentTypeRepository;

    public CalculateWorkingHourSegmentService(SegmentTypeRepository segmentTypeRepository) {
        this.segmentTypeRepository = segmentTypeRepository;
    }

    public void calculateWorkingHourSegment(WorkingHour workingHour,LocalDateTime startTime, LocalDateTime endTime) {
        List<WorkingHourSegment> segments = workingHour.getSegments();
        double duration= Duration.between(startTime, endTime).toMinutes();

        LocalDateTime over=startTime.plusHours(8);
        LocalDateTime night = LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(22,0));

        if(over.isAfter(endTime) && night.isAfter(endTime)) {
            double untilNight=Duration.between(startTime,night).toMinutes();

            segments.add(createSegment(workingHour,duration-480,"over-night"));

            if(untilNight>480){
                //overtime + over-night
                segments.add(createSegment(workingHour,untilNight-480,"overtime"));
                segments.add(createSegment(workingHour,480,"regular"));
            }else{
                //night + over-night
                segments.add(createSegment(workingHour,480-untilNight,"night"));
                segments.add(createSegment(workingHour,untilNight,"regular"));
            }

        }else if(over.isAfter(endTime)){
            segments.add(createSegment(workingHour,duration-480,"overtime"));
            segments.add(createSegment(workingHour,480,"regular"));

        }else if(night.isAfter(endTime)){
            double untilNight=Duration.between(startTime,night).toMinutes();
            segments.add(createSegment(workingHour,duration-untilNight,"night"));
            segments.add(createSegment(workingHour,untilNight,"regular"));

        }else{
            segments.add(createSegment(workingHour,480,"regular"));
        }

    }

    private WorkingHourSegment createSegment(WorkingHour workingHour, double duration, String type) {
        SegmentType segmentType=segmentTypeRepository.findSegmentTypeByName(type);
        return new WorkingHourSegment(duration,workingHour,segmentType);
    }
}
