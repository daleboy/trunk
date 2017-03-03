package com.eshore.nrms.util;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;

import com.eshore.nrms.sysmgr.pojo.Application;
import com.eshore.nrms.sysmgr.pojo.User;

import java.io.IOException;
import java.util.*;

/**
 * 邮件工具类
 * Created by forgeeks at 2017-02-26 15:58
 * 默认配置POP3/SMTP服务,并配置QQ邮箱来发送邮件
 */

public class MailUtil {


	/**
	 * 定时发送邮件
	 *
	 * @param sendTime  发送时间
	 * @param subject  邮件主题
	 * @param text  邮件正文
	 * @param targetList  收件人集合
	 */
	public static void sendTimerEmailBySC (Date sendTime, String subject , String text, List<String> targetList){
		Timer timer = new Timer();
		long period = sendTime.getTime()-System.currentTimeMillis();
		if(period<0) period=0;
		timer.schedule(  new MailTimerTask(subject,text,targetList)  , period);
	}






	/**
	 * 改进的默认邮件发送方式
	 * @param application 会议
	 * @param userList  用户集合
	 * @throws MessagingException
	 * @throws IOException
	 */
	 public static void sendMailBySC(String subject,String context,List<String> targetList) throws MessagingException, IOException {
	        SendMail mail = new SendMail("783808649@qq.com", "szzattotfkzzbbae");
	        mail.sendEmailsBySC(mail,subject,context,targetList);
	 }
	
	
	/**
	 * 改进的默认邮件发送方式
	 * @param application 会议
	 * @param userList  用户集合
	 * @throws MessagingException
	 * @throws IOException
	 */
	 public static void sendByDev(Application application,List<User> userList) throws MessagingException, IOException {
	        SendMail mail = new SendMail("783808649@qq.com", "szzattotfkzzbbae");
	        String time=application.getStartDate()+"  "+ application.getStartTime()+"至"+application.getEndTime();
	        List<String> list= new ArrayList<String>();
	        for(User user: userList){
	        	if( StringUtils.isNoneBlank(user.getEmail()) )
	        	list.add(user.getEmail());
	        }
	        mail.sendEmailsByDefault(mail,time,application.getPlaceName(),time,list);
	 }
	 
	 /**
		 * 改进的自定义邮件发送方式
		 * @param application 会议
		 * @param userList  用户集合
		 * @throws MessagingException
		 * @throws IOException
		 */
		 public static void sendByDev(String emailName,String emailTokenCode,Application application,List<User> userList) throws MessagingException, IOException {
		        SendMail mail = new SendMail(emailName, emailTokenCode);
		        String time=application.getStartDate()+"  "+ application.getStartTime()+"至"+application.getEndTime();
		        List<String> list= new ArrayList<String>();
		        for(User user: userList){
		        	if( StringUtils.isNoneBlank(user.getEmail()) )
		        	list.add(user.getEmail());
		        }
		        mail.sendEmailsByDefault(mail,time,application.getPlaceName(),time,list);
		 }
		
	
	
	
	/**
	 * 使用默认邮箱发送会议通知邮件
	 * @param conferenceName 会议名
	 * @param conferenceTime 会议时间
	 * @param conferenceLocation 会议地点
	 * @param targetList 收件人集合
	 * @throws MessagingException  
	 * @throws IOException
	 */
    public static void sendByDefault(String conferenceName, String conferenceTime, String conferenceLocation,List<String> targetList) throws MessagingException, IOException {
        SendMail mail = new SendMail("783808649@qq.com", "szzattotfkzzbbae");
        mail.sendEmailsByDefault(mail,conferenceName, conferenceTime,conferenceLocation,targetList);
    }
    
    
    /**
     * 
     * 使用默认邮箱发送会议通知邮件（带附件）
	 * @param conferenceName 会议名
	 * @param conferenceTime 会议时间
	 * @param conferenceLocation 会议地点
	 * @param targetList 收件人集合
     * @param files 附件
     * @throws MessagingException
     * @throws IOException
     */
    public static void sendByDefaultWithFiles(String conferenceName, String conferenceTime, String conferenceLocation,List<String> targetList, List<String> files) throws MessagingException, IOException {
        SendMail mail = new SendMail("783808649@qq.com", "szzattotfkzzbbae");
        mail.sendEmailsByDefaultWithFiles(mail,conferenceName, conferenceTime,conferenceLocation,targetList,files);
    }
    
    
    /**
     * 自定义邮箱发会议通知邮件
     * @param emailName 邮件名
     * @param emailTokenCode 邮件token
     * @param conferenceName 会议名
     * @param conferenceTime 会议时间
     * @param conferenceLocation 会议地点
     * @param targetList 收件人集合
     * @throws MessagingException
     * @throws IOException
     */
    public static void sendByMyType(String emailName,String emailTokenCode,String conferenceName, String conferenceTime, String conferenceLocation,List<String> targetList) throws MessagingException, IOException {
        SendMail mail = new SendMail(emailName, emailTokenCode);
        mail.sendEmailsByDefault(mail,conferenceName, conferenceTime,conferenceLocation,targetList);
    }
    
    /**
     * 自定义邮箱发会议通知邮件(带附件)
     * @param emailName 邮件名
     * @param emailTokenCode 邮件token
     * @param conferenceName 会议名
     * @param conferenceTime 会议时间
     * @param conferenceLocation 会议地点
     * @param targetList 收件人集合
     * @param files
     * @throws MessagingException
     * @throws IOException
     */
    
    public static void sendByMyTypeWithFiles(String emailName,String emailTokenCode,String conferenceName, String conferenceTime, String conferenceLocation,List<String> targetList, List<String> files) throws MessagingException, IOException {
        SendMail mail = new SendMail(emailName, emailTokenCode);
        mail.sendEmailsByDefaultWithFiles(mail,conferenceName, conferenceTime,conferenceLocation,targetList,files);
    }
    
    
    
    


}
