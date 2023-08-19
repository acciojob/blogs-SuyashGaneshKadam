package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){
        User user = new User(username,password);
        userRepository3.save(user);
        return user;
    }

    public void deleteUser(int userId){
        userRepository3.deleteById(userId);
    }

    public User updateUser(Integer id, String password){
        Optional<User> optionalUser = userRepository3.findById(id);
        if(!optionalUser.isPresent()){
            return null;
        }
        User user = optionalUser.get();
        user.setPassword(password);
        userRepository3.save(user);
        return null;
    }
}
