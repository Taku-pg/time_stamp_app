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

    public List<Employee> getEmployeeLost() {
        return employeeLost;
    }

    public void setEmployeeLost(List<Employee> employeeLost) {
        this.employeeLost = employeeLost;
    }
}
