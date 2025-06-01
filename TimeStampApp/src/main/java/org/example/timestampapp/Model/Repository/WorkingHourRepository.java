package org.example.timestampapp.Model.Repository;

import org.example.timestampapp.Model.Entity.WorkingHour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkingHourRepository extends CrudRepository<WorkingHour, Long> {
    @Query("SELECT w FROM WorkingHour w LEFT JOIN w.segments " +
            "LEFT JOIN FETCH w.breaks " +
            "WHERE w.employee.id= :id AND YEAR(w.startTime) = :year AND MONTH(w.startTime) = :month")
    List<WorkingHour> findDetailWorkingHourByEmployeeId(@Param("id") long employeeId,
                                                        @Param("year") int year,
                                                        @Param("month") int month);
    @Query("SELECT w FROM WorkingHour w LEFT JOIN w.segments " +
            "LEFT JOIN FETCH w.breaks " +
            "WHERE w.employee.department.name= :dName AND YEAR(w.startTime) = :year AND MONTH(w.startTime) = :month")
    List<WorkingHour> findDetailWorkingHourByDepartmentName(@Param("dName") String dName,
                                                        @Param("year") int year,
                                                        @Param("month") int month);
}
