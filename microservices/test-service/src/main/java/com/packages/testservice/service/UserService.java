package com.packages.testservice.service;

import com.packages.testservice.entity.UserTest;
import com.packages.testservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserTest> getlAll() {
        return userRepository.findAll();
    }

    public UserTest getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserTest save(UserTest user){
        UserTest userNew = userRepository.save(user);
        return userNew;
    }


}
