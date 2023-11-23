package com.example.securoles.repo;

import com.example.securoles.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<MyUser, Integer>{
     Optional<MyUser> findByName(String name);
}
