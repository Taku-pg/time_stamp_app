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


        if(endTime.isAfter(over) && endTime.isAfter(night)) {
            double untilNight=Duration.between(startTime,night).toMinutes();
            //over-night work
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

        }else if(endTime.isAfter(over)){
            //overtime and regular work
            segments.add(createSegment(workingHour,duration-480,"overtime"));
            segments.add(createSegment(workingHour,480,"regular"));

        }else if(endTime.isAfter(night)){
            //night and regular(optionally)
            double untilNight=Duration.between(startTime,night).toMinutes();
            segments.add(createSegment(workingHour,duration-untilNight,"night"));
            if(untilNight>0)
                segments.add(createSegment(workingHour,untilNight,"regular"));

        }else{
            //only regular work
            segments.add(createSegment(workingHour,duration,"regular"));
        }

    }

    private WorkingHourSegment createSegment(WorkingHour workingHour, double duration, String type) {
        SegmentType segmentType=segmentTypeRepository.findSegmentTypeByName(type).orElse(null);
        if(segmentType==null) {
            throw new RuntimeException("No such segment type");
        }
        return new WorkingHourSegment(duration,workingHour,segmentType);
    }
}
