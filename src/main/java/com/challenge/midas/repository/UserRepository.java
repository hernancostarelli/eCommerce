package com.challenge.midas.repository;

import com.challenge.midas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "u.name LIKE :value " +
            "OR u.surname LIKE :value " +
            "AND u.deleted = false " +
            "ORDER BY u.surname ASC")
    List<User> getByValue(String value);

    @Query("SELECT u FROM User u WHERE u.deleted = false ORDER BY u.surname ASC")
    List<User> getByEnable();

    @Query("SELECT u FROM User u WHERE u.deleted = true ORDER BY u.surname ASC")
    List<User> getByDisable();

    User getUserByEmail(String email);
}