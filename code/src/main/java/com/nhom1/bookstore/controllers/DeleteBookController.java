package com.nhom1.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nhom1.bookstore.services.BookService;

@Controller
public class DeleteBookController {
    private final BookService bookService;

    public DeleteBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/quantri/sanpham/xoa/{id}")
    public String viewEditAccount(@PathVariable("id") String id) {
        bookService.deleteBook(id);
        return "redirect:/quantri/sanpham"; 
    }
}
