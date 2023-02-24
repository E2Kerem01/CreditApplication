package com.patika.definexproject.service;


import com.patika.definexproject.model.Credit;
import com.patika.definexproject.repository.CreditRepository;
import com.patika.definexproject.model.User;
import com.patika.definexproject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServices {


    private final UserRepository userRepository;

    PasswordEncoder passwordEncoder;
    private final CreditRepository creditRepository;


    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder,
                        CreditRepository creditRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.creditRepository = creditRepository;
    }

    public void save(User user) {                                                                                           // Yeni Müşteri Ekleme
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteUser(Long id) {                                                                                      //  Müşteri Silme
        Credit credit = creditRepository.findByUser_Id(id);
        if(credit==null){
            userRepository.deleteById(id);
        }else {
            creditRepository.deleteById(credit.getCreditId());
            userRepository.deleteById(id);
        }

    }

    public User updateUser(Long id, String email, String username) {                                                       // Müşteri Güncelleme
        User user = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus
                .NOT_FOUND,"User not found"));
        user.setEmail(email);
        user.setUsername(username);
        return userRepository.save(user);
    }

    public Page<User> getPagesOfUsers(Pageable pageable) {                                                                  // Müşterileri Listeleme
        return userRepository.findAll(pageable);
    }
}
