package com.sunbeam.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunbeam.pojos.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findById(Long id);

	User findByEmail(String email);
}
