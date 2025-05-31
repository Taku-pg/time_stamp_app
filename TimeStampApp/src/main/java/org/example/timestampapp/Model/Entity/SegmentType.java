package org.example.timestampapp.Model.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SegmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double magnification;
    @OneToMany(mappedBy = "segmentType")
    private List<WorkingHourSegment> workingHourSegments;

    public SegmentType() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMagnification() {
        return magnification;
    }

    public void setMagnification(double magnification) {
        this.magnification = magnification;
    }

    public List<WorkingHourSegment> getWorkingHourSegments() {
        return workingHourSegments;
    }

    public void setWorkingHourSegments(List<WorkingHourSegment> workingHourSegments) {
        this.workingHourSegments = workingHourSegments;
    }
}
