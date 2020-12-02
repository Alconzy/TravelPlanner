package com.laioffer.TravelPlanner.repository;

import com.laioffer.TravelPlanner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
