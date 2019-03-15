package com.model.utills.mail;

import java.io.IOException;
import javax.mail.Session;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

public class SendEmail {

	public static void sendemail(String toAddr,String str) throws Exception, IOException{

		//创建session
		Session session = MailUtils.createSession("smtp.163.com", "14787484930", "");

		//创建邮件对象
		Mail mail = new Mail("14787484930@163.com",toAddr,"西林易市",str);

		//发送邮件
		MailUtils.send(session, mail);
	}
}
