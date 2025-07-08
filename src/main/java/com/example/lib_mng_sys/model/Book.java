package com.example.lib_mng_sys.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;


    @Column(name = "is_available")
    private boolean available;

    @Column(name = "image")
    private String image;

    @ManyToOne
    private NormalUser user;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Publisher publisher;


}
