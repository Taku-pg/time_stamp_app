package org.example.timestampapp.Model.Repository;

import org.example.timestampapp.Model.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
