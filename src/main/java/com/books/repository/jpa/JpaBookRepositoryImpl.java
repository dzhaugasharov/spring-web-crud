package com.books.repository.jpa;

import com.books.model.Book;
import com.books.repository.BookRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
//@Transactional
@EnableTransactionManagement
public class JpaBookRepositoryImpl implements BookRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Book get(int id) {
        return em.find(Book.class, id);
    }

    @Override
    @Transactional
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
    @Transactional
    public boolean delete(Book book) {
        em.remove(em.contains(book) ? book : em.merge(book));
        return true;
    }
}
