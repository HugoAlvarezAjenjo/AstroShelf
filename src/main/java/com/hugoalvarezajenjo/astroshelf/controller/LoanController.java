package com.hugoalvarezajenjo.astroshelf.controller;

import com.hugoalvarezajenjo.astroshelf.model.Book;
import com.hugoalvarezajenjo.astroshelf.model.Loan;
import com.hugoalvarezajenjo.astroshelf.model.User;
import com.hugoalvarezajenjo.astroshelf.service.BookService;
import com.hugoalvarezajenjo.astroshelf.service.LoanService;
import com.hugoalvarezajenjo.astroshelf.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;
    private final BookService bookService;
    private final UserService userService;

    @GetMapping
    public String listActiveLoans(Model model) {
        List<Loan> loans = loanService.getAllActiveLoans();
        model.addAttribute("loans", loans);
        return "loans/list";
    }

    @GetMapping("/new")
    public String showLoanForm(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("loan", new Loan());
        return "loans/form";
    }

    @PostMapping("/new")
    public String createLoan(@RequestParam Long userId, @RequestParam Long bookId) {
        User user = userService.getUserById(userId).orElseThrow();
        Book book = bookService.getBookById(bookId).orElseThrow();
        loanService.createLoan(user, book);
        return "redirect:/loans";
    }

    @PostMapping("/return/{id}")
    public String returnLoan(@PathVariable Long id) {
        loanService.returnBook(id);
        return "redirect:/loans";
    }
}
