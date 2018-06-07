package com.books.service;

import com.books.model.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    List getAll();

    Book get(int id);

    boolean delete(Book book);
}
