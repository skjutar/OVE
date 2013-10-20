/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * An EJB that sends confirmation emails to newly created users 
 * @author kristofferskjutar
 */
@Stateful  // A stateless EJB
@LocalBean
public class MailBean {
    
     @Resource(name = "mail/myMailSession")
     private Session mailSession;
    
     
     public void sendEmail(String to, Long id) throws MessagingException
     {
         // Create the message object
     Message message = new MimeMessage(mailSession);

     // Adjust the recipients. Here we have only one
     // recipient. The recipient's address must be
     // an object of the InternetAddress class.
     message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));
    
     String subject = "Confirm your email";
     // Set the message's subject
     message.setSubject(subject);
         
     String msg = "You have succesfully created an account on the epic site OVE! \n You just need to confirm this email with this link: http://localhost:8080/OVE/confirm?token="+id;
     // Insert the message's body
     message.setText(msg);

     // This is not mandatory, however, it is a good
     // practice to indicate the software which
     // constructed the message.
     message.setHeader("X-Mailer", "My Mailer");

     // Adjust the date of sending the message
     Date timeStamp = new Date();
     message.setSentDate(timeStamp);

     // Use the 'send' static method of the Transport
     // class to send the message
     Transport.send(message);
     }
    
}
