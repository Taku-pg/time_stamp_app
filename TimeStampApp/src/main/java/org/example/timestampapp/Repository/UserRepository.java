package org.example.timestampapp.Repository;

import org.example.timestampapp.Model.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findUserByUsername(String username);
}
