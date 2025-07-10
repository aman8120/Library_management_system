package com.example.lib_mng_sys.repository;

import com.example.lib_mng_sys.model.Author;
import com.example.lib_mng_sys.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    public Optional<Publisher> findByName(String name);
}
