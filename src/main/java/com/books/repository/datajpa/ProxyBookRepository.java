package com.books.repository.datajpa;

import com.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProxyBookRepository extends JpaRepository<Book, Integer> {

    Book save(Book book);

    @Override
    Book getOne(Integer id);
}
