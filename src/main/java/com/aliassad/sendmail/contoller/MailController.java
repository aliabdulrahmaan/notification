package com.aliassad.sendmail.contoller;


import com.aliassad.sendmail.model.MailContent;
import com.aliassad.sendmail.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MailController {

    private final MailService mailService;


    @PostMapping("/mails/send-email")
    public ResponseEntity<String> sendEmail( @Valid @RequestBody MailContent mailContent)  {

        try {

           mailService.sendMail(mailContent);
           return ResponseEntity.ok("Email has been sent successfully");

        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/mails/generate-excel-sheet/send-email")
    public ResponseEntity<String> sendEmailWithAttach(@Valid @RequestBody MailContent mailContent)  {

        try {

            mailService.sendMailWithGenerateExcelSheet(mailContent);
            return ResponseEntity.ok("Email has been sent successfully");

        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
