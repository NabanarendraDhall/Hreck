package com.HReckAutomation.CommonUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	public static void mail() {
		final String user = "narendra.dhall@techeasesystems.in";// change accordingly
		final String password = "Narendra@123";// change accordingly
		String[] to = { "dhallnabanarendra@gmail.com", "ajit.swami@techeasesystems.in","narendra.dhall@techeasesystems.in" };// change accordingly
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtpout.secureserver.net");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "3535");
		props.put("mail.smtp.starttis.enable", "true");
		props.put("mail.smtp.starttis.equired", "true");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			for (String toEmail : to) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			}
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
			Date dateobj = new Date();
			message.setSubject("TES Automation Test Report: " + df.format(dateobj));
			// message.setText("This is simple program of sending email using JavaMail
			// API");
			BodyPart messageBodyPart = new MimeBodyPart();
			// Fill the message
			messageBodyPart.setText(
					"Hi Team,\nPlease find the automation test report on "+df.format(dateobj)+" in the below attachment.\nThe attachment is a HTML file so open it with an editor for best result.");
			// Create a multi part message
			Multipart multipart = new MimeMultipart();
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
	//		String []filename = {"D:\\WorkSpace_Naba\\HReck\\test-output\\emailable-report.html",TakeScteenshot.folderName()};
			String filename = "D:\\WorkSpace_Naba\\HReck\\test-output\\emailable-report.html";
	//		for(String files: filename) {
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
	//		}
			multipart.addBodyPart(messageBodyPart);
			// Send the complete message parts
			message.setContent(multipart);
			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
