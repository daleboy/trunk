
package com.eshore.nrms.util;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * Created by forgeeks at 2017-02-26 14:58
 */

public class SendMail {
    private String username = null;
    private String password = null;
    private Authenticator auth = null;
    private MimeMessage mimeMessage = null;
    private Properties pros = null;
    private Multipart multipart = null;
    private BodyPart bodypart = null;


	/** 
	 * 邮箱发送会议通知邮件
	 * @param conferenceName 会议名
	 * @param conferenceTime 会议时间
	 * @param conferenceLocation 会议地点
	 * @param targetList 收件人集合
	 * @throws MessagingException  
	 * @throws IOException
	 */
    public void sendEmailsByDefault(SendMail mail, String conferenceName, String conferenceTime, String conferenceLocation,List<String> targetList) throws MessagingException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("mail.smtp.host", "smtp.qq.com");
        map.put("mail.smtp.auth", "true");
        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        map.put("mail.smtp.port", "465");
        map.put("mail.smtp.socketFactory.port", "465");
        mail.setPros(map);
        mail.initMessage();
        mail.setRecipients(targetList);
        mail.setSubject(conferenceName);
        mail.setDate(new Date());
        mail.setFrom("亿迅研发会议室管理员");
        mail.setContent("你好，你参加的会议【" + conferenceName + "】即将在" + conferenceLocation + " 于 " + conferenceTime + "   召开，请稍作准备！", "text/html; charset=UTF-8");

