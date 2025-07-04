package org.example.timestampapp.Repository;

import org.example.timestampapp.Model.Entity.WorkingHour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WorkingHourRepository extends CrudRepository<WorkingHour, Long> {
    @Query("SELECT w FROM WorkingHour w LEFT JOIN w.segments " +
            "LEFT JOIN w.breaks " +
            "WHERE w.employee.id= :id AND YEAR(w.startTime) = :year AND MONTH(w.startTime) = :month")
    List<WorkingHour> findDetailWorkingHourByEmployeeId(@Param("id") long employeeId,
                                                        @Param("year") int year,
                                                        @Param("month") int month);
    @Query("SELECT w FROM WorkingHour w LEFT JOIN w.segments " +
            "LEFT JOIN w.breaks " +
            "WHERE w.employee.department.name= :dName AND YEAR(w.startTime) = :year AND MONTH(w.startTime) = :month")
    List<WorkingHour> findDetailWorkingHourByDepartmentName(@Param("dName") String dName,
                                                        @Param("year") int year,
                                                        @Param("month") int month);

    @Query("SELECT w FROM WorkingHour w LEFT JOIN w.segments WHERE w.autoLeave=TRUE")
    List<WorkingHour> findWorkingHourWithAutoLeave();

    @Query("SELECT w FROM WorkingHour w WHERE w.endTime IS NULL AND w.employee.id= :employeeId")
    Optional<WorkingHour> findCurrentWorkingHourByEmployeeId(Long employeeId);

    List<WorkingHour> findAllByEndTimeIsNull();
}
