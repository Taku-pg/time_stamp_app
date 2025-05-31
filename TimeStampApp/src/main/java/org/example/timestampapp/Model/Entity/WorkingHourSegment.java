package org.example.timestampapp.Model.Entity;

import jakarta.persistence.*;

@Entity
public class WorkingHourSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private double duration;
    @ManyToOne
    @JoinColumn(name="working_hour_id")
    private WorkingHour workingHour;
    @ManyToOne
    @JoinColumn(name = "segment_type_id")
    private SegmentType segmentType;

    public WorkingHourSegment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public WorkingHour getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(WorkingHour workingHour) {
        this.workingHour = workingHour;
    }

    public SegmentType getSegmentType() {
        return segmentType;
    }

    public void setSegmentType(SegmentType segmentType) {
        this.segmentType = segmentType;
    }
}

