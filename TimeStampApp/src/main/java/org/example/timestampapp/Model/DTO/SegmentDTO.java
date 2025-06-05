package org.example.timestampapp.Model.DTO;

import java.time.LocalDateTime;

public class SegmentDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long duration;

    @Override
    public String toString() {
        return startTime.toString() + " - " + endTime.toString()+" "+duration;
    }

    public SegmentDTO(LocalDateTime startTime, LocalDateTime endTime, Long duration) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
