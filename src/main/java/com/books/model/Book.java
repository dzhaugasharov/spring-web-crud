package com.books.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "{book.title.empty}")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "{book.author.empty}")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "isbn")
    @NotEmpty(message = "{book.isbn.empty}")
    private String isbn;

    @Column(name = "printYear")
    @NotEmpty(message = "{book.printYear.empty}")
    private int printYear;

    @Column(name = "readAlready")
    private boolean readAlready;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPrintYear() {
        return printYear;
    }

    public void setPrintYear(int printYear) {
        this.printYear = printYear;
    }

    public boolean isReadAlready() {
        return readAlready;
    }

    public void setReadAlready(boolean readAlready) {
        this.readAlready = readAlready;
    }
}
