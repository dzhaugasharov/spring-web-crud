package com.books.repository.datajpa;

import com.books.model.Book;
import com.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private ProxyBookRepository proxy;

    public Book get(int id) {
        return proxy.getOne(id);
    }

    public Book save(Book book) {
        return proxy.save(book);
    }

    public List<Book> getAll() {
        return proxy.findAll();
    }

    public boolean delete(Book book) {
        proxy.delete(book);
        return true;
    }
}
