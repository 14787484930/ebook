package com.ebook.controllers.book;

import com.ebook.beans.book.Book;
import com.ebook.services.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.messages.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/books")
    @ResponseBody
    public Object getBooks(@RequestParam(value = "pn", defaultValue = "2") Integer pn , Model model){

        //在查询之前注入页码和页面容量
        PageHelper.startPage(pn,2);
        //查询数据
        List<Book> list = bookService.getBooks();
        //包装查询后的结果
        PageInfo<Book> pageInfo = new PageInfo<Book>(list,3);
        //封装数据
        return ResultInfo.success().add("pageinfo",pageInfo);
    }


    /*@RequestMapping("/books")
    @ResponseBody
    public Object getBooks(@RequestParam(value = "pn", defaultValue = "2") Integer pn , Model model){

        //在查询之前注入页码和页面容量
        PageHelper.startPage(pn,2);
        //查询数据
        List<Book> list = bookService.getBooks();
        //包装查询后的结果
        PageInfo<Book> pageInfo = new PageInfo<Book>(list,3);
        return pageInfo;
    }*/

   /* @RequestMapping("/books")
    public Object getBooks(@RequestParam(value = "pn", defaultValue = "2") Integer pn ,Model model){

        //在查询之前注入页码和页面容量
        PageHelper.startPage(pn,2);
        //查询数据
        List<Book> list = bookService.getBooks();
        //包装查询后的结果
        PageInfo<Book> pageInfo = new PageInfo<Book>(list,3);

        model.addAttribute("pageinfo",pageInfo);
        return "books/books";
    }*/
}
