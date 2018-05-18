package com.books.service;

import com.books.model.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    public List getAll();

}
