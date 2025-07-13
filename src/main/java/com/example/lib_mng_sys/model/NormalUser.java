package com.example.lib_mng_sys.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "normal_user")
@EntityListeners(AuditingEntityListener.class)
public class NormalUser extends User {

    @CreatedBy
    @Column(name = "createdBy")
    private String createdBy;

    @OneToMany(mappedBy = "user")
    private List<Book> books;
}
