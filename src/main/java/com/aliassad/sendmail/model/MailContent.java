package com.aliassad.sendmail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class MailContent {

   @NotNull
   private String message;
   @NotNull
   private String subject;
   private String [] tos ;
}
