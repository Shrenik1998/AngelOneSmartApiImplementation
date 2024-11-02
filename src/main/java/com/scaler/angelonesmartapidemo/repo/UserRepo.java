package com.scaler.angelonesmartapidemo.repo;

import com.scaler.angelonesmartapidemo.models.Users;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByUserName(String username);
    Users save(Users user);
}
