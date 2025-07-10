package com.example.lib_mng_sys.controller;

import com.example.lib_mng_sys.dto.request.BookRequest;
import com.example.lib_mng_sys.dto.response.BookResponse;
import com.example.lib_mng_sys.service.BookSevice;
import com.example.lib_mng_sys.util.ResponseBuilder;
import com.example.lib_mng_sys.util.ResponseStructure;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookSevice bookSevice;


    //    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseStructure<BookResponse>> createBook(
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        BookRequest request;
        try {
            ObjectMapper mapper = new ObjectMapper();
            request = mapper.readValue(data, BookRequest.class);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON for BookRequest", e);
        }

        BookResponse bookResponse = bookSevice.createBook(request, image);
        return ResponseBuilder.created("Book Created", bookResponse);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<BookResponse>> updateBook(
            @PathVariable Long id,
            @RequestPart("data") String data,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        BookRequest request;
        try {
            ObjectMapper mapper = new ObjectMapper();
            request = mapper.readValue(data, BookRequest.class);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON for BookRequest", e);
        }

        BookResponse bookResponse = bookSevice.updateBook(id, request, image);
        return ResponseBuilder.ok("Book Updated", bookResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Long id) {
        bookSevice.deleteBook(id);
        return ResponseBuilder.ok("Book Deleted", "Book ID " + id + " deleted");
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<BookResponse>>> getAllBooks() {
        List<BookResponse> books = bookSevice.getAllBooks();
        return ResponseBuilder.ok("All Books Retrieved", books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<BookResponse>> getBookById(@PathVariable Long id) {
        BookResponse book = bookSevice.getBookById(id);
        return ResponseBuilder.ok("Book Found", book);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseStructure<List<BookResponse>>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String category) {
        List<BookResponse> books = bookSevice.searchBooks(title, author, publisher, category);
        return ResponseBuilder.ok("Search Results", books);
    }


    @PostMapping("/lend")
    public ResponseEntity<ResponseStructure<String>> lendBook(
            @RequestParam Long bookId,
            @RequestParam Long userId) {
        bookSevice.lendBook(bookId, userId);
        return ResponseBuilder.ok("Book lent successfully", "Book ID " + bookId + " assigned to User ID " + userId);
    }


    @PostMapping("/return")
    public ResponseEntity<ResponseStructure<String>> returnBook(
            @RequestParam Long bookId,
            @RequestParam Long userId) {

        bookSevice.returnBook(bookId, userId);
        return ResponseBuilder.ok("Book returned successfully", "Book ID " + bookId + " returned by User ID " + userId);
    }


}
