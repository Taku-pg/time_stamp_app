package org.example.timestampapp.Model.Repository;

import org.example.timestampapp.Model.Entity.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatusRepository extends CrudRepository<Status, Long> {
    Optional<Status> findStatusByType(String type);
}
