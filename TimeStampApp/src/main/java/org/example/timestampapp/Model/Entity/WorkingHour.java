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
    @OneToMany
    private List<WorkingHourSegment> segments;
    @OneToMany
    private List<Break> breaks;

    public WorkingHour() {}
}
