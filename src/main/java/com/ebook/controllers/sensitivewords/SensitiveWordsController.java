package com.ebook.controllers.sensitivewords;

import com.ebook.beans.sensitivewords.SensitiveWords;
import com.ebook.beans.sensitivewords.SensitiveWordsQuery;
import com.ebook.services.SensitiveWordsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.messages.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zxl
 * @date 2019/1/29 16:17
 * @describe
 */
@Controller
@RequestMapping("/sensitiveword")
public class SensitiveWordsController {

    @Autowired
    SensitiveWordsService sensitiveWordsService;

    /**
     * zxl
     * @param sensitiveWordsQuery
     * @return
     * 查询敏感词详细信息
     */
    @RequestMapping("/sensitivewords")
    @ResponseBody
    public Object getSensitiveWords(SensitiveWordsQuery sensitiveWordsQuery){

        PageHelper.startPage(sensitiveWordsQuery.getPageNumber(),sensitiveWordsQuery.getPageSize());

        List<SensitiveWords> list = sensitiveWordsService.getSensitiveWords(sensitiveWordsQuery);

        PageInfo<SensitiveWords> pageInfo = new PageInfo<SensitiveWords>(list,10);

        return ResultInfo.success().add("pageinfo",pageInfo);
    }

    /**
     * zxl
     * @return
     * 查看
     */
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getById(@PathVariable("id") String id){

        return ResultInfo.success().add("info",sensitiveWordsService.getById(id));
    }

    /**
     * zxl
     * @param sensitiveWords
     * @return
     * 编辑铭感词
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@Valid SensitiveWords sensitiveWords){

        sensitiveWordsService.update(sensitiveWords);
        return ResultInfo.success();
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(@Valid SensitiveWords sensitiveWords){

        sensitiveWordsService.save(sensitiveWords);
        return ResultInfo.success();
    }
}
