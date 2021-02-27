<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Stocki5</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container" style="border-style:solid ; padding: 5px ; margin-top: 5px">
    <div class="row">
        <div class="col-sm-4"></div>

        <div class="col-sm-4">
            <form name="login" action="<c:url value='/login' />" method='POST'>
                <h1 class="text-center">STOCKI5</h1>
                <proc:if test="${not empty error}">
                    <div class="alert alert-danger"
                         role="alert" ${not empty error ? '' : 'hidden="hidden"' }>${error}</div>
                </proc:if>
                <div class="form-group">
                    <input class="form-group" id="username" name="username" type="text" placeholder="User Code"/>
                </div>
                <div class="form-group">
                    <input class="form-group" id="password" name="password" type="password" placeholder="Password"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary my-1">Log In</button>
                </div>
                <div class="form-group">
                    <a class="signup-link" href="signup">
                        <span>Sign Up</span>
                    </a>
                </div>
				<div class="form-group">
					<a class="signup-link" href="forgotuser">
						<span>Forgot Usercode?</span>
					</a>
				</div>
            </form>
        </div>

        <div class="col-sm-4"></div>
    </div>
</div>
</body>
</html>