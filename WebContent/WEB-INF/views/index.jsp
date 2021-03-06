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
<body>
	<div class="container" style="display: flex; align-items: center !important; justify-content: center; height: calc(100vh - 30px); margin: 15px; width: calc(100% - 30px);">
		<div class="row" style="text-align: center; -webkit-box-shadow: 0 0 2px 2px rgba(0, 0, 0, .2); box-shadow: 0 0 2px 2px rgba(0, 0, 0, .2); padding: 40px; border-radius: 5px;">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<h1 style="color: #337ab7; margin-bottom: 20px;">STOCKI5 LOG IN</h1>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<form name="login" action="<c:url value='/login' />" method='POST'>
				    <div class="input-group">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
				      <input id="username" type="text" class="form-control input-lg" name="username" value="${username}" placeholder="User Code">
				    </div>
				    <br>
				    <div class="input-group">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
				      <input id="password" type="password" class="form-control input-lg" name="password" placeholder="Password">
				    </div>
				    <br>
				    <div class="input-group" style="display: flex; justify-content: center;">
				    	<button type="submit" class="btn btn-primary btn-lg">Log In</button>
				    </div>
				    <br>
				    <h5>Forgot?&nbsp;<a href="forgotuser">User&nbsp;Code</a>&nbsp;/&nbsp;<a href="#">Password</a></h5>
				    <br>
				    <h5>Create account?&nbsp;<a href="signup">Sign&nbsp;Up</a></h5>
				    <proc:if test="${not empty error}">
					    <div class="alert alert-danger" ${not empty error ? '' : 'hidden="hidden"' }>${error}</div>
				    </proc:if>
				</form>
			</div>
		</div>
	</div>
</body>
</html>