package com.hugoalvarezajenjo.astroshelf.service;

import com.hugoalvarezajenjo.astroshelf.model.Book;
import com.hugoalvarezajenjo.astroshelf.model.Loan;
import com.hugoalvarezajenjo.astroshelf.model.User;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    List<Loan> getAllLoans();

    List<Loan> getAllActiveLoans();

    List<Loan> getLoansByBookId(Long bookId);

    List<Loan> getLoansByUserId(Long userId);

    Optional<Loan> getLoanById(Long id);

    Loan saveLoan(Loan loan);

    Boolean hasActiveLoans(Long userId);

    void returnBook(Long loanId);

    Loan createLoan(User user, Book book);
}
