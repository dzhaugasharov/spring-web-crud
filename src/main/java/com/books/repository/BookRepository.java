package com.books.repository;


import com.books.model.Book;

import java.util.List;

public interface BookRepository {

    public Book get(int id);

    public Book save(Book book);

    public List getAll();

    public boolean delete(Book book);
}
