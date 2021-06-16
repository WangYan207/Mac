package com.bjsxt.service.impl;

import com.bjsxt.utils.CommonsMailSender;
import freemarker.template.TemplateException;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.io.IOException;

@DubboService
public class EmailDubboServiceImpl {
    @Autowired
    private CommonsMailSender commonsMailSender;
    public void sendMail(String to, String subject, String content) {
        try {
            commonsMailSender.send(to, subject, content);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
