package com.books.repository;


import com.books.model.Book;

import java.util.List;

public interface BookRepository {

    Book get(int id);

    Book save(Book book);

    List getAll();

    boolean delete(Book book);
}
