package org.example.timestampapp.Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class WorkingHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean autoLeave;
    @OneToMany(mappedBy = "workingHour", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<WorkingHourSegment> segments;
    @OneToMany(mappedBy = "workingHour")
    private List<Break> breaks;
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    public WorkingHour() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isAutoLeave() {
        return autoLeave;
    }

    public void setAutoLeave(boolean autoLeave) {
        this.autoLeave = autoLeave;
    }

    public List<WorkingHourSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<WorkingHourSegment> segments) {
        this.segments = segments;
    }

    public List<Break> getBreaks() {
        return breaks;
    }

    public void setBreaks(List<Break> breaks) {
        this.breaks = breaks;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
