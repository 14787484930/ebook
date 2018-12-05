package com.ebook.controllers.book;

import com.ebook.beans.book.Book;
import com.ebook.beans.book.BookQuery;
import com.ebook.log.SysLog;
import com.ebook.services.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.messages.ResultInfo;
import com.model.utills.upload.PicUpload;
import com.model.utills.validate.ValidateDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/books")
    @ResponseBody
    @SysLog(moduleName = "zxl查看所有信息")
    public Object getBooks(@RequestParam(value = "pn", defaultValue = "2") Integer pn , Model model){

        //在查询之前注入页码和页面容量
        PageHelper.startPage(pn,2);
        //查询数据
        List<Book> list = bookService.getBooks();
        //包装查询后的结果
        PageInfo<Book> pageInfo = new PageInfo<Book>(list,3);
        //封装数据
        return ResultInfo.success().add("pageInfo",pageInfo);
    }

    @RequestMapping(value="/getById/{id}")
    @ResponseBody
    @SysLog(moduleName = "zxl查看信息")
    public Object getById(@PathVariable("id") String id){

        BookQuery query = new BookQuery();
        query.setId(id);
        return ResultInfo.success().add("info",bookService.getById(query));
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(@Valid Book book, BindingResult result){

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        //校验成功,,进行保存操作
        bookService.update(book);
        return ResultInfo.success();

    }

    @RequestMapping(value="/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable("id") String id){

        BookQuery query = new BookQuery();
        query.setId(id);
        bookService.delete(query);
        return ResultInfo.success();
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(@Valid Book book, BindingResult result){

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        //校验成功,,进行保存操作
        bookService.save(book);
        return ResultInfo.success();

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

    /**
     * 图片上传测试
     */
    @RequestMapping("/upload")
    public String doFileUpload(@RequestParam(value="Title") String Title, @RequestParam(value="file",required=false) MultipartFile[] file, HttpSession session) throws Exception {


        //图片上传
        String picsPath = PicUpload.uploadPic(file,session,"book");

        //图片删除
        //PicUpload.delPic(picsPath,session);

        return null;
    }


}
