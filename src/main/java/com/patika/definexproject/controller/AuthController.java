package com.patika.definexproject.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.patika.definexproject.model.User;
import com.patika.definexproject.repository.UserRepository;
import com.patika.definexproject.shared.CurrentUser;
import com.patika.definexproject.shared.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/auth")
    @JsonView(Views.Base.class)
    ResponseEntity<?> handleAuthentication(@CurrentUser User user){
        return ResponseEntity.ok(user);

    }

}
