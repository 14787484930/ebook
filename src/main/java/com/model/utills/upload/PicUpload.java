package com.model.utills.upload;

import com.model.utills.constants.Constant;
import com.model.utills.uuid.GeneratingId;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * 图片上传
 */
public class PicUpload {

    /*x新增时上传图片*/
    public static String uploadPic(MultipartFile[] pics, HttpSession session,String type)throws Exception{

        StringBuffer sb = new StringBuffer();

        //获取文件上传路径
        //String path = session.getServletContext().getRealPath("/pictures/" + type); //放在类路径下
        String path = Constant.PICPATH + type; //放在nginx服务器上
        File sy = new File(Constant.SYPATH);

        for(MultipartFile pic : pics) {

            String endStr = StringUtils.substringAfterLast(pic.getOriginalFilename(),".");

            File file = new File(path, GeneratingId.getId() + "." + endStr);

            if(file.length() <= 102400){
                Thumbnails.of(pic.getInputStream()).scale(1f).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(sy), 0.5f).toFile(file);
            }else{
                Thumbnails.of(pic.getInputStream()).scale(0.2f).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(sy), 0.5f).toFile(file);
            }



            sb.append("/" + type + "/").append(file.getName()).append(",");

        };
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    /*编辑时删除原来图片*/
    public static boolean delPic(String picsPath,HttpSession session){

        //获取文件上传路径
        //String path = session.getServletContext().getRealPath("/");

        String path = StringUtils.substringBeforeLast(Constant.PICPATH,"/"); //放在nginx服务器上

        //解析出单个图片的路径
        String[] pics = StringUtils.split(picsPath,",");

        for(String pic:pics){
            File file = new File(path+pic);
            file.delete();
        }

        return true;
    }
}
