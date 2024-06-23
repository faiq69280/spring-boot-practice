package com.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    public User save(User user);
    public Optional<User> findById(Long id);

    public Optional<User> findByNameAndPassword(String name, String password);

    public Optional<User> findByName(String name);

}
