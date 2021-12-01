package com.leolmcoding.userservice.service;

import com.leolmcoding.userservice.domain.AppUser;
import com.leolmcoding.userservice.domain.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    AppUser getUser(String username);//dessa forma esta se assumindo que haverá nomes unicos
    List<AppUser> getUsers();
}
