package com.leolmcoding.userservice.repository;

import com.leolmcoding.userservice.domain.AppUser;
import com.leolmcoding.userservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
