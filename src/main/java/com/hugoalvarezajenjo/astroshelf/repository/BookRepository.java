package com.hugoalvarezajenjo.astroshelf.repository;

import com.hugoalvarezajenjo.astroshelf.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
