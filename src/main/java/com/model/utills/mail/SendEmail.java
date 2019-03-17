package com.model.utills.mail;

import java.io.IOException;
import javax.mail.Session;

import cn.itcast.mail.Mail;

import com.model.utills.mail.MailUtils;


public class SendEmail {

	public static void sendemail(String toAddr,String content) throws Exception, IOException{

		//创建session
		//qq邮箱
		Session session = MailUtils.createSession("smtp.qq.com", "690345407", "");
		//163邮箱
		//Session session = MailUtils.createSession("smtp.163.com", "14787484930", "xl2013725");

		//创建邮件对象
		//qq邮箱
		Mail mail = new Mail("690345407@qq.com",toAddr,"西林易市",content);
		//163邮箱
		//Mail mail = new Mail("14787484930@163.com",toAddr,"西林易市",str);


		//发送邮件
		MailUtils.send(session, mail,"西林易市");
	}
}
