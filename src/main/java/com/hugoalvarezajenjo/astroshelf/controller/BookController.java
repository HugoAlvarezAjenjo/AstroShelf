package com.hugoalvarezajenjo.astroshelf.controller;

import com.hugoalvarezajenjo.astroshelf.model.Book;
import com.hugoalvarezajenjo.astroshelf.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public String getBooks(final Model model) {
        model.addAttribute("books", this.bookService.getAllBooks());
        return "books/list";
    }

    @GetMapping("/new")
    public String addBookForm(final Model model) {
        model.addAttribute("book", new Book());
        return "books/form";
    }

    @PostMapping("/new")
    public String saveBook(@ModelAttribute final Book book, final Model model) {
        this.bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable final Long id, final Model model) {
        Optional<Book> book = this.bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "books/form";
        } else {
            return "books/list";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute final Book book) {
        book.setId(id);
        this.bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable final Long id) {
        this.bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/view/{id}")
    public String viewBook(@PathVariable final Long id, final Model model) {
        Optional<Book> book = this.bookService.getBookById(id);
        if (book.isPresent()) {
            System.out.printf("Libro" + book.get());
            model.addAttribute("book", book.get());
            return "books/view";
        } else {
            return "books/list";
        }
    }
}
