package com.example.lib_mng_sys.repository;

import com.example.lib_mng_sys.model.Author;
import com.example.lib_mng_sys.model.Book;
import com.example.lib_mng_sys.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    boolean existsByTitleAndAuthorAndPublisher(String title, Author author, Publisher publisher);

    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthor_NameContainingIgnoreCase(String author);
    List<Book> findByPublisher_NameContainingIgnoreCase(String publisher);
    List<Book> findByCategoryContainingIgnoreCase(String category);
}
