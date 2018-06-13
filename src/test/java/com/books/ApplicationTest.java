package com.books;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;


@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("setting up");
    }

    @Test
    public void myTest() {
        System.out.println("Yesso");
    }
}