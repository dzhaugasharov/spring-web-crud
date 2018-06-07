package com.books.web;

import com.books.model.Book;
import com.books.repository.BookRepository;
import com.books.service.BookService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class BookServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private BookRepository bookRepository;
    private BookService bookService;
    private WebApplicationContext wac;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //Spring web
        wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        if (wac != null) {
            bookRepository = wac.getBean(BookRepository.class);
            bookService = wac.getBean(BookService.class);
        }
        else {
            //Plain spring
            springContext = new ClassPathXmlApplicationContext("spring/spring-db.xml");
            bookRepository = springContext.getBean(BookRepository.class);
            bookService = springContext.getBean(BookService.class);
        }
    }

    @Override
    public void destroy() {
        if (springContext != null) springContext.close();
        super.destroy();
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create":
            case "update":
                Book book = "create".equals(action) ? new Book() : bookRepository.get(getId(request));
                if (book == null) {
                    //404
                    //response.setStatus(404);
                    response.sendError(404, "Page not found!");
                    break;
                }
                request.setAttribute("book", book);
                request.getRequestDispatcher("WEB-INF/jsp/form.jsp").forward(request, response);
                break;

            case "delete":
                Book delBook = bookRepository.get(getId(request));
                bookRepository.delete(delBook);
                response.sendRedirect("/books");
                break;

            default:
                request.setAttribute("books", bookRepository.getAll());
                request.getRequestDispatcher("WEB-INF/jsp/books.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        //String action = request.getParameter("action");
        Book book = new Book();
        book.setTitle(request.getParameter("title"));
        book.setAuthor(request.getParameter("author"));
        book.setDescription(request.getParameter("description"));
        book.setIsbn(request.getParameter("isbn"));
        book.setPrintYear(Integer.parseInt(request.getParameter("printYear")));
        //book.setReadAlready(Integer.parseInt(request.getParameter("printYear")));

        //New Record
        if (!request.getParameter("id").isEmpty()) {
            book.setId(Integer.parseInt(request.getParameter("id")));
        }
        bookService.save(book);
        response.sendRedirect("/books");
    }
}
