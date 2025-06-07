package org.example.timestampapp.Service;

import org.example.timestampapp.Model.Entity.Break;
import org.example.timestampapp.Model.Entity.WorkingHour;
import org.example.timestampapp.Repository.BreakRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class BreakService {
    private final BreakRepository breakRepository;

    public BreakService(BreakRepository breakRepository) {
        this.breakRepository = breakRepository;
    }

    public void breakTimeStamp(WorkingHour workingHour) {
        Break break_=new Break(LocalDateTime.now(), workingHour);
        breakRepository.save(break_);
    }

    public void backTimeStamp(Long workingHourId) {
        Break break_ = breakRepository
                .findCurrentBreakByWorkingHourId(workingHourId)
                .orElseThrow(()->new NoSuchElementException("No break segment found"));
        break_.setEndTime(LocalDateTime.now());
        breakRepository.save(break_);
    }
}
