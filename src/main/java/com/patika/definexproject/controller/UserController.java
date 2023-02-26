package com.patika.definexproject.controller;


import javax.validation.Valid;
import com.patika.definexproject.model.User;
import com.patika.definexproject.repository.UserRepository;
import com.patika.definexproject.service.UserServices;
import com.patika.definexproject.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/")
public class UserController {


    @Autowired
    UserServices userServices;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("users")
    public GenericResponse createUser(@Valid @RequestBody User user) {
        userServices.save(user);
        return new GenericResponse("Kullanıcı kaydı başarıyla tamamlandı.");
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Kullanıcı Silindi.")
    public void deleteUser(@PathVariable Long id){
        userServices.deleteUser(id);

    }



    @PutMapping("update/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "İlgili user'ın bilgileri güncellendi.")
    public User updateUser(@PathVariable Long id, String email, String username){
        return userServices.updateUser(id,email,username);

    }

    @GetMapping("allusers")
    public Page<User> listOfCustomer(Pageable pageable){
        return userServices.getPagesOfUsers(pageable);

    }







}
