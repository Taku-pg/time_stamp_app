package org.example.timestampapp.Model.Repository;

import org.example.timestampapp.Model.Entity.SegmentType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SegmentTypeRepository extends CrudRepository<SegmentType,Long> {
    Optional<SegmentType> findSegmentTypeByName(String name);
}
