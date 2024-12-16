package com.kelompok2.tubespbo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kelompok2.tubespbo.models.UserEntity;
import com.kelompok2.tubespbo.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserEntity user = userRepository.findByUsername(username).get();
        if(user != null) {
            List<SimpleGrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(username));
            User authUser = new User(
                user.getUsername(),
                user.getPassword(),
                roles
            );
            return authUser;
        } else {
            throw new UsernameNotFoundException("Invalid user credentials!");
        }

    }

}
