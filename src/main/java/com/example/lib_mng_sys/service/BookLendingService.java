package com.example.lib_mng_sys.service;

public interface BookLendingService {
    public void lendBook(Long bookId, Long userId);

    public void returnBook(Long bookId, Long userId);

//    public List<BookLendingResponse> getActiveLendingsByUser(Long userId);
//
//    public List<BookLendingResponse> getAllLendingHistory(Long userId);
}
