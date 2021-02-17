package com.home.fitness.services;

import com.home.fitness.documents.Users;
import com.home.fitness.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomUserDetailsService implements ReactiveUserDetailsService {
    private static final Logger log = Logger.getLogger(CustomUserDetailsService.class);
    private String email;
    private String password;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Mono<UserDetails> findByUsername(String email) {
        return usersRepository.findByEmail(email);

    }
    public Mono<Users> saveUser(Users user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        Roles userRole = rolesRepository.findByRole("ROLE_ADMIN");
//        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
     log.info("User saved in BD");
        return usersRepository.save(user);
    }
}
