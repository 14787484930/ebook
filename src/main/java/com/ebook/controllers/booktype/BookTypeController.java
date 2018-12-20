package com.ebook.controllers.booktype;

import com.ebook.beans.booktype.BookType;
import com.ebook.beans.booktype.BookTypeQuery;
import com.ebook.services.BookTypeService;
import com.model.utills.messages.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author zxl
 * @date 2018/12/20 13:48
 * @describe
 */

@Controller
@RequestMapping("/booktype")
public class BookTypeController {

    @Autowired
    private BookTypeService bookTypeService;

    /**
     * zxl
     * @param bookTypeQuery
     * @param session
     * @return
     * 2018/12/20 13:49
     * 查看图书类型列表
     */
    @RequestMapping("/booktypes")
    @ResponseBody
    public Object getBookTypes(BookTypeQuery bookTypeQuery, HttpSession session){

        return ResultInfo.success().add("booktypes",bookTypeService.getBookTypes(bookTypeQuery));
    }

    /**
     * zxl
     * @param id
     * @return
     * 2018/12/20 13:51
     * 查看图书类型详情
     */
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getBookTypeById(@PathVariable("id") String id){

        return ResultInfo.success().add("info",bookTypeService.getById(id));
    }

    /**
     * zxl
     * @param bookType
     * @return
     * 2018/12/20 13:55
     * 编辑图书类型
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@Valid BookType bookType){

        bookTypeService.update(bookType);
        return ResultInfo.success();
    }

    /**
     * zxl
     * @param id
     * @return
     * 2018/12/20 13:57
     * 删除图书类型
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable("id") String id){

        BookTypeQuery query = new BookTypeQuery();
        query.setId(id);
        bookTypeService.delete(query);

        return ResultInfo.success();
    }

    /**
     * zxl
     * @param bookType
     * @return
     * 2018/12/20 13:59
     * 保存图书类型
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@Valid BookType bookType){

        bookTypeService.save(bookType);
        return ResultInfo.success();
    }

}
