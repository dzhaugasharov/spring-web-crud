package com.books.web;

import com.books.model.Book;
import com.books.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.transaction.Transactional;
import java.io.StringReader;
import java.time.LocalDateTime;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml", "classpath:spring/spring-mvc.xml"})
@RunWith(SpringRunner.class)
@Transactional //For testing, data not changes
public class BookRestControllerTest extends AbstractControllerTest {

    public static final String REST_URL = "/api/book/";

    @Autowired
    BookService bookService;

    private Book book4delete;
    private Book book4update;
    private ObjectMapper objectMapper;

    @Override
    @Before
    public void setUp() {
        book4delete = createBook();
        book4update = createBook();

        //Save to DB
        bookService.save(book4delete);
        bookService.save(book4update);

        objectMapper = new ObjectMapper();
    }

    private Book createBook() {
        Book book = new Book();
        book.setPrintYear(2018);
        book.setIsbn("TEST_ISBN");
        book.setAuthor("Darkhan");
        book.setTitle("TES BOOK TITLE");
        book.setDescription("TEST DESCTIPTION");

        return book;
    }

    @Test
    public void books() throws Exception {
        mockMvc.perform(get(REST_URL + "all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
//                //.andExpect();
    }

    @Test
    public void book() throws Exception {
        mockMvc.perform(get(REST_URL + book4update.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void testCreate() throws Exception {
        Book book = new Book();
        book.setPrintYear(2018);
        book.setIsbn("NEW_ISBN");
        book.setAuthor("Darkhan");
        book.setTitle("NEW CREATED BOOK");
        book.setDescription("NEW TEST DESCRIPTION");


        String json = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            ).andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String desc = "Updated at: " + now;
        book4update.setDescription(desc);

        mockMvc.perform(
                    put(REST_URL + book4update.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(book4update))
                )
                .andDo(print())
                .andExpect(status().isOk());
        Book updatedBook = bookService.get(book4update.getId());
        Assert.assertEquals("Description updated: ", desc, updatedBook.getDescription());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + book4delete.getId()))
            .andDo(print())
            .andExpect(status().isOk());
    }
}