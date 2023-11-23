package com.example.securoles.services;


import com.example.securoles.model.MyUser;
import com.example.securoles.repo.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepo.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserDetails userDetails = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
        return userDetails;
    }

    public Optional<MyUser> findByName(String username) {
        return userRepo.findByName(username);
    }

    public void saveUser(MyUser user){
        userRepo.save(user);
    }
}
