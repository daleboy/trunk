package com.eshore.nrms.util;

import com.eshore.nrms.util.MailUtil;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by forgeeks at 2017-03-03 14:33
 */
public class MailTimerTask extends TimerTask {


    private String subject ;
    private String text ;
    private List<String> targetList;


    public MailTimerTask(  String subject, String text, List<String> targetList) {

        this.subject=subject;
        this.text=text;
        this.targetList=targetList;

    }



    @Override
    public void run() {
        try {

            MailUtil.sendMailBySC(this.subject,this.text,this.targetList);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
