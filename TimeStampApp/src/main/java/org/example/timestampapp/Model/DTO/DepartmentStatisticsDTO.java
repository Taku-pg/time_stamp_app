package org.example.timestampapp.Model.DTO;

public class DepartmentStatisticsDTO {
    private String name;
    private Double regular;
    private Double nightShift;
    private Double overTime;
    private Double overNight;
    private Double breakTime;
    private Double total;
    private Integer year;
    private Integer month;

    @Override
    public String toString() {
        return name+" "+regular+" "+nightShift+" "+overTime+" "+overNight+" "+breakTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRegular() {
        return regular;
    }

    public void setRegular(Double regular) {
        this.regular = regular;
    }

    public Double getNightShift() {
        return nightShift;
    }

    public void setNightShift(Double nightShift) {
        this.nightShift = nightShift;
    }

    public Double getOverTime() {
        return overTime;
    }

    public void setOverTime(Double overTime) {
        this.overTime = overTime;
    }

    public Double getOverNight() {
        return overNight;
    }

    public void setOverNight(Double overNight) {
        this.overNight = overNight;
    }

    public Double getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Double breakTime) {
        this.breakTime = breakTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
