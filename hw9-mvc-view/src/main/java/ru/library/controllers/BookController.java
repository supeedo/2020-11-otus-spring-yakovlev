package ru.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.library.models.Book;
import ru.library.services.BookServiceImpl;

import java.util.List;

@Controller
public class BookController {

    BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "library";
    }

    @PostMapping("/edit")
    public String updateBook(@RequestParam("id") Long id,
                             Model model) {
        return null;
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id,
                             Model model) {
        bookService.deleteBookById(id);
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "redirect:/";
    }
}
