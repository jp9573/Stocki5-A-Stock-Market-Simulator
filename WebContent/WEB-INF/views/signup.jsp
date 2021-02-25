<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stocki5</title>
<link rel="stylesheet" href="resources/css/main.css">
</head>
<body>
	<div class="signup-container">
		<form class="signup-form">
		<div class="signup-title">STOCKI5</div>
			<input class="signup-input" id="username" name="username" type="text" placeholder="User Code"/> 
			<input class="signup-input" id="contactno" name="contactno" type="number" placeholder="Contact Number"/>
			<input class="signup-input" id="address1" name="address1" type="text" placeholder="Address Line 1"/>
			<input class="signup-input" id="address2" name="address2" type="text" placeholder="Address Line 2"/>
			<input class="signup-input" id="password" name="password" type="password" placeholder="Password"/>
			<input class="signup-input signup-last-input" id="cnfpassword" name="cnfpassword" type="password" placeholder="Confirm Password"/>
			<input class="signup-button" value="Sign Up" onclick="signup()" />
		</form>
	</div>
</body>
</html>