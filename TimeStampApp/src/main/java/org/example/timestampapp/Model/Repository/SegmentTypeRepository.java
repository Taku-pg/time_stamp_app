package org.example.timestampapp.Model.Repository;

import org.example.timestampapp.Model.Entity.SegmentType;
import org.springframework.data.repository.CrudRepository;

public interface SegmentTypeRepository extends CrudRepository<SegmentType,Long> {
    SegmentType findSegmentTypeByName(String name);
}
