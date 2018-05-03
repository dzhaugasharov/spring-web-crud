package com.books.repository.jpa;

import com.books.model.Book;
import com.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaBookRepositoryImpl implements BookRepository {

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager em;

    @Override
    public Book get(int id) {
        return em.find(Book.class, id);
    }

    @Override
    public Book save(Book book) {
        if (book.isNew())
        {
            em.persist(book);
        }
        else {
            book = em.merge(book);
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        return em.createNamedQuery(Book.GET_ALL).getResultList();
    }

    @Override
    public boolean delete(Book book) {
        em.remove(book);
        return true;
    }
}
