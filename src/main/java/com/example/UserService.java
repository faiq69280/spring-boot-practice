package com.example;

import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.SaveFailureException;
import com.example.security.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
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
    @Qualifier("customPasswordEncoder")
    CustomPasswordEncoder passwordEncoder;

    public User register(User user) throws SaveFailureException{

        if(userRepository.findByName(user.getName()).isPresent())
            throw new SaveFailureException("Couldn't register user: already exists",
                    (User)user);

        passwordEncoder.generateSalt();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSalt(passwordEncoder.getSalt());

        return userRepository.save(user);
    }

    public User login(User user) throws AuthenticationCredentialsNotFoundException
    {
        User fetchedUser = userRepository.findByName(user.getName()).orElseGet(()->{
            throw new UsernameNotFoundException("Couldn't find username in DB");
        });

        passwordEncoder.setSalt(fetchedUser.getSalt());
        return userRepository.findByNameAndPassword(
                user.getName(),
                passwordEncoder.encode(user.getPassword())
              ).orElseThrow(
                       () -> new AuthenticationCredentialsNotFoundException("Couldn't find credentials")
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
