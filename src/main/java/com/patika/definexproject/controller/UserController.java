package com.patika.definexproject.controller;


import javax.validation.Valid;


import com.patika.definexproject.model.User;
import com.patika.definexproject.service.UserServices;
import com.patika.definexproject.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/")
public class UserController {


    @Autowired
    UserServices userServices;


    @PostMapping("users")
    public GenericResponse createUser(@Valid @RequestBody User user) {
        userServices.save(user);
        return new GenericResponse("user created");
    }





}
