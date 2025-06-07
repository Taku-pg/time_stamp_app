package org.example.timestampapp.Model.Repository;

import org.example.timestampapp.Model.Entity.Break;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BreakRepository extends CrudRepository<Break, Long> {
    @Query("SELECT b FROM Break b WHERE b.endTime IS NULL AND b.workingHour.id= :workingHourId")
    Optional<Break> findCurrentBreakByWorkingHourId(Long workingHourId);
}
