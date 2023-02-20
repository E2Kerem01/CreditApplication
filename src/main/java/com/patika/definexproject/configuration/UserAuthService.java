package com.patika.definexproject.configuration;

import com.patika.definexproject.model.User;
import com.patika.definexproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User inBD = userRepository.findByUsername(username);
        if (inBD == null){
            throw new UsernameNotFoundException("User not found...");
        }
        return inBD;
    }
}
