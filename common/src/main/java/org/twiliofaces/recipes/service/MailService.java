package org.twiliofaces.recipes.service;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
@LocalBean
public class MailService implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Resource(mappedName = "java:/mail/Gmail")
   private Session mailSession;

   public boolean send(String from, String to, String subject, String content)
   {
      try
      {
         MimeMessage m = new MimeMessage(mailSession);
         Address fromAddress = new InternetAddress(from);
         Address[] toAddress = new InternetAddress[] { new InternetAddress(to) };

         m.setFrom(fromAddress);
         m.setRecipients(Message.RecipientType.TO, toAddress);
         m.setSubject(subject);
         m.setSentDate(new java.util.Date());
         m.setContent(content, "text/plain");
         Transport.send(m);
         System.out.println("Mail sent!");
         return true;
      }
      catch (javax.mail.MessagingException e)
      {
         e.printStackTrace();
         System.out.println("Error in Sending Mail: " + e);
      }
      return false;
   }
}
