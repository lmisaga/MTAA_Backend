package com.sclad.scladapp.repository;

import com.sclad.scladapp.entity.Authority;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByUsername(String username);
}
