package com.example.demo.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

  User findByEmail(String email);
  List<User> findByClubId(UUID clubId);

}
