import com.model.utills.mail.SendEmail;
import org.junit.Test;

public class MailTest {

    @Test
    public void test1(){

        try {
            SendEmail.sendemail("690345407@163.com","兄弟们邮件发送测试成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
