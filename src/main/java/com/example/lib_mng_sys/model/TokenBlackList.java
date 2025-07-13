package com.example.lib_mng_sys.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "token_black_list")
public class TokenBlackList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "black_list_id")
    private String blackListId;

    @Column(name = "token")
    private String token;

    @Column(name = "expiration")
    private long expiration;

}
