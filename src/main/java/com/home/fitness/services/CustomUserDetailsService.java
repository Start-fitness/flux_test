package com.home.fitness.services;

import com.home.fitness.documents.Roles;
import com.home.fitness.documents.Users;
import com.home.fitness.repository.RolesRepository;
import com.home.fitness.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Service
public class CustomUserDetailsService implements ReactiveUserDetailsService {
    private static final Logger log = Logger.getLogger(CustomUserDetailsService.class);
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Mono<UserDetails> findByUsername(String email) {
        return usersRepository.findUsersByEmail(email);
    }
    public Mono<Users> saveUser(Users user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        Roles userRole = rolesRepository.findByRole("ADMIN");
//        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
     log.info("User saved in BD");
        return usersRepository.save(user);
    }
    public static CustomUserDetailsService fromUserEntityToCustomUserDetails(Users user) {
        CustomUserDetailsService userDetails = new CustomUserDetailsService();
        userDetails.email = user.getEmail();
        userDetails.password = user.getPassword();
        userDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        return userDetails;
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
}
