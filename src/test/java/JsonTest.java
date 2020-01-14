import com.alibaba.fastjson.JSONArray;
import com.ebook.beans.good.Good;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;

/**
 * @author zxl
 * @date 2020/1/14 10:23
 * @describe
 */

public class JsonTest {

    @Test
    public void jsonTest(){

       /* String str = "[{\"id\":\"1\",\"buyNumber\":2},{\"id\":\"2\",\"buyNumber\":1}]";
        JSONArray array = JSONArray.parseArray(str);
        List<Good> list = array.toJavaList(Good.class);
        for(Good good:list){

            System.out.println(good.getId()+"，"+good.getBuyNumber());
        }*/

       String str = "";
       System.out.println(StringUtils.isBlank("123   "));

    }
}
