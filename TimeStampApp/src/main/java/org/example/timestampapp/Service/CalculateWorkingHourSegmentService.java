package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.SegmentDTO;
import org.example.timestampapp.Model.Entity.Break;
import org.example.timestampapp.Model.Entity.SegmentType;
import org.example.timestampapp.Model.Entity.WorkingHour;
import org.example.timestampapp.Model.Entity.WorkingHourSegment;
import org.example.timestampapp.Model.Repository.SegmentTypeRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CalculateWorkingHourSegmentService {

    private final SegmentTypeRepository segmentTypeRepository;
    private final LocalTime NIGHT = LocalTime.of(22,0);
    private final LocalTime END_NIGHT = LocalTime.of(5,0);

    public CalculateWorkingHourSegmentService(SegmentTypeRepository segmentTypeRepository) {
        this.segmentTypeRepository = segmentTypeRepository;
    }

    public void calculateWorkingHourSegment(WorkingHour workingHour,LocalDateTime startTime, LocalDateTime endTime) {
        List<WorkingHourSegment> segments = workingHour.getSegments();
        Iterator<Break> breaks = workingHour.getBreaks().iterator();
        List<SegmentDTO> segmentDurations = new ArrayList<>();

        if(breaks.hasNext()) {
            Break first=breaks.next();
            segmentDurations.add(
                    new SegmentDTO(startTime,
                            first.getEndTime(),
                            Duration.between(startTime,first.getStartTime()).toMinutes()));
            while (breaks.hasNext()) {
                Break second=breaks.next();
                segmentDurations.add(
                        new SegmentDTO(first.getEndTime(),
                                second.getStartTime(),
                                Duration.between(first.getEndTime(), second.getStartTime()).toMinutes()));
                first = second;
            }
            segmentDurations.add(
                    new SegmentDTO(first.getEndTime(),
                            endTime,
                            Duration.between(first.getEndTime(),endTime).toMinutes()));
        }else{
            //without break
            segmentDurations.add(
                    new SegmentDTO(startTime,
                            endTime,
                            Duration.between(startTime,endTime).toMinutes()));
        }
        LocalDateTime night = LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(22,0));
        long regularDuration=0;
        long nightDuration=0;
        long overDuration=0;
        long overNightDuration=0;
        for(SegmentDTO segmentDuration:segmentDurations) {
            if (segmentDuration.getEndTime().toLocalTime().isBefore(NIGHT)
                    && segmentDuration.getEndTime().toLocalTime().isAfter(END_NIGHT)) {
                //before night
                if(regularDuration+segmentDuration.getDuration()<=480) {
                    //only regular
                    regularDuration+=segmentDuration.getDuration();
                }else{
                    //regular or overtime
                    overDuration+=segmentDuration.getDuration()-(480-regularDuration);
                    regularDuration=480;
                }
            }else{
                if(segmentDuration.getStartTime().toLocalTime().isBefore(NIGHT)
                        && segmentDuration.getStartTime().toLocalTime().isAfter(END_NIGHT)) {
                    //until night: regular or over
                    long untilNight=Duration.between(segmentDuration.getStartTime(),night).toMinutes();
                    if(regularDuration+untilNight<=480) {
                        //only regular
                        regularDuration+=untilNight;
                    }else{
                        //regular + over
                        overDuration+=untilNight-(480-regularDuration);
                        regularDuration=480;
                    }

                    //after night: night or over + night
                    long afterNight=segmentDuration.getDuration()-untilNight;
                    if(regularDuration+afterNight<=480) {
                        //only night
                        nightDuration+=afterNight;
                    }else{
                        //night and over + night
                        nightDuration+=480-regularDuration;
                        overNightDuration+=afterNight-(480-regularDuration);
                    }
                }

            }
        }

        if(regularDuration>0)
            segments.add(createSegment(workingHour,regularDuration,"regular"));
        if(nightDuration>0)
            segments.add(createSegment(workingHour,nightDuration,"night"));
        if(overDuration>0)
            segments.add(createSegment(workingHour,overDuration,"overtime"));
        if(overNightDuration>0)
            segments.add(createSegment(workingHour,overNightDuration,"over-night"));

        /*double duration= Duration.between(startTime, endTime).toMinutes();



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
        }*/

    }

    private WorkingHourSegment createSegment(WorkingHour workingHour, double duration, String type) {
        SegmentType segmentType=segmentTypeRepository.findSegmentTypeByName(type).orElse(null);
        if(segmentType==null) {
            throw new RuntimeException("No such segment type");
        }
        return new WorkingHourSegment(duration,workingHour,segmentType);
    }
}
