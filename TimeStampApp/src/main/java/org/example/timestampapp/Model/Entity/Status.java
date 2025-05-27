package org.example.timestampapp.Model.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @OneToMany(mappedBy = "status")
    private List<Employee> employeeLost;

    public Status(){}
}
