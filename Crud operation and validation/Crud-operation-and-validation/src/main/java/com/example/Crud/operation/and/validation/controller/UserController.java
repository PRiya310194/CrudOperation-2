package com.example.Crud.operation.and.validation.controller;

import com.example.Crud.operation.and.validation.model.User;
import com.example.Crud.operation.and.validation.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
//@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("getUser")
    public List<User> getuser(@Nullable @RequestParam Integer userId){

        return userService.getUser(userId);
    }


    @PutMapping("updateuser/id/{id}")
    public void updateUser(@PathVariable Integer userId, @RequestBody User newuser){
        userService.updateUser(userId,newuser);
    }

    @DeleteMapping("deleteuser/id/{id}")
    public void deleteuser(@PathVariable Integer id){
        userService.deleteuser(id);
    }

    @PostMapping("/saveuser")
    public ResponseEntity  saveuser(@RequestBody  String Userrequest) {
        JSONObject Userdetails = new JSONObject(Userrequest);

        List<String> errorlist = validation(Userdetails);
        if (errorlist.isEmpty()) {
            User newuser = setuser(Userdetails);
            userService.saveuser(newuser);
            return new ResponseEntity("user saved", HttpStatus.CREATED);
        } else {
            String[] arr = Arrays.copyOf(
                    errorlist.toArray(), errorlist.size(), String[].class);

            return new ResponseEntity<>(Arrays.toString(arr),HttpStatus.BAD_REQUEST);
        }
    }

    public List<String> validation(JSONObject json){
        List<String>  errorlist=new ArrayList<>();
        if(!json.has("username")){
            errorlist.add("username required");
        }
        if(!json.has("dateOfBirth")){
            errorlist.add("Birth date is required");
        }
        if(!json.has("email")){
            errorlist.add("email is required");
        }
        if(!json.has("phone_number") ){
            errorlist.add("Phone number is Required");

        }
        return errorlist;
    }


    public User setuser(JSONObject json){
        User user=new User();


        if(json.has("userId")) {
            String userId= json.getString("userId");
            user.setUserId(Integer.valueOf(userId));
        }

        user.setUserName(json.getString("username"));
        user.setDateOfBirth(json.getString("dateOfBirth"));
        user.setEmail(json.getString("email"));
        user.setPhoneNumber(json.getString("phoneNumber"));
        if(json.has("date")){
            String date= json.getString("date");
            user.setDate(date);
        }
        if(json.has("time")){
            String time= json.getString("time");
            user.setTime("time");
        }

        return user;
    }
}