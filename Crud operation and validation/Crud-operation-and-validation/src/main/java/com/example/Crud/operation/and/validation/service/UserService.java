package com.example.Crud.operation.and.validation.service;


import com.example.Crud.operation.and.validation.model.User;
import com.example.Crud.operation.and.validation.reposatory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
     UserRepository userRepository;


    public void saveuser(User newuser) {

        userRepository.save(newuser);
    }

    public List<User> getUser(Integer userId) {
        List<User> users;
        if(userId==null){
            users=userRepository.findAll();
        }
        else{
            users=new ArrayList<>();
            users.add(userRepository.findById(userId).get());
        }
        return users;
    }

    public void updateUser(Integer userId, User newuser) {
        User user= userRepository.findById(userId).get();

        user.setUserId(newuser.getUserId());
        user.setUserName(newuser.getUserName());
        user.setDateOfBirth(newuser.getDateOfBirth());
        user.setEmail(newuser.getEmail());
        user.setPhoneNumber(newuser.getPhoneNumber());
        user.setDate(newuser.getDate());
        user.setTime(newuser.getTime());

        userRepository.save(newuser);
    }


    public void deleteuser(Integer userId) {
        User user= (User) userRepository.findById(userId).get();
        userRepository.delete(user);
    }
}
