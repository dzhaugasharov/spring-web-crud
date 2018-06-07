package com.books.web;

import com.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookRestController {

    @Autowired
    BookService bookService;


}
