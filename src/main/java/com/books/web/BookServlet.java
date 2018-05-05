package com.books.web;

import com.books.model.Book;
import com.books.repository.BookRepository;
import com.books.service.BookService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class BookServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private BookRepository bookRepository;
    private BookService bookService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring-app.xml");
        bookRepository = springContext.getBean(BookRepository.class);
        bookService = springContext.getBean(BookService.class);
    }

    @Override
    public void destroy() {
        springContext.close();
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
                int id = getId(request);
                Book book = "create".equals(action) ? new Book() : bookRepository.get(id);
                if (book == null) {
                    //404
                }
                request.setAttribute("book", book);
                request.getRequestDispatcher("/form.jsp").forward(request, response);
                break;

        case "delete":
            Book delBook = bookRepository.get(getId(request));
            bookRepository.delete(delBook);
            response.sendRedirect("/");
            break;

            default:
                request.setAttribute("books", bookRepository.getAll());
                request.getRequestDispatcher("/books.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        response.sendRedirect("/");
    }
}
