import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author zxl
 * @date 2019/4/23 11:02
 * @describe
 */
public class PicEditTest {


    @Test
    public void test() throws IOException {

        File file = new File("C:\\Users\\dell\\Desktop\\pic\\2\\0835fff059694874ad64a0d9cf803eee.jpg");
        File tofile = new File("C:\\Users\\dell\\Desktop\\pic\\2\\2.jpg");
        //Thumbnails.of(file).scale(1f).outputQuality(0.25f).toFile(tofile);
        //Thumbnails.of(file).size(1280,960).toFile(tofile);
        //System.out.println(1/(file.length()/(1024*50)));
        //System.out.println(tofile.length()/1024);

        BigDecimal b1 = new BigDecimal(Double.toString(file.length()));
        BigDecimal b2 = new BigDecimal(Double.toString(50*1024));
        float d = b2.divide(b1,4).floatValue();
        System.out.println(d);
        Thumbnails.of(file).scale(0.25f).toFile(tofile);
        System.out.println(file.length());
        System.out.println(tofile.length());

    }
}
