package com.books.controller;

import com.books.model.Book;
import com.books.model.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BookController {

    @RequestMapping("/")
    public ModelAndView books(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return new ModelAndView("pages/index");
    }

    @RequestMapping(value = "/create")
    public ModelAndView create(Model model,
        @RequestParam(value="title", required=false, defaultValue = "") String title,
        @RequestParam(value="author", required=false, defaultValue = "") String author,
        @RequestParam(value="isbn", required=false, defaultValue = "") String isbn,
        @RequestParam(value="printYear", required=false, defaultValue = "") String year,
        @RequestParam(value="description", required=false, defaultValue = "") String description,
        @RequestParam(value="submit", required=false) String submit
    ) {
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("isbn", isbn);
        model.addAttribute("year", year);
        model.addAttribute("description", description);
        if (submit != null && !submit.isEmpty())
        {
            save(null,title,author,isbn,year,description,model);
        }
        return new ModelAndView("pages/form");
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Model model,
         @RequestParam(value="id") Integer id,
         @RequestParam(value="title", required=false, defaultValue = "") String title,
         @RequestParam(value="author", required=false, defaultValue = "") String author,
         @RequestParam(value="isbn", required=false, defaultValue = "") String isbn,
         @RequestParam(value="printYear", required=false, defaultValue = "") String year,
         @RequestParam(value="description", required=false, defaultValue = "") String description,
         @RequestParam(value="submit", required=false) String submit
    ) {
        //Сохранение
        if (submit != null && !submit.isEmpty())
        {
            save(id,title,author,isbn,year,description,model);
        }
        return new ModelAndView("pages/form");
    }

    public void save(Integer id, String title, String author, String isbn,
                             String year, String description, Model model)
    {
        BookService bookService = new BookService();
        Book book = new Book();
        if (id != null) {
            book = bookService.get(id);
            //Если нет 404
        }

        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setDescription(description);
        book.setPrintYear(Integer.parseInt(year));
        if (id != null) bookService.edit(book);
        else bookService.add(book);

        model.addAttribute("id", id);
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("isbn", isbn);
        model.addAttribute("description", description);
        model.addAttribute("year", year);

        //Validation errors
        Map<String, String> errors = new HashMap<>();
        model.addAttribute("errors", errors);
    }
}
