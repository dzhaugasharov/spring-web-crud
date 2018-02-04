package com.books.controller;

import com.books.model.Book;
import com.books.model.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @RequestMapping("/")
    public ModelAndView books(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);

        BookService bookService = new BookService();
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);

        return new ModelAndView("pages/index");
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(Model model,
        @RequestParam(value="title", required=false, defaultValue = "") String title,
        @RequestParam(value="author", required=false, defaultValue = "") String author,
        @RequestParam(value="isbn", required=false, defaultValue = "") String isbn,
        @RequestParam(value="printYear", required=false, defaultValue = "") String printYear,
        @RequestParam(value="description", required=false, defaultValue = "") String description,
        @RequestParam(value="submit", required=false) String submit
    ) {
        Map<String, String> errors = new HashMap<>();
        model.addAttribute("errors", "YESS");
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("isbn", isbn);
        model.addAttribute("printYear", printYear);
        model.addAttribute("description", description);
        if (submit != null && !submit.isEmpty())
        {
            save(null,title,author,isbn,printYear,description,model);
        }
        return new ModelAndView("pages/form");
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Model model,
         @RequestParam(value="id") Integer id,
         @RequestParam(value="title", required=false, defaultValue = "") String title,
         @RequestParam(value="author", required=false, defaultValue = "") String author,
         @RequestParam(value="isbn", required=false, defaultValue = "") String isbn,
         @RequestParam(value="printYear", required=false, defaultValue = "") String printYear,
         @RequestParam(value="description", required=false, defaultValue = "") String description,
         @RequestParam(value="submit", required=false) String submit
    ) {
        model.addAttribute("errors", new HashMap<String, String>());
        //Сохранение
        if (submit != null && !submit.isEmpty())
        {
            save(id,title,author,isbn,printYear,description,model);
        }

        //Получение книги
        BookService bookService = new BookService();
        //Book book = bookService.get(id);

        model.addAttribute("id", id);
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("isbn", isbn);
        model.addAttribute("printYear", printYear);
        model.addAttribute("description", description);

        return new ModelAndView("pages/form");
    }

    public void save(Integer id, String title, String author, String isbn,
                             String year, String description, Model model)
    {
        BookService bookService = new BookService();
        Book book = new Book();
        if (id != null) {
            book = (Book) bookService.get(id);
            //Если нет 404
        }

        //Validate
        Map<String, String> errors = new HashMap<>();
        if (title == "") errors.put("titleError", "Поле Название не может быть пустым!");
        if (author == "") errors.put("authorError", "Поле Автор не может быть пустым!");
        if (year == "") errors.put("printYearError", "Поле Год печати не может быть пустым!");
        for (Map.Entry<String, String> item : errors.entrySet()) model.addAttribute(item.getKey(), item.getValue());
        if (errors.size() > 0) return;
        if (1 == 1) return ;

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
        model.addAttribute("printYear", year);
    }

    @RequestMapping(value = "/removebook", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String remove(Model model, @RequestParam(value="id") Integer id)
    {
        BookService bookService = new BookService();
        bookService.delete(id);
        return "{\"success\": true}";
    }
}
