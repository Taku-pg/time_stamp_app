package org.example.timestampapp.Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Break {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    @JoinColumn(name="workinghour_id")
    private WorkingHour workingHour;

    public Break() {}
}
