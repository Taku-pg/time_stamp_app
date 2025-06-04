package org.example.timestampapp.Service;

import org.example.timestampapp.Model.Entity.Break;
import org.example.timestampapp.Model.Entity.WorkingHour;
import org.example.timestampapp.Model.Repository.BreakRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
                .orElse(null);

        if (break_ == null) {
            throw new RuntimeException("No break found");
        }

        break_.setEndTime(LocalDateTime.now());
        breakRepository.save(break_);
    }
}
