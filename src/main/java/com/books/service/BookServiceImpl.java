package com.books.service;

import com.books.model.Book;
import com.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book get(int id) {
        return bookRepository.get(id);
    }

    @Override
    public boolean delete(Book book) {
        return bookRepository.delete(book);
    }
}
