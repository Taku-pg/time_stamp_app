package org.example.timestampapp.Model.Entity;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int salary;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;
    @ManyToOne
    @JoinColumn(name="status_id")
    private Status status;

    public Employee() {}
}
