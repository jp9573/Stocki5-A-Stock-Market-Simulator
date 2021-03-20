<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Stocki5</title>
     <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body style="overflow-y: scroll;">
<div class="container" style="margin:40px; width:calc(100% - 80px);">
	<div class="row" style="text-align: center; -webkit-box-shadow: 0 0 2px 2px rgba(0, 0, 0, .2); box-shadow: 0 0 2px 2px rgba(0, 0, 0, .2); padding: 20px; border-radius: 5px;">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="row">
				<h1 style="color: #337ab7; margin-bottom: 20px;">STOCKI5 SIGN UP</h1>
			</div>
		</div>
		<form class="col-sm-12 col-md-12 col-lg-12" name="signup" action="<c:url value='signupuser' />" method='POST'>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group" style="width: 100%;">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-font"></i></span>
				      <input id="firstName" name="firstName" type="text" placeholder="First Name" value="${firstName}" class="form-control"/>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group" style="width: 100%;">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-font"></i></span>
				      <input id="lastName" name="lastName" type="text" placeholder="Last Name" value="${lastName}" class="form-control"/>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group" style="width: 100%;">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
				      <input id="emailId" name="emailId" type="text" placeholder="Email Id" value="${emailId}" class="form-control"/>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group" style="width: 100%;">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
				      <input id="contactNumber" name="contactNumber" type="text" placeholder="Contact No. with Country Code" value="${contactNumber}" class="form-control"/>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group" style="width: 100%;">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-map-marker"></i></span>
				      <input id="address" name="address" type="text" placeholder="Address" value="${address}" class="form-control"/>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group" style="width: 100%;">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-map-marker"></i></span>
				      <input id="province" name="province" type="text" placeholder="State/Province" value="${province}" class="form-control"/>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group" style="width: 100%;">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
				      <input id="password" name="password" type="password" placeholder="password" value="${password}" class="form-control"/>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group" style="width: 100%;">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
				      <input id="confirmPassword" name="confirmPassword" type="password" placeholder="Confirm Password" value="${confirmPassword}" class="form-control"/>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group" style="width: 100%;">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-map-marker"></i></span>
				      <select class="form-control" id="country" name="country">
	                        <option value="India" ${country == "India" ? 'selected="selected"' : '' } selected>India</option>
	                        <option value="Canada" ${country == "Canada" ? 'selected="selected"' : '' }>Canada</option>
	                        <option value="USA" ${country == "USA" ? 'selected="selected"' : '' }>USA</option>
	                        <option value="Italy" ${country == "Italy" ? 'selected="selected"' : '' }>Italy</option>
	                    </select>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group" style="width: 100%;">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
				      <input id="dob" name="dob" type="date" placeholder="Date of Birth" class="form-control" value="${dob}"/>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group " style="display: flex; justify-content: center; width: 100%;">
						<label class="radio-inline">
						  <input type="radio" id="male" name="gender" value="male" ${gender == "male" ? 'checked="checked"' : '' } checked>Male
						</label>
						<label class="radio-inline">
						  <input type="radio" id="female" name="gender" value="female" ${gender == "female" ? 'checked="checked"' : '' }>Female
						</label>
						<label class="radio-inline">
						  <input type="radio" id="other" name="gender" value="other" ${gender == "other" ? 'checked="checked"' : '' }>Other
						</label>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<div class="input-group" style="display: flex; justify-content: center;">
				    	<button type="submit" class="btn btn-primary">Sign Up</button>
				    </div>
				</div>
				<div class="form-group col-sm-12 col-md-12 col-lg-12">
					<h5>Go to&nbsp;<a href="login">Log&nbsp;In</a></h5>
				</div>
			    <div class="form-group col-sm-12 col-md-12 col-lg-12">
				    <proc:if test="${not empty error}">
					    <div class="alert alert-danger" ${not empty error ? '' : 'hidden="hidden"' }>${error}</div>
				    </proc:if>
			    </div>
		</form>
	</div>
</div>
</body>
</html>