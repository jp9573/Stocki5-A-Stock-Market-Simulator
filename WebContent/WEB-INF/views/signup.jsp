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
		<form class="signup-form" name="login" action="<c:url value='signupuser' />" method='POST'>
		<div class="signup-title">STOCKI5</div>
			<input class="signup-input" id="firstName" name="firstName" type="text" placeholder="First Name" value="${firstName}"/>
			<input class="signup-input" id="lastName" name="lastName" type="text" placeholder="Last Name" value="${lastName}"/> 
			<input class="signup-input" id="emailId" name="emailId" type="text" placeholder="Email Id" value="${emailId}"/> 
			<input class="signup-input" id="contactNumber" name="contactNumber" type="text" placeholder="Contact No. with Country Code" value="${contactNumber}"/> 
			<input class="signup-input" id="address" name="address" type="text" placeholder="Address" value="${address}"/> 
			<input class="signup-input" id="province" name="province" type="text" placeholder="State/Province" value="${province}"/>
			<select class="signup-select" id="country" name="country">
			    <option value="India" selected>India</option>
			    <option value="Canada">Canada</option>
			    <option value="USA">USA</option>
			    <option value="Italy">Italy</option>
			</select>
			<input class="signup-input" id="password" name="password" type="password" placeholder="password" value="${password}"/> 
			<input class="signup-input" id="confirmPassword" name="confirmPassword" type="password" placeholder="Confirm Password" value="${confirmPassword}"/> 
			<input class="signup-input" id="dob" name="dob" type="date" placeholder="Date of Birth"/> 
			<label class="signup-input" style="text-align: center; border: none;">
				<input type="radio" id="male" name="gender" value="male" checked>
				Male
				<input type="radio" id="female" name="gender" value="female">
				Female
				<input type="radio" id="other" name="gender" value="other">
				Other
			</label>
			<!-- <input class="signup-input" id="gender" name="gender" type="text" placeholder="Gender"/>  -->
			<input class="signup-button" name="submit" type="submit" value="Sign Up" />
			<c:if test="${not empty error}"><div class="errormsg">${error}</div></c:if>
		</form>
	</div>
</body>
</html>