import com.model.utills.mail.SendEmail;
import org.junit.Test;

public class MailTest {

    @Test
    public void test1(){

        String[] qqs = {"1965956192","1549361150","1655626823","1364145571","377102746","690345407","1151605848"};

        try {
            SendEmail.sendemail("690345407@qq.com","666");
           /* for(String qq:qqs){
                SendEmail.sendemail(qq+"@qq.com","<html><body><span>兄弟们，别忘了今晚九点的团，有事提早说，方便调整时间！</span></body></html>");
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
