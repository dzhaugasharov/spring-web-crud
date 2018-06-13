package com.books.web;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest extends AbstractControllerTest {

    @Test
    public void books() throws Exception {
        mockMvc.perform(get("/books"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("books")) //view file Name
        .andExpect(forwardedUrl("WEB-INF/jsp/books.jsp"))
        .andExpect(model().attribute("books", hasSize(2)));
//        .andExpect(model().attribute("books", hasItem(
//                allOf(
//                        hasProperty("id", is(START_SEG)),
//                        hasProperty("title", is())
//                )
//        )));
    }
}