package com.books.web;

import com.books.model.Book;
import com.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletResponse response) {
        //Redirecting
        return "redirect:/books";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String books(Model model, @RequestParam(value = "action", required = false, defaultValue = "") String action,
                        @RequestParam(value = "id", required = false) Integer id/*,
                              @RequestParam(value="title", required=false, defaultValue="") String title,
                              @RequestParam(value="readAlready", required=false, defaultValue="") String readAlready,
                              @RequestParam(value="page", required=false, defaultValue="1") int page*/) {

        if (action == null) action = "";

        switch (action) {
            case "delete":
                Book book = bookService.get(id);
                //404

                bookService.delete(book);
        }

        /*model.addAttribute("title", title);
        model.addAttribute("readAlready", readAlready);

        BookService bookService = new BookService();
        List<Book> books = bookService.getBooks(page, title, readAlready);
        model.addAttribute("books", books);
        model.addAttribute("total", bookService.getTotal());
        model.addAttribute("totalPages", bookService.getPagesCount());
        model.addAttribute("page", page);

        model.addAttribute("one", readAlready.equals("0") ? "selected='selected'" : "");
        model.addAttribute("two", readAlready.equals("1") ? "selected='selected'" : "");*/

        List books = bookService.getAll();
        model.addAttribute("books", books);
        return "books";
    }


    @RequestMapping(value = "/add")
    public String add(Model model,
                      @RequestParam(value = "title", required = false, defaultValue = "") String title,
                      @RequestParam(value = "author", required = false, defaultValue = "") String author,
                      @RequestParam(value = "isbn", required = false, defaultValue = "") String isbn,
                      @RequestParam(value = "printYear", required = false, defaultValue = "") String printYear,
                      @RequestParam(value = "description", required = false, defaultValue = "") String description,
                      @RequestParam(value = "submit", required = false) String submit
    ) {
        model.addAttribute("errors", new HashMap<String, String[]>());
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("isbn", isbn);
        model.addAttribute("printYear", printYear);
        model.addAttribute("description", description);
        if (submit != null && !submit.isEmpty()) {
            if (save(null, title, author, isbn, printYear, description, model)) return "redirect:/";
        }
        return "form";
    }

    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String edit(Model model,
                       @PathVariable(value = "id") Integer id,
                       @RequestParam(value = "title", required = false, defaultValue = "") String title,
                       @RequestParam(value = "author", required = false, defaultValue = "") String author,
                       @RequestParam(value = "isbn", required = false, defaultValue = "") String isbn,
                       @RequestParam(value = "printYear", required = false, defaultValue = "") String printYear,
                       @RequestParam(value = "description", required = false, defaultValue = "") String description,
                       @RequestParam(value = "submit", required = false) String submit,
                       HttpServletRequest request
    ) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        model.addAttribute("errors", new HashMap<String, String[]>());
        //Сохранение
        if (submit != null && !submit.isEmpty()) {
            save(id, title, author, isbn, printYear, description, model);
        }

        //Получение книги
        Book book = bookService.get(id);
        model.addAttribute("book", book);

        return "form";
    }

    //@RequestMapping(value = "/book/save", method = RequestMethod.POST, params = {"id", "title", "author", "isbn", "year", "description"})
    public boolean save(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "author") String author,
            @RequestParam(value = "isbn") String isbn,
            @RequestParam(value = "year") String year,
            @RequestParam(value = "description", required = false, defaultValue = "") String description,
            Model model) {
        Book book = new Book();
        if (id != null) {
            book = bookService.get(id);
            //Если нет 404
            //return false;
        }

        //Validate
        Map<String, String[]> errors = new HashMap<>();
        if (title == null || title.isEmpty()) errors.put("title", new String[]{"Поле Название не может быть пустым!"});
        if (author == null || author.isEmpty()) errors.put("author", new String[]{"Поле Автор не может быть пустым!"});
        if (year == null || year.isEmpty()) errors.put("printYear", new String[]{"Поле Год печати не может быть пустым!"});
        else {
            Pattern pattern = Pattern.compile("^\\d{4}$");
            Matcher matcher = pattern.matcher(year);
            if (!matcher.find()) {
                errors.put("printYearError", new String[]{"Поле Год печати заполнено не по формату!"});
            }
        }
        model.addAttribute("errors", errors);
        if (errors.size() > 0) return false;

        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setDescription(description);
        book.setPrintYear(Integer.parseInt(year));

        model.addAttribute("id", id);
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("isbn", isbn);
        model.addAttribute("description", description);
        model.addAttribute("printYear", year);

        bookService.save(book);

        model.addAttribute("success", true);
        return true;
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String ajax(Model model, @RequestParam(value = "id") Integer id, @RequestParam(value = "action") String action) {
        String json = "";
        Book book = bookService.get(id);
        switch (action) {
            case "read_already":
                book.setReadAlready(book.isReadAlready() ? false : true);
                bookService.save(book);
                json = "{\"success\": true, \"readAlready\": " + book.isReadAlready() + "}";
                break;

            case "delete":

                bookService.delete(book);
                json = "{\"success\": true}";
                break;
        }
        return json;
    }
}
