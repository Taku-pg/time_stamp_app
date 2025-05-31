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
    @JoinColumn(name="workinghour_id")
    private WorkingHour workingHour;

    public WorkingHourSegment() {}
}

