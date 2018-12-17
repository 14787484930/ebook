import com.ebook.beans.book.Book;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 测试Controller
 * zxl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class TestController {

    @Autowired
    WebApplicationContext context;

    //虚拟MVC请求，获取请求结果
    MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test() throws Exception {

        //发送get请求
        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.get("/books").param("pn","2")).andReturn();

        MockHttpServletRequest request = result.getRequest();

        PageInfo<Book> pageInfo = (PageInfo<Book>) request.getAttribute("pageinfo");

        System.out.println(pageInfo);
        System.out.println("当前页码:"+pageInfo.getPageNum());
        System.out.println("页数"+pageInfo.getPages());
        System.out.println("总数："+pageInfo.getTotal());

        System.out.println("要显示的页码：");

        int[] nums = pageInfo.getNavigatepageNums();

        for (int i=0;i<nums.length;i++){

            System.out.print(nums[i]+" ");
        }

        System.out.println("输出图书数据：");
        List<Book> list = pageInfo.getList();
        for(Book book:list){
            //System.out.println("书名："+book.getName()+" 图片："+book.getPic()+" 价格："+book.getPrice());
        }

    }
}
