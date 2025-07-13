package com.example.lib_mng_sys.security.util;


import com.example.lib_mng_sys.exception.IllegalActivityException;
import com.example.lib_mng_sys.model.User;
import com.example.lib_mng_sys.repository.UserRepositoy;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserIdentity {

    private final UserRepositoy userRepository;

    public Authentication getAuthentication(){

        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getCurrentUserEmail(){

        return this.getAuthentication().getName();
    }

    public User getCurrentUser(){
         return userRepository.findByEmail(this.getCurrentUserEmail())
                .orElseThrow(()-> new RuntimeException("Failed to fetch user"));
    }

    public void validateOwnerShip(String ownername){
        if(!this.getCurrentUserEmail().equals(ownername)){
            throw new IllegalActivityException("User not allowed to access or modify the resource requested");
        }

    }
}
