package com.hugoalvarezajenjo.astroshelf.repository;

import com.hugoalvarezajenjo.astroshelf.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId);
    List<Loan> findByBookId(Long bookId);
    List<Loan> findByReturnedFalse();
    @Query("SELECT COUNT(l) FROM Loan l WHERE l.user.id = :userId AND l.returned = false")
    int countActiveLoansByUserId(Long userId);
}
