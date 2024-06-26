package com.example;

import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.SaveFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public User register(User user) throws SaveFailureException{

        if(userRepository
                .findByName(user.getName())
                .isPresent())
            throw new SaveFailureException("Couldn't register user: already exists",
                    (User)user);

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



}
