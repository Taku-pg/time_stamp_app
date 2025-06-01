package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.EmployeeWorkingStatisticsDTO;
import org.example.timestampapp.Model.Entity.Break;
import org.example.timestampapp.Model.Entity.SegmentType;
import org.example.timestampapp.Model.Entity.WorkingHour;
import org.example.timestampapp.Model.Entity.WorkingHourSegment;
import org.example.timestampapp.Model.Repository.SegmentTypeRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkingHourMapper {

    private final SegmentTypeRepository segmentTypeRepository;

    public WorkingHourMapper(SegmentTypeRepository segmentTypeRepository) {
        this.segmentTypeRepository = segmentTypeRepository;
    }

    public EmployeeWorkingStatisticsDTO mapStatistics(List<WorkingHour> monthlyRecord,int year,int month,Long employeeId) {
        EmployeeWorkingStatisticsDTO recordDTO=new EmployeeWorkingStatisticsDTO();
        double regular=0;
        double overTime=0;
        double night=0;
        double overNight=0;
        double breakHours=0;
        for(WorkingHour workingHour : monthlyRecord) {
            //calculate working hours
            List<WorkingHourSegment> segments=workingHour.getSegments();
            for(WorkingHourSegment segment : segments) {
                System.out.println(segment.getId());
                Optional<SegmentType> type=segmentTypeRepository.findById(segment.getSegmentType().getId());
                if(type.isPresent()) {
                    System.out.println("type:"+type.get().getName());
                    String typeName=type.get().getName();
                    switch(typeName) {
                        case "regular":
                            regular += segment.getDuration();
                            break;
                        case "overtime":
                            overTime += segment.getDuration();
                            break;
                        case "night":
                            night += segment.getDuration();
                            break;
                        case "over-night":
                            overNight += segment.getDuration();
                            break;
                    }
                }
            }
            System.out.println(regular);
            System.out.println(overTime);
            System.out.println(night);
            System.out.println(overNight);
            System.out.println(breakHours);

            //calculate break time
            List<Break> breaks=workingHour.getBreaks();
            for(Break break_ : breaks) {
                Duration duration=Duration.between(break_.getStartTime(),break_.getEndTime());
                breakHours+=duration.toMinutes();
            }
        }
        regular=(double)Math.round((100*regular)/60)/100;
        overTime=(double)Math.round((100*overTime)/60)/100;
        night=(double)Math.round((100*night)/60)/100;
        overNight=(double)Math.round((100*overNight)/60)/100;
        breakHours=(double)Math.round((100*breakHours)/60)/100;

        double totalHours=regular+overTime+night+overNight-breakHours;

        recordDTO.setEmployeeId(employeeId);
        recordDTO.setRegular(regular);
        recordDTO.setOverTime(overTime);
        recordDTO.setNightShift(night);
        recordDTO.setOverNight(overNight);
        recordDTO.setBreakTime(breakHours);
        recordDTO.setTotal(totalHours);
        recordDTO.setYear(year);
        recordDTO.setMonth(month);

        return recordDTO;
    }
}
