package hit.TeamProject;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendAttachmentByMail {
	
	public static void sendMailFromGmail(String subject,String textBody,final String myEmailAccount,final String password) {
		Scanner scan=new Scanner(System.in);
		int n=1;
		
		//recipients email addresses
		Set<String> recipients=new TreeSet<String>();
		System.out.println("Type your email addressess...");
		for(int i=0;i<n;i++) {
			recipients.add(scan.next()+"\n");
		}
		//creating session
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port","465");
		prop.put("mail.smtp.ssl.enable","true");
		prop.put("mail.smtp.auth","true");
		
		
		Session session=Session.getInstance(prop, new Authenticator() {//session object stores all the information of host like host name, username, password etc.
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(myEmailAccount, password);
			}
		});
		
		//Compose message
		try {
		MimeMessage message=new MimeMessage(session);
		
		message.setFrom(new InternetAddress(myEmailAccount));
		for(String recipient:recipients) {
			if(isValidEmailAddress(recipient)==true) {
				String userName=getUserName(recipient);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
				
				MimeMultipart mimeMultiPart=new MimeMultipart();
				
				String path="E:\\PdfECertificateCreation\\ProgressReport.xls";
				String path1="E:\\PdfECertificateCreation\\pdfECertificate.pdf";
				
				
				MimeBodyPart t=new MimeBodyPart();
				MimeBodyPart t1=new MimeBodyPart();
				MimeBodyPart a=new MimeBodyPart();
				MimeBodyPart a1=new MimeBodyPart();
				
				t.setText(subject);
				t1.setContent(textBody, "text/html");
				
				File file=new File(path);
				File file1=new File(path1);

				try {
				a.attachFile(file);
				a1.attachFile(file1);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				mimeMultiPart.addBodyPart(t);
				mimeMultiPart.addBodyPart(t1);
				mimeMultiPart.addBodyPart(a);
				mimeMultiPart.addBodyPart(a1);
				message.setContent(mimeMultiPart);
				
				System.out.println("Sending the mail...");
				Transport.send(message);
				System.out.println("Mail sent succesfully...");
			}
		}
		
		}catch(MessagingException mex) {
			mex.printStackTrace();
		}
		
	}
	//checking valid email
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	//extracting user name from email
	public static String getUserName(String email) {
		int index=email.indexOf("@");
		email=email.substring(0, index);
		return email;
	}
}



