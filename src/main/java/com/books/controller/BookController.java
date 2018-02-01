package com.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

    @RequestMapping("/")
    public ModelAndView books(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return new ModelAndView("pages/index");
    }

    @RequestMapping("/create")
    public ModelAndView create(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return new ModelAndView("pages/form");
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value="id") String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("name", id);
        return new ModelAndView("pages/form");
    }

}
