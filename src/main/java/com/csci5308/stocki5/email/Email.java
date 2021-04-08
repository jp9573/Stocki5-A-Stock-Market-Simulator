package com.csci5308.stocki5.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class Email implements IEmail
{
	private static final boolean ENABLE_SMTP_STARTTLS = true;
	private static final String SMPT_SSL_TRUST_PROPERTY = "mail.smtp.ssl.trust";
	private static final String SMTP_STARTTLS_ENABLE_PROPERTY = "mail.smtp.starttls.enable";

	private static IEmail uniqueInstance = null;

	private Email()
	{
	}

	public static IEmail instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new Email();
		}
		return uniqueInstance;
	}

	IStocki5EmailConfig stocki5EmailConfig = Stocki5EmailConfig.instance();

	private JavaMailSender configureJavaMail()
	{
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(stocki5EmailConfig.getHost());
		javaMailSenderImpl.setUsername(stocki5EmailConfig.getUsername());
		javaMailSenderImpl.setPassword(stocki5EmailConfig.getPassword());
		javaMailSenderImpl.setPort(stocki5EmailConfig.getPort());

		Properties javaMailProperties = new Properties();
		javaMailProperties.put(SMPT_SSL_TRUST_PROPERTY, stocki5EmailConfig.getHost());
		javaMailProperties.put(SMTP_STARTTLS_ENABLE_PROPERTY, ENABLE_SMTP_STARTTLS);

		javaMailSenderImpl.setJavaMailProperties(javaMailProperties);
		return javaMailSenderImpl;
	}

	@Override
	public boolean sendEmail(String toEmail, String subject, String content)
	{
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(toEmail);
		email.setText(content);
		email.setSubject(subject);

		JavaMailSender mailSender = configureJavaMail();
		mailSender.send(email);
		return true;
	}

}
