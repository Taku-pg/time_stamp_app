package org.example.timestampapp.Model.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EmployeeHistoryDTO {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private double calculatedSalary;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public double getCalculatedSalary() {
        return calculatedSalary;
    }

    public void setCalculatedSalary(double calculatedSalary) {
        this.calculatedSalary = calculatedSalary;
    }
}
