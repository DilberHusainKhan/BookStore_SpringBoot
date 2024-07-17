package com.humayun.bookStore.controller;

import com.humayun.bookStore.entity.Book;
import com.humayun.bookStore.entity.MyBook;
import com.humayun.bookStore.service.BookService;
import com.humayun.bookStore.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
@Controller
public class BookController {
    @Autowired
    private BookService bservice;

    @Autowired
    private MyBookService myBookService;

    @GetMapping("/")
    public String home(){
        return "Home";
    }
    @GetMapping("/book_register")
    public String bookRegister(){
        return "bookRegister";
    }
    @GetMapping("/available_book")
    public ModelAndView getALLBook(){
        List<Book> bList =bservice.getAllBook();
        System.out.println(bList);
        System.out.println("Dilber");
        return new ModelAndView("bookListPage","book",bList);
    }
    @GetMapping("/my_book")
    public String getSelectedBook(Model model){
        List<MyBook> myBooks = myBookService.getAllBook();
        model.addAttribute("book",myBooks);
        return "myBookList";
    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b){
        bservice.save(b);
        return "redirect:/available_book";
    }
    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id){
//        System.out.println(id);
//        System.out.println("KHAN");
        Book b = bservice.getBookById(id);
        MyBook mb = new MyBook(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookService.saveMyBook(mb);
        return "redirect:/my_book";
    }

    @RequestMapping("/deleteItemAva/{id}")
    public String deleteById(@PathVariable("id") int id){
        bservice.deleteById(id);
        return "redirect:/available_book";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book b = bservice.getBookById(id);
        model.addAttribute("book",b);
        return "BookEdit";
    }
}
