package com.books.model;

import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;

import javax.annotation.Resource;
import java.util.List;

public class BookService {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    /**
     * Retrieves all persons
     *
     * @return a list of persons
     */
    public List<Book> getAll() {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // Create a Hibernate query (HQL)
        List<Book> books = (List<Book>) session.createQuery("FROM Book").list();
        return  books;
    }

    /**
     * Retrieves a single book
     */
    public Book get( Integer id ) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        // Retrieve existing Book first
        Book book = (Book)session.get(Book.class, id);

        return book;
    }

    /**
     * Adds a new book
     */
    public void add(Book book) {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // Save
        session.save(book);
        transaction.commit();
    }

    /**
     * Deletes an existing book
     * @param id the id of the existing book
     */
    public void delete(Integer id) {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // Retrieve existing book first
        Object book = session.get(Book.class, id);

        // Delete
        session.delete(book);
        transaction.commit();
    }

    /**
     * Edits an existing book
     */
    public void edit(Book book) {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing book via id
        Book existingBook = (Book) session.get(Book.class, book.getId());

        // Assign updated values to this book
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(existingBook.getAuthor());
        existingBook.setIsbn(existingBook.getIsbn());

        // Save updates
        session.save(existingBook);
    }
}
