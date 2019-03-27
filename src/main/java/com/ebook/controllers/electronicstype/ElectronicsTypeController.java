package com.ebook.controllers.electronicstype;

import com.ebook.beans.electronicstype.ElectronicsType;
import com.ebook.beans.electronicstype.ElectronicsTypeQuery;
import com.ebook.services.ElectronicsTypeService;
import com.model.utills.messages.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author gpj
 * @date 2018/12/21 13:48
 * @describe
 */

@CrossOrigin
@Controller
@RequestMapping("/electronicstype")
public class ElectronicsTypeController {

    @Autowired
    private ElectronicsTypeService electronicsTypeService;

    /**
     * gpj
     * @param electronicsTypeQuery
     * @param session
     * @return
     * 查看电子类型列表
     */
    @RequestMapping("/electronicsTypes")
    @ResponseBody
    public Object getElectronicsTypes(ElectronicsTypeQuery electronicsTypeQuery, HttpSession session){
        return ResultInfo.success().add("electronicsType", electronicsTypeService.getElectronicsType(electronicsTypeQuery));
    }

    /**
     * gpj
     * @param id
     * @return
     * 查看电子类型详情
     */
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getElectronicsTypeById(@PathVariable("id") String id){
        return ResultInfo.success().add("info", electronicsTypeService.getById(id));
    }

    /**
     * gpj
     * @param electronicsType
     * @return
     * 编辑电子类型
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object updata(@Valid ElectronicsType electronicsType){
        return ResultInfo.success();
    }

    /**
     * gpj
     * @param id
     * @return
     * 删除电子类型
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable("id") String id ){
        ElectronicsTypeQuery query = new ElectronicsTypeQuery();
        query.setId(id);
        electronicsTypeService.delete(query);

        return ResultInfo.success();
    }

    /**
     * gpj
     * @param electronicsType
     * @return
     * 保存电子类型
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@Valid ElectronicsType electronicsType){
        electronicsTypeService.save(electronicsType);
        return ResultInfo.success();
    }
}
