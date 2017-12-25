package org.trams.common.mail;

import java.util.Properties;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.*;

public class AmazonSES {
	
	//static final String FROM = "tv@micimpact.com";   // Replace with your "From" address. This address must be verified.
	static final String FROM = "help.whelearn@gmail.com";
    String TO;  // Replace with a "To" address. If your account is still in the 
                                                       // sandbox, this address must be verified.
    
    String BODY = "This email was sent through the Amazon SES SMTP interface by using Java.";
    String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";
    
    // Supply your SMTP credentials below. Note that your SMTP credentials are different from your AWS credentials.
    static final String SMTP_USERNAME = "AKIAINYMMMVFJMKN6SWQ";  // Replace with your SMTP username.
    static final String SMTP_PASSWORD = "Asjg8KqOElf+Qs6UMkmu1woSBnMcrOOSRlkoFX2vS9zi";  // Replace with your SMTP password.
    
    // Amazon SES SMTP host name. This example uses the us-west-2 region.
    static final String HOST = "email-smtp.eu-west-1.amazonaws.com";    
    
    // Port we will connect to on the Amazon SES SMTP endpoint. We are choosing port 25 because we will use
    // STARTTLS to encrypt the connection.
    static final int PORT = 25;
	
    public AmazonSES(String title, String body, String recv)
    {
    	this.TO = recv;
    	this.BODY = body;
    	this.SUBJECT = title;
    }
    
	public boolean sendMail() throws AddressException, MessagingException{
		// Create a Properties object to contain connection configuration information.
		Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtps");
    	props.put("mail.smtp.port", PORT); 
    	
    	// Set properties indicating that we want to use STARTTLS to encrypt the connection.
    	// The SMTP session will begin on an unencrypted connection, and then the client
        // will issue a STARTTLS command to upgrade to an encrypted connection.
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.starttls.required", "true");
    	props.put("mail.debug","true");
    	//props.put("ail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	//props.put("mail.smtp.socketFactory.fallback", "true");

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT,"UTF-8");
        msg.setContent(BODY,"text/html; charset=UTF-8");
        
        // Create a transport.        
        Transport transport = session.getTransport();
                    
        // Send the message.
        try
        {
            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
            return true;
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: ");
            ex.printStackTrace();
            return false;
        }
        finally
        {
        	
            // Close and terminate the connection.
            transport.close();        	
        }
	}

}
