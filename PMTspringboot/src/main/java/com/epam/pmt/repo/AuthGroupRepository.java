package com.epam.pmt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.pmt.entities.AuthGroup;



public interface AuthGroupRepository extends JpaRepository<AuthGroup, Long> {
    List<AuthGroup> findByUsername(String username);
}