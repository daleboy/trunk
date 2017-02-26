package com.eshore.nrms.util;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * 邮件工具类
 * Created by forgeeks at 2017-02-26 15:58
 * 默认配置POP3/SMTP服务,并配置QQ邮箱来发送邮件
 */
public class MailUtil {
    public static void sendByDefault(String conferenceName, String conferenceTime, String conferenceLocation,List<String> targetList) throws MessagingException, IOException {
        SendMail mail = new SendMail("783808649@qq.com", "szzattotfkzzbbae");
        mail.sendEmailsByDefault(mail,conferenceName, conferenceTime,conferenceLocation,targetList);
    }
    public static void sendByDefaultWithFiles(String conferenceName, String conferenceTime, String conferenceLocation,List<String> targetList, List<String> files) throws MessagingException, IOException {
        SendMail mail = new SendMail("783808649@qq.com", "szzattotfkzzbbae");
        mail.sendEmailsByDefaultWithFiles(mail,conferenceName, conferenceTime,conferenceLocation,targetList,files);
    }
    public static void sendByMyType(String emailName,String emailTokenCode,String conferenceName, String conferenceTime, String conferenceLocation,List<String> targetList) throws MessagingException, IOException {
        SendMail mail = new SendMail(emailName, emailTokenCode);
        mail.sendEmailsByDefault(mail,conferenceName, conferenceTime,conferenceLocation,targetList);
    }
    public static void sendByMyTypeWithFiles(String emailName,String emailTokenCode,String conferenceName, String conferenceTime, String conferenceLocation,List<String> targetList, List<String> files) throws MessagingException, IOException {
        SendMail mail = new SendMail(emailName, emailTokenCode);
        mail.sendEmailsByDefaultWithFiles(mail,conferenceName, conferenceTime,conferenceLocation,targetList,files);
    }


}
