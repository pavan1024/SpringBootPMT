package com.epam.pmt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.pmt.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
}
