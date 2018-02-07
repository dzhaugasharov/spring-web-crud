package com.books.model;

import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {

    public final static int LIMIT = 10;
    private int total = 0;

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    /**
     * Retrieves all persons
     *
     * @return a list of persons
     */
    public List<Book> getBooks(int page, String title, String readAlready) {
        int start = 0;
        if (page > 1) start = page * LIMIT - LIMIT;

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // Create a Hibernate query (HQL)
        String q = "FROM Book WHERE id > 0";
        Map<String, String> filters = new HashMap<>();
        if (!title.isEmpty())
        {
            q += " AND title LIKE :title";
            filters.put("title", "%"+title+"%");
        }
        if (!readAlready.isEmpty())
        {
            q += " AND readAlready = :readAlready";
            filters.put("readAlready", readAlready);
        }
        Query query = session.createQuery(q);
        Query totalQuery = session.createQuery("select count(*) "+q);
        //Setting Parameters
        for (Map.Entry<String, String> filter : filters.entrySet())
        {
            query.setString( filter.getKey(), filter.getValue() );
            totalQuery.setString( filter.getKey(), filter.getValue() );
        }

        //Paginating
        query.setFirstResult(start);
        query.setMaxResults(LIMIT);
        //Total
        total = ((Long)totalQuery.uniqueResult()).intValue();

        //List
        List<Book> books = (List<Book>) query.list();

        return  books;
    }

    public List<Book> getBooks(int page) {
        return getBooks(page, "", "");
    }

    /* *
    * Returns total Rows of previous query
    */
    public int getTotal() {
        return total;
    }

    public int getPagesCount() {
        return (int) Math.ceil(total / (double)LIMIT);
    }

    /**
     * Retrieves a single book
     */
    public com.books.model.Book get( Integer id ) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Book WHERE id = :id");
        query.setInteger("id", id);
        List<com.books.model.Book> books = (List<Book>) query.list();
        com.books.model.Book book = new com.books.model.Book();
        book.setId(books.get(0).getId());
        book.setTitle(books.get(0).getTitle());
        return book;

        // Retrieve existing Book first
        //Book book = (Book)session.get(Book.class, id);
        //return book;
    }

    /**
     * Adds a new book
     */
    public void add(Book book) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // Save
        //session.save(book);

        Query query = session.createSQLQuery("INSERT INTO Books (title, author, description, isbn,printYear) VALUES (:title,:author,:description,:isbn,:printYear)");
        query.setParameter("title", book.getTitle());
        query.setParameter("author", book.getAuthor());
        query.setParameter("description", book.getDescription());
        query.setParameter("isbn", book.getIsbn());
        query.setParameter("printYear", book.getPrintYear());
        query.executeUpdate();

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
