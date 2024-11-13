package com.hugoalvarezajenjo.astroshelf.model;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.tomcat.jni.Library;

import java.time.LocalDate;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "librarian_id", nullable = false)
    private User librarian;

    @Column(nullable = false)
    private LocalDate loanDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    @Column(nullable = false)
    private Boolean returned;
}
