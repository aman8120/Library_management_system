package com.example.lib_mng_sys.service;


import com.example.lib_mng_sys.dto.request.BookRequest;
import com.example.lib_mng_sys.dto.response.BookResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookSevice {

    BookResponse createBook(BookRequest request, MultipartFile image);

    BookResponse updateBook(Long id, BookRequest request, MultipartFile image);

    void deleteBook(Long id);

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    List<BookResponse> searchBooks(String title, String author, String publisher, String category);

    void lendBook(Long bookId, Long userId);
}
