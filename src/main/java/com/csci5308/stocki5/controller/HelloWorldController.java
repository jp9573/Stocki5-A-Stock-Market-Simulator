package com.csci5308.stocki5.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.csci5308.stocki5.config.Stocki5DbConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csci5308.stocki5.model.HelloWorld;

@Controller
public class HelloWorldController {

	@Autowired
	Stocki5DbConnection stocki5DbConnection;

	@RequestMapping("/helloworld")
	public String handler(Model model) throws SQLException {
		HelloWorld helloWorld = new HelloWorld();

		Connection c = stocki5DbConnection.createConnection();
		Statement statement = c.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM demo WHERE id=1");
		while (resultSet.next()) {
			System.out.println(resultSet.getInt("id"));
			System.out.println(resultSet.getString("name"));
		}
		stocki5DbConnection.closeConnection(c);

		helloWorld.setMessage("Hello World Example Using Spring MVC 5!!!");
		helloWorld.setDateTime(LocalDateTime.now().toString());
		model.addAttribute("helloWorld", helloWorld);

		return "helloworld";

	}
}