package com.example.lib_mng_sys.service.impl;

import com.example.lib_mng_sys.dto.response.DashboardSummaryResponse;
import com.example.lib_mng_sys.model.Book;
import com.example.lib_mng_sys.repository.AuthorRepository;
import com.example.lib_mng_sys.repository.BookRepository;
import com.example.lib_mng_sys.repository.PublisherRepository;
import com.example.lib_mng_sys.repository.UserRepositoy;
import com.example.lib_mng_sys.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepositoy userRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public DashboardSummaryResponse getDashboardSummary() {
        int userCount = (int) userRepository.count();
        int bookCount = (int) bookRepository.count();
        int authorCount = (int) authorRepository.count();
        int publisherCount = (int) publisherRepository.count();

        List<Book> allBooks = bookRepository.findAll();

        long lendCount = allBooks.stream()
                .filter(book -> Boolean.FALSE.equals(book.isAvailable()) && Boolean.FALSE.equals(book.isReturned()))
                .count();
        // ✅ Count books that have been returned
        long returnCount = allBooks.stream()
                .filter(book -> Boolean.TRUE.equals(book.isReturned()))
                .count();

        List<DashboardSummaryResponse.MonthlyStat> monthlyStats = new ArrayList<>();
        for (Month month : Month.values()) {
            long lend = allBooks.stream()
                    .filter(book -> book.getIssueDate() != null && book.getIssueDate().getMonth() == month)
                    .count();

            long returned = allBooks.stream()
                    .filter(book -> book.getReturnDate() != null && book.getReturnDate().getMonth() == month)
                    .count();

            monthlyStats.add(DashboardSummaryResponse.MonthlyStat.builder()
                    .month(month.name().substring(0, 3)) // "JAN", "FEB", etc.
                    .lend(lend)
                    .returnCount(returned)
                    .build());
        }
        return DashboardSummaryResponse.builder()
                .users(userCount)
                .books(bookCount)
                .authors(authorCount)
                .publishers(publisherCount)
                .lendCount(lendCount)
                .returnCount(returnCount)
                .monthlyStats(monthlyStats)
                .build();
    }
}

