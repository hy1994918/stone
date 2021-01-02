package com.kdmins.main;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
class MainApplicationTests {
    @Autowired
    private JavaMailSender javaMailSender;
    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }
    @Test
    void text1() {
        System.out.println(readFileContent("D:/java/myPro/kdmins/main/src/main/resources/static/resume/test.html"));



//        创建媒体消息
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("1154474377@qq.com");//发送人
            helper.setTo("1154474377@qq.com");//接收人
            helper.setSubject("邮件标题");//标题
            helper.setText(readFileContent("D:/java/myPro/kdmins/main/src/main/resources/static/resume/test.html"), true);//发送的内容

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
