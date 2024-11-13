package com.hugoalvarezajenjo.astroshelf.service;

import com.hugoalvarezajenjo.astroshelf.model.Book;
import com.hugoalvarezajenjo.astroshelf.model.Loan;
import com.hugoalvarezajenjo.astroshelf.model.User;
import com.hugoalvarezajenjo.astroshelf.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    @Override
    public List<Loan> getAllLoans() {
        return this.loanRepository.findAll();
    }

    @Override
    public List<Loan> getAllActiveLoans() {
        return this.loanRepository.findByReturnedFalse();
    }

    @Override
    public List<Loan> getLoansByBookId(final Long bookId) {
        return this.loanRepository.findByBookId(bookId);
    }

    @Override
    public List<Loan> getLoansByUserId(final Long userId) {
        return this.loanRepository.findByUserId(userId);
    }

    @Override
    public Optional<Loan> getLoanById(final Long id) {
        return this.loanRepository.findById(id);
    }

    @Override
    public Loan saveLoan(final Loan loan) {
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(loan.getLoanDate().plusWeeks(2));
        loan.setReturned(false);
        return this.loanRepository.save(loan);
    }

    @Override
    public Boolean hasActiveLoans(final Long userId) {
        return this.loanRepository.countActiveLoansByUserId(userId) > 0;
    }

    @Override
    public void returnBook(final Long loanId) {
        final Optional<Loan> bookLoan = this.loanRepository.findById(loanId);
        bookLoan.ifPresent(loan -> {
            loan.setReturned(true);
            this.loanRepository.save(loan);
        });
    }

    @Override
    public Loan createLoan(final User user, final Book book) {
        final Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(LocalDate.now().plusDays(7));
        loan.setReturned(false);
        return this.saveLoan(loan);
    }
}
