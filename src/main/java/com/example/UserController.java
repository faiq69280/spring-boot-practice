package com.example;

import com.example.security.AuthenticationResponse;
import com.example.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){

         return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user){

        User authentication = userService.login(user);
        UserDetails userDetails =  org.springframework.security.core.userdetails
                .User.builder()
                .username(authentication.getName())
                .password(authentication.getPassword())
                .authorities("ADMIN","USER")
                .build();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getAuthorities()
        ));
        String jwtToken = jwtUtils.getJwtFromUserName(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(
                Objects::toString
        ).toList();

       AuthenticationResponse response =  new AuthenticationResponse(jwtToken,roles, userDetails.getUsername());

        return new ResponseEntity<>(
                response,HttpStatus.OK
        );

    }

}
