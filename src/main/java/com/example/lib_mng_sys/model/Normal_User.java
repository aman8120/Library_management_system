package com.example.lib_mng_sys.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="normal_user")
@EntityListeners(AuditingEntityListener.class)
public class Normal_User extends User {

    @CreatedBy
    @Column(name = "createdBy")
    private String createdBy;
}
