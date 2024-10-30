package com.hugoalvarezajenjo.astroshelf.controller;

import com.hugoalvarezajenjo.astroshelf.model.Book;
import com.hugoalvarezajenjo.astroshelf.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/public/books")
public class PublicController {
    private final BookService bookService;

    @GetMapping
    public String listBooks(@RequestParam(value = "query", required = false) final String query, final Model model) {
        final List<Book> books = this.bookService.searchBooks(query);
        model.addAttribute("books", books);
        model.addAttribute("query", query);
        return "public/books/list";
    }

    @GetMapping("/{id}")
    public String viewBookDetails(@PathVariable final Long id, final Model model) {
        final Book book = this.bookService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));
        model.addAttribute("book", book);
        return "public/books/details";
    }

}
