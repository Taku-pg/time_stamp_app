package org.example.timestampapp.Model.Repository;

import org.example.timestampapp.Model.Entity.WorkingHourSegment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkingHourSegmentRepository extends CrudRepository<WorkingHourSegment, Long> {

    List<WorkingHourSegment> findWorkingHourSegmentsByWorkingHour_Id(Long workingHourId);
}
