package com.hugoalvarezajenjo.astroshelf.service;

import com.hugoalvarezajenjo.astroshelf.model.Book;
import com.hugoalvarezajenjo.astroshelf.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(final Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Book saveBook(final Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public void deleteBookById(final Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public List<Book> searchBooks(String query) {
        if (query == null || query.isBlank()) {
            return getAllBooks();
        }
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrIsbnContainingIgnoreCase(query, query, query);
    }
}
