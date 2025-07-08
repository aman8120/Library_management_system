package com.example.lib_mng_sys.repository;

import com.example.lib_mng_sys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoy extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
