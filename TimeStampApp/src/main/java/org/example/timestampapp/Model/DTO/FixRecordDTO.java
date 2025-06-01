package org.example.timestampapp.Model.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FixRecordDTO {
    private Long employeeId;
    private Long workingHourId;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getWorkingHourId() {
        return workingHourId;
    }

    public void setWorkingHourId(Long workingHourId) {
        this.workingHourId = workingHourId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
}
