package com.csci5308.stocki5.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class Stocki5AppEmailConfig
{
	@Value("${smtp.host}")
	private String host;

	@Value("${smtp.username}")
	private String username;

	@Value("${smtp.password}")
	private String password;

	@Value("${smtp.port}")
	private int port;

	public JavaMailSender configureJavaMail()
	{
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(host);
		javaMailSenderImpl.setUsername(username);
		javaMailSenderImpl.setPassword(password);
		javaMailSenderImpl.setPort(port);

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.ssl.trust", host);
		javaMailProperties.put("mail.smtp.starttls.enable", true);

		javaMailSenderImpl.setJavaMailProperties(javaMailProperties);
		return javaMailSenderImpl;
	}

}
