package com.leolmcoding.userservice.service;

import com.leolmcoding.userservice.domain.AppUser;
import com.leolmcoding.userservice.domain.Role;
import com.leolmcoding.userservice.repository.AppUserRepository;
import com.leolmcoding.userservice.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AppUserService implements UserService, UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser== null){
            log.error("User not found");
            throw new UsernameNotFoundException("User not Found");
        }
        else{
            log.info("User found in the database : {} ",appUser.getUsername());
        }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    appUser.getRoles().forEach(role->{
        authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return  new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("Saving new user to the database : {} ",appUser.getName());
        return appUserRepository.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving a new role {} to the database",role.getName());
        return  roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        appUser.getRoles().add(role);
        log.info("New Role {} added to user : {} ",roleName,username);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {}",username);
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }


}
