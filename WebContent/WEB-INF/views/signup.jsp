<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Stocki5</title>
<%--    <link rel="stylesheet" href="resources/css/main.css">--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container", style="border-style:solid ; padding: 5px ; margin-top: 5px">
    <div class="row">
        <div class="col-sm-2"></div>

        <div class="col-sm-8">

            <form name="login" action="<c:url value='signupuser' />" method='POST'>
				<h1 class="text-center">STOCKI5</h1>
				<proc:if test="${not empty error}">
					<div class="alert alert-danger"
						 role="alert" ${not empty error ? '' : 'hidden="hidden"' }>${error}</div>
				</proc:if>
                <div class="form-group">
                    <input class="form-control" id="firstName" name="firstName" type="text" placeholder="First Name"
                           value="${firstName}"/>
                </div>

                <div class="form-group">
                    <input class="form-control" id="lastName" name="lastName" type="text" placeholder="Last Name"
                           value="${lastName}"/>
                </div>

                <div class="form-group">
                    <input class="form-control" id="emailId" name="emailId" type="text" placeholder="Email Id"
                           value="${emailId}"/>
                </div>

                <div class="form-group">
                    <input class="form-control" id="contactNumber" name="contactNumber" type="text"
                           placeholder="Contact No. with Country Code" value="${contactNumber}"/>
                </div>

                <div class="form-group">
                    <input class="form-control" id="address" name="address" type="text" placeholder="Address"
                           value="${address}"/>
                </div>

                <div class="form-group">
                    <input class="form-control" id="province" name="province" type="text" placeholder="State/Province"
                           value="${province}"/>
                </div>

                <div class="form-group">
                    <select class="form-control" id="country" name="country">
                        <option value="India" selected>India</option>
                        <option value="Canada">Canada</option>
                        <option value="USA">USA</option>
                        <option value="Italy">Italy</option>
                    </select>
                </div>

                <div class="form-group">
                    <input class="form-control" id="password" name="password" type="password" placeholder="password"
                           value="${password}"/>
                </div>

                <div class="form-group">
                    <input class="form-control" id="confirmPassword" name="confirmPassword" type="password"
                           placeholder="Confirm Password" value="${confirmPassword}"/>
                </div>

                <div class="form-group">
                    <input class="form-control" id="dob" name="dob" type="date" placeholder="Date of Birth"/>
                </div>

                <div class="form-group">
                    <label class="form-control" style="text-align: center; border: none;">
                        <input type="radio" id="male" name="gender" value="male" checked>
                        Male
                        <input type="radio" id="female" name="gender" value="female">
                        Female
                        <input type="radio" id="other" name="gender" value="other">
                        Other
                    </label>
                </div>

                <div class="form-group">
                    <!-- <input class="signup-input" id="gender" name="gender" type="text" placeholder="Gender"/>  -->
                    <button type="submit" class="btn btn-primary my-1">Sign up</button>
				</div>

            </form>
        </div>

        <div class="col-sm-2"></div>
    </div>
</div>
</body>
</html>