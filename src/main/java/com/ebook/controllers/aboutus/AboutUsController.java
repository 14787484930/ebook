package com.ebook.controllers.aboutus;

import com.ebook.beans.about.AboutUsQuery;
import com.ebook.services.AboutUsService;
import com.ebook.sys.log.SysLog;
import com.model.utills.messages.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@RequestMapping("/aboutus")
public class AboutUsController {
    /**
     *
     */
    @Autowired
    AboutUsService aboutUsService;
    @CrossOrigin
    @RequestMapping("/us")
    @ResponseBody
    @SysLog(moduleName = "查看关于我们的信息")
    public Object getAboutUs(HttpSession session, AboutUsQuery aboutUsQuery){
        return ResultInfo.success().add("pageinfo",aboutUsService.getAboutUs());
    }
    @CrossOrigin
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getById(@PathVariable("id") String id){

        return ResultInfo.success().add("info",aboutUsService.getById(id));
    }

}
