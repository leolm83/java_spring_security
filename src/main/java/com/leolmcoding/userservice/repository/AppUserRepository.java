package com.leolmcoding.userservice.repository;

import com.leolmcoding.userservice.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
//JpaRepository<Classe, TipoChavePrimaria>
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
