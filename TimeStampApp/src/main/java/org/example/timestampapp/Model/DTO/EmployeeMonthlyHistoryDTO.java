package org.example.timestampapp.Model.DTO;

import java.util.List;

public class EmployeeMonthlyHistoryDTO {
    private int year;
    private int month;
    private List<EmployeeHistoryDTO> histories;
    private double totalSalary;

    public EmployeeMonthlyHistoryDTO(int year, int month, List<EmployeeHistoryDTO> histories) {
        this.year = year;
        this.month = month;
        this.histories = histories;
        for (EmployeeHistoryDTO employeeHistoryDTO : histories) {
            totalSalary+=employeeHistoryDTO.getCalculatedSalary();
        }
        totalSalary=Math.round(totalSalary*100)/100.0;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<EmployeeHistoryDTO> getHistories() {
        return histories;
    }

    public void setHistories(List<EmployeeHistoryDTO> histories) {
        this.histories = histories;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
}
