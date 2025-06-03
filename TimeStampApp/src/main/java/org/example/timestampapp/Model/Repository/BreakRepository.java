package org.example.timestampapp.Model.Repository;

import org.example.timestampapp.Model.Entity.Break;
import org.springframework.data.repository.CrudRepository;

public interface BreakRepository extends CrudRepository<Break, Long> {
}
