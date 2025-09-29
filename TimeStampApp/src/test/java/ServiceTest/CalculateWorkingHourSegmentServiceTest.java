package ServiceTest;

import org.example.timestampapp.Model.Entity.Break;
import org.example.timestampapp.Model.Entity.SegmentType;
import org.example.timestampapp.Model.Entity.WorkingHour;
import org.example.timestampapp.Repository.SegmentTypeRepository;
import org.example.timestampapp.Service.CalculateWorkingHourSegmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculateWorkingHourSegmentServiceTest {
    @Mock
    SegmentTypeRepository segmentTypeRepository;

    @InjectMocks
    private CalculateWorkingHourSegmentService calculateWorkingHourSegmentService;

    @Test
    void calculateWorkingHourSegment_RegularWithoutBreak() {
        WorkingHour mockWorkingHour = new WorkingHour();
        mockWorkingHour.setStartTime(LocalDateTime.of(2025,1,1,9,0));
        mockWorkingHour.setEndTime(LocalDateTime.of(2025,1,1,17,0));
        mockWorkingHour.setSegments(new ArrayList<>());
        mockWorkingHour.setBreaks(List.of());

        SegmentType mockRegularType = new SegmentType();
        mockRegularType.setName("regular");
        when(segmentTypeRepository.findSegmentTypeByName("regular")).thenReturn(Optional.of(mockRegularType));
        /*SegmentType mockOverType = new SegmentType();
        mockRegularType.setName("overtime");
        when(segmentTypeRepository.findSegmentTypeByName("overtime")).thenReturn(Optional.of(mockOverType));*/

        calculateWorkingHourSegmentService.calculateWorkingHourSegment(mockWorkingHour, mockWorkingHour.getStartTime(), mockWorkingHour.getEndTime());

        assertEquals(1, mockWorkingHour.getSegments().size());
        assertEquals(480, mockWorkingHour.getSegments().getFirst().getDuration());
        //assertEquals(180, mockWorkingHour.getSegments().get(1).getDuration());
        assertEquals("regular", mockWorkingHour.getSegments().getFirst().getSegmentType().getName());
        //assertEquals("overtime", mockWorkingHour.getSegments().get(1).getSegmentType().getName());
    }

    @Test
    void calculateWorkingHourSegment_RegularWithBreak() {
        WorkingHour mockWorkingHour = new WorkingHour();
        mockWorkingHour.setStartTime(LocalDateTime.of(2025,1,1,9,0));
        mockWorkingHour.setEndTime(LocalDateTime.of(2025,1,1,18,0));
        mockWorkingHour.setSegments(new ArrayList<>());

        Break mockBreak = new Break();
        mockBreak.setStartTime(LocalDateTime.of(2025,1,1,11,0));
        mockBreak.setEndTime(LocalDateTime.of(2025,1,1,12,0));
        mockWorkingHour.setBreaks(List.of(mockBreak));

        SegmentType mockRegularType = new SegmentType();
        mockRegularType.setName("regular");
        when(segmentTypeRepository.findSegmentTypeByName("regular")).thenReturn(Optional.of(mockRegularType));
        /*SegmentType mockOverType = new SegmentType();
        mockRegularType.setName("overtime");
        when(segmentTypeRepository.findSegmentTypeByName("overtime")).thenReturn(Optional.of(mockOverType));*/

        calculateWorkingHourSegmentService.calculateWorkingHourSegment(mockWorkingHour, mockWorkingHour.getStartTime(), mockWorkingHour.getEndTime());

        assertEquals(1, mockWorkingHour.getSegments().size());
        assertEquals(480, mockWorkingHour.getSegments().getFirst().getDuration());
        //assertEquals(180, mockWorkingHour.getSegments().get(1).getDuration());
        assertEquals("regular", mockWorkingHour.getSegments().getFirst().getSegmentType().getName());
        //assertEquals("overtime", mockWorkingHour.getSegments().get(1).getSegmentType().getName());
    }
}
