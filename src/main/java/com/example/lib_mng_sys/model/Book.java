package com.example.lib_mng_sys.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name ="return_date")
    private LocalDate returnDate;

    @Column(name = "returned")
    private  boolean returned;

    @Column(name = "is_available")
    private boolean available;

    @Column(name = "image")
    private String image;

    @CreatedBy
    @Column(name = "createdBy")
    private String createdBy;

    @ManyToOne
    private NormalUser user;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Publisher publisher;


}
