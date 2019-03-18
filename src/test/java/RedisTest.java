import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zxl
 * @date 2019/3/18 15:08
 * @describe
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RedisTest {

    @Autowired
    private RedisTemplate<String,String> template;

    @Test
    public void test(){

        System.out.println(123);
        template.boundValueOps("zxl").set("zxl");
    }


}
