package com.books.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.List;

public class BookService {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Retrieves all persons
     *
     * @return a list of persons
     */
    public List<Book> getAll() {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM books");

        // Retrieve all
        return  query.list();
    }

    /**
     * Retrieves a single book
     */
    public Book get( Integer id ) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing Book first
        Book book = (Book) session.get(Book.class, id);

        return book;
    }
    /**
     * Adds a new book
     */
    public void add(Book book) {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Save
        session.save(book);
    }

    /**
     * Deletes an existing book
     * @param id the id of the existing book
     */
    public void delete(Integer id) {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing book first
        Book book = (Book) session.get(Book.class, id);

        // Delete
        session.delete(book);
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
