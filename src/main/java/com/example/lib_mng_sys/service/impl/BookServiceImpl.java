package com.example.lib_mng_sys.service.impl;

import com.example.lib_mng_sys.dto.request.BookRequest;
import com.example.lib_mng_sys.dto.response.BookResponse;
import com.example.lib_mng_sys.enums.UserRole;
import com.example.lib_mng_sys.exception.UserNotFoundException;
import com.example.lib_mng_sys.mapper.BookMapper;
import com.example.lib_mng_sys.model.*;
import com.example.lib_mng_sys.repository.AuthorRepository;
import com.example.lib_mng_sys.repository.BookRepository;
import com.example.lib_mng_sys.repository.PublisherRepository;
import com.example.lib_mng_sys.repository.UserRepositoy;
import com.example.lib_mng_sys.service.BookSevice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl  implements BookSevice {

    private final BookRepository bookRepository;
    private final UserRepositoy userRepositoy;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final BookMapper bookMapper;

    @Override
    public void lendBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is already lent to another user");
        }

        User user = userRepositoy.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        if (!user.getRole().equals(UserRole.NORMAL_USER)) {
            throw new RuntimeException("Only Normal Users can lend books");
        }

        book.setUser((NormalUser) user);
        book.setAvailable(false);

        bookRepository.save(book);
    }

    @Override
    public BookResponse createBook(BookRequest request, MultipartFile image) {
        Author author = authorRepository.findByName(request.getAuthorName())
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(request.getAuthorName());
                    return authorRepository.save(newAuthor);
                });

        Publisher publisher = publisherRepository.findByName(request.getPublisherName())
                .orElseGet(() -> {
                    Publisher newPublisher = new Publisher();
                    newPublisher.setName(request.getPublisherName());
                    return publisherRepository.save(newPublisher);
                });

        Book book = new Book();
        bookMapper.mapToNewBook(request, book);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setAvailable(true);

        if (image != null && !image.isEmpty()) {
            String imagePath = image.getOriginalFilename();
            book.setImage(imagePath); // Save actual path if file stored
        }

        Book savedBook = bookRepository.save(book);
        return bookMapper.mapToBookResponse(savedBook);
    }


    @Override
    public BookResponse updateBook(Long id, BookRequest request, MultipartFile image) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Author author = authorRepository.findByName(request.getAuthorName())
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(request.getAuthorName());
                    return authorRepository.save(newAuthor);
                });

        Publisher publisher = publisherRepository.findByName(request.getPublisherName())
                .orElseGet(() -> {
                    Publisher newPublisher = new Publisher();
                    newPublisher.setName(request.getPublisherName());
                    return publisherRepository.save(newPublisher);
                });

        book.setTitle(request.getTitle());
        book.setCategory(request.getCategory());
        book.setAuthor(author);
        book.setPublisher(publisher);

        if (image != null && !image.isEmpty()) {
            book.setImage(image.getOriginalFilename());
        }

        Book updated = bookRepository.save(book);
        return bookMapper.mapToBookResponse(updated);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookMapper.mapToBookResponseList(bookRepository.findAll());
    }

    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.mapToBookResponse(book);
    }

    @Override
    public List<BookResponse> searchBooks(String title, String author, String publisher, String category) {
        if (title != null) return bookMapper.mapToBookResponseList(bookRepository.findByTitleContainingIgnoreCase(title));
        if (author != null) return bookMapper.mapToBookResponseList(bookRepository.findByAuthor_NameContainingIgnoreCase(author));
        if (publisher != null) return bookMapper.mapToBookResponseList(bookRepository.findByPublisher_NameContainingIgnoreCase(publisher));
        if (category != null) return bookMapper.mapToBookResponseList(bookRepository.findByCategoryContainingIgnoreCase(category));
        throw new RuntimeException("Please provide a valid search parameter");
    }

}
