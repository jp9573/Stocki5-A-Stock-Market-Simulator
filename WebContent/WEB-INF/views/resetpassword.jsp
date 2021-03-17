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
<div class="container" style="display: flex; align-items: center !important; justify-content: center; height: 100vh;">
    <div class="row" style="text-align: center; -webkit-box-shadow: 0 0 2px 2px rgba(0, 0, 0, .2); box-shadow: 0 0 2px 2px rgba(0, 0, 0, .2); padding: 40px; border-radius: 5px;">
        <div class="col-sm-12 col-md-12 col-lg-12">
            <h1 style="color: #337ab7; margin-bottom: 20px;">STOCKI5 RESET PASSWORD</h1>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-12">
            <form name="resetpassword" action="<c:url value='resetpassword' />" method='POST'>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input id="password" name="password" type="password" placeholder="password" value="${password}" class="form-control"/>
                </div>
                <br>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input id="confirmPassword" name="confirmPassword" type="password" placeholder="Confirm Password" value="${confirmPassword}" class="form-control"/>
                </div>
                <br>
                <div class="input-group" style="display: flex; justify-content: center;">
                    <button type="submit" class="btn btn-primary btn-lg">Reset Password</button>
                </div>
                <br>
                <h5>Go to&nbsp;<a href="login">Log&nbsp;In</a></h5>
                <input type="hidden" id="userCode" name="userCode" type="text" placeholder="User Code" value="${userCode}" class="form-control input-lg">
                <proc:if test="${not empty error}">
                    <div class="alert alert-danger" ${not empty error ? '' : 'hidden="hidden"' }>${error}</div>
                </proc:if>
                <proc:if test="${not empty success}">
                    <div class="alert alert-success" ${not empty success ? '' : 'hidden="hidden"' }>${success}</div>
                </proc:if>
            </form>
        </div>
    </div>
</div>
</body>
</html>