        List<String> fileList = new ArrayList<String>();
        fileList.add("C:/Users/for geek/Pictures/pic/04.png");
        fileList.add("C:/Users/for geek/Pictures/pic/04.zip");
        mail.setMultiparts(fileList);
        String info = mail.sendMessage();
        generateReport(info,targetList);
    }

    /**
     * 发送会议通知邮件（带附件）
	 * @param conferenceName 会议名
	 * @param conferenceTime 会议时间
	 * @param conferenceLocation 会议地点
	 * @param targetList 收件人集合
     * @param files 附件
     * @throws MessagingException
     * @throws IOException
     */
    public void sendEmailsByDefaultWithFiles(SendMail mail, String conferenceName, String conferenceTime, String conferenceLocation,List<String> targetList, List<String> files) throws MessagingException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("mail.smtp.host", "smtp.qq.com");
        map.put("mail.smtp.auth", "true");
        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        map.put("mail.smtp.port", "465");
        map.put("mail.smtp.socketFactory.port", "465");
        mail.setPros(map);
        mail.initMessage();
        mail.setRecipients(targetList);
        mail.setSubject(conferenceName);
        mail.setDate(new Date());
        mail.setFrom("亿迅研发会议室管理员");
        mail.setContent("你好，你参加的会议【" + conferenceName + "】即将在" + conferenceLocation + "于 " + conferenceTime + " 召开，请稍作准备！", "text/html; charset=UTF-8");
        mail.setMultiparts(files);
        String info = mail.sendMessage();
        generateReport(info,targetList);
    }



    public void generateReport(String info,List<String> list) {
        if(info.equals("success")) {
            System.out.println(" :-)  成功邮件通知了所有准备参加该会议的人员 !");
        }else {
            System.out.println(" :-(  由于网络原因或自定义邮箱配置等异常，部分参加该会议的人员未通知到！");
        }
    }

    public static void main(String[] args) throws MessagingException, IOException {

        Map<String, String> map = new HashMap<String, String>();
        SendMail mail = new SendMail("783808649@qq.com", "szzattotfkzzbbae");
        map.put("mail.smtp.host", "smtp.qq.com");

        //暂时未成功，需要调试
        /*SendMail mail = new SendMail("14789****@sina.cn","***miya");
        map.put("mail.smtp.host", "smtp.sina.com");*/
        map.put("mail.smtp.auth", "true");
        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        map.put("mail.smtp.port", "465");
        map.put("mail.smtp.socketFactory.port", "465");
        mail.setPros(map);
        mail.initMessage();
        /*
         * 添加收件人有三种方法：
         * 1,单人添加(单人发送)调用setRecipient(str);发送String类型
         * 2,多人添加(群发)调用setRecipients(list);发送list集合类型
         * 3,多人添加(群发)调用setRecipients(sb);发送StringBuffer类型
         */
        /*
        List<String> list = new ArrayList<String>();
        list.add("forgeekscn@gmail.com");
        //list.add("***92@sina.cn");
        list.add("13720308660@163.com");
        mail.setRecipients(list);*/

        String defaultStr = "forgeekscn@gmail.com,13720308660@163.com";
        StringBuffer sb = new StringBuffer();
        sb.append(defaultStr);
//        sb.append(",316121113@qq.com");
        mail.setRecipients(sb);

        mail.setSubject("会议通知-亿迅研发组");
        //mail.setText("谢谢合作");
        mail.setDate(new Date());
        mail.setFrom("杨晓秋（亿迅研发组经理）");
//      mail.setMultipart("D:你你你.txt");
        mail.setContent("你好何超，你参加的会议【近期项目进度汇报】即将在A会议室于今日14:30召开，请稍作准备！", "text/html; charset=UTF-8");

        List<String> fileList = new ArrayList<String>();
        fileList.add("C:/Users/for geek/Pictures/pic/04.png");
        fileList.add("C:/Users/for geek/Pictures/pic/04.zip");
//        fileList.add("D:dstz.sql");
//        fileList.add("D:软件配置要求.doc");
        mail.setMultiparts(fileList);

        System.out.println(mail.sendMessage());
    }


    /**
     * 初始化账号密码并验证
     * 创建MimeMessage对象
     * 发送邮件必须的步骤:1
     *
     * @param username
     * @param password
     */
    public SendMail(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 初始化MimeMessage对象
     * 发送邮件必须的步骤:3
     */
    public void initMessage() {
        this.auth = new Email_Autherticator();
        Session session = Session.getDefaultInstance(pros, auth);
        session.setDebug(false); //设置获取 debug 信息
        mimeMessage = new MimeMessage(session);
    }

    /**
     * 设置email系统参数
     * 接收一个map集合key为string类型，值为String
     * 发送邮件必须的步骤:2
     *
     * @param map
     */
    public void setPros(Map<String, String> map) {
        pros = new Properties();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            pros.setProperty(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 验证账号密码
     * 发送邮件必须的步骤
     */
    public class Email_Autherticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }

    /**
     * 设置发送邮件的基本参数(去除繁琐的邮件设置)
     *
     * @param sub  设置邮件主题
     * @param text 设置邮件文本内容
     * @param rec  设置邮件接收人
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public void setDefaultMessagePros(String sub, String text, String rec) throws MessagingException, UnsupportedEncodingException {
        mimeMessage.setSubject(sub);
        mimeMessage.setText(text);
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(rec));
        mimeMessage.setSentDate(new Date());
        mimeMessage.setFrom(new InternetAddress(username, username));
    }

    /**
     * 设置主题
     *
     * @param subject
     * @throws MessagingException
     */
    public void setSubject(String subject) throws MessagingException {
        mimeMessage.setSubject(subject);
    }

    /**
     * 设置日期
     *
     * @param date
     * @throws MessagingException
     */
    public void setDate(Date date) throws MessagingException {
        mimeMessage.setSentDate(new Date());
    }

    /**
     * 设置邮件文本内容
     *
     * @param text
     * @throws MessagingException
     */
    public void setText(String text) throws MessagingException {
        mimeMessage.setText(text);
    }

    /**
     * 设置邮件头部
     *
     * @param arg0
     * @param arg1
     * @throws MessagingException
     */
    public void setHeader(String arg0, String arg1) throws MessagingException {
        mimeMessage.setHeader(arg0, arg1);
    }

    /**
     * 设置邮件接收人地址 <单人发送>
     *
     * @param recipient
     * @throws MessagingException
     */
    public void setRecipient(String recipient) throws MessagingException {
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    }

    /**
     * 设置邮件接收人地址 <多人发送>
     *
     * @throws MessagingException
     * @throws AddressException
     */
    public String setRecipients(List<String> recs) throws AddressException, MessagingException {
        if (recs.isEmpty()) {
            return "接收人地址为空!";
        }
        for (String str : recs) {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(str));
        }
        return "加入接收人地址成功!";
    }

    /**
     * 设置邮件接收人地址 <多人发送>
     *
     * @throws MessagingException
     * @throws AddressException
     */
    @SuppressWarnings("static-access")
    public String setRecipients(StringBuffer sb) throws AddressException, MessagingException {
        if (sb == null || "".equals(sb)) {
            return "字符串数据为空!";
        }
        Address[] address = new InternetAddress().parse(sb.toString());
        mimeMessage.addRecipients(Message.RecipientType.TO, address);
        return "收件人加入成功";
    }

    /**
     * 设置邮件发送人的名字
     *
     * @param from
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public void setFrom(String from) throws UnsupportedEncodingException, MessagingException {
        mimeMessage.setFrom(new InternetAddress(username, from));
    }

    /**
     * 发送邮件<单人发送>
     * return 是否发送成功
     *
     * @throws MessagingException
     */
    public String sendMessage() throws MessagingException {
        Transport.send(mimeMessage);
        return "success";
    }

    /**
     * 设置附件
     *
     * @param file 发送文件的路径
     */
    public void setMultipart(String file) throws MessagingException, IOException {
        if (multipart == null) {
            multipart = new MimeMultipart();
        }
        multipart.addBodyPart(writeFiles(file));
        mimeMessage.setContent(multipart);
    }

    /**
     * 设置附件<添加多附件>
     *
     * @param fileList<接收List集合>
     * @throws MessagingException
     * @throws IOException
     */
    public void setMultiparts(List<String> fileList) throws MessagingException, IOException {
        if (multipart == null) {
            multipart = new MimeMultipart();
        }
        for (String s : fileList) {
            multipart.addBodyPart(writeFiles(s));
        }
        mimeMessage.setContent(multipart);
    }

    /**
     * 发送文本内容，设置编码方式
     * <方法与发送附件配套使用>
     * <发送普通的文本内容请使用setText()方法>
     *
     * @param s
     * @param type
     * @throws MessagingException
     */
    public void setContent(String s, String type) throws MessagingException {
        if (multipart == null) {
            multipart = new MimeMultipart();
        }
        bodypart = new MimeBodyPart();
        bodypart.setContent(s, type);
        multipart.addBodyPart(bodypart);
        mimeMessage.setContent(multipart);
        mimeMessage.saveChanges();
    }

    /**
     * 读取附件
     *
     * @param filePath
     * @return
     * @throws IOException
     * @throws MessagingException
     */
    public BodyPart writeFiles(String filePath) throws IOException, MessagingException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("文件不存在!请确定文件路径是否正确");
        }
        bodypart = new MimeBodyPart();
        DataSource dataSource = new FileDataSource(file);
        bodypart.setDataHandler(new DataHandler(dataSource));
        //文件名要加入编码，不然出现乱码
        bodypart.setFileName(MimeUtility.encodeText(file.getName()));
        return bodypart;
    }

}