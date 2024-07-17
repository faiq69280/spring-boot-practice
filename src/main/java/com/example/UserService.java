package com.example;

import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.SaveFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User register(User user) throws SaveFailureException{

        if(userRepository.findByName(user.getName()).isPresent())
            throw new SaveFailureException("Couldn't register user: already exists",
                    (User)user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User login(User user) throws ResourceNotFoundException
    {


        return userRepository.findByNameAndPassword(
                user.getName(),
                user.getPassword()
              ).orElseThrow(
                       () -> new ResourceNotFoundException("User doesn't exist in DB")
                     );

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByName(username).orElseGet(()->{
             throw new UsernameNotFoundException("Couldn't find username in DB");
        });

        return org.springframework.security.core.userdetails
                .User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities("ADMIN","USER")
                .build();
    }



}
