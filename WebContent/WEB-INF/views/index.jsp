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
	<div class="login-container">
		<form class="login-form">
		<div class="login-title">STOCKI5</div>
			<input class="login-input" id="username" name="username" type="text" placeholder="User Code"/> <input
				class="login-input login-last-input" id="password" name="password" type="password" placeholder="Password"/> <input
				class="login-button" value="Log In" onclick="login()" />
				<a class="signup-link" href="signup">
				<span>Sign Up</span>
				</a>
		</form>
	</div>
</body>
</html>