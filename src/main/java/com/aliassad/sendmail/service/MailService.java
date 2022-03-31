package com.aliassad.sendmail.service;

import com.aliassad.sendmail.model.Employee;
import com.aliassad.sendmail.model.MailContent;
import com.infobelt.excellent.WorkbookBuilder;
import com.infobelt.excellent.WorkbookReference;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static List<Employee> employees=new ArrayList<>(Arrays.asList(
            new Employee(1L,"ali","Java Developer","001"),
            new Employee(1L,"Mohammad","Team Lead","002"),
            new Employee(1L,"Ahmad","Technical Support","003")

    ));

    public void sendMail(MailContent mailContent) throws MessagingException {

        MimeMessage simpleMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(simpleMailMessage, true);
        message.setTo( mailContent.getTos());

        message.setSubject(mailContent.getSubject());
        message.setText(mailContent.getMessage());

        javaMailSender.send(simpleMailMessage);
    }


    public void sendMailWithGenerateExcelSheet(MailContent mailContent) throws MessagingException, IOException {

        WorkbookReference workbook = new WorkbookBuilder().sheet().title("EMPLOYEES").from(employees).endSheet().build();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.toWorkbook().write(byteArrayOutputStream);
        ByteArrayDataSource dataSource = new ByteArrayDataSource(byteArrayOutputStream.toByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        MimeMessage simpleMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(simpleMailMessage, true);
        message.setTo( mailContent.getTos());
        message.addAttachment("EMPLOYEES_REPORT.xlsx", dataSource);

        message.setSubject(mailContent.getSubject());
        message.setText(mailContent.getMessage());

        javaMailSender.send(simpleMailMessage);
    }
}
