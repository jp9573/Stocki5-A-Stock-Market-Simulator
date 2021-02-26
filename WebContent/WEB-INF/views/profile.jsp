<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Stocki5</title>
    <link rel="stylesheet" href="resources/css/main.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>

        <div class="col-sm-8">

            <%@ include file="header.jsp" %>

            <form class="profile-form" action="<c:url value='updateProfile' />" method='POST'>
                <h1 class="text-center">User Profile</h1>

                <proc:if test="${not empty error}">
                    <div class="alert alert-danger"
                         role="alert" ${not empty error ? '' : 'hidden="hidden"' }>${error}</div>
                </proc:if>
                <proc:if test="${not empty success}">
                    <div class="alert alert-success"
                         role="alert"  ${not empty success ? '' : 'hidden="hidden"' }>${success}</div>
                </proc:if>

                <div class="form-group">
                    <input class="form-control" name="userCode" type="text" value="${userCode}" readonly/>
                </div>

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

                    <input class="form-control" id="contactNumber" name="contactNo" type="text"
                           placeholder="Contact No. with Country Code" value="${contactNo}"/>
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
                        <option value="India" ${country == "India" ? 'selected="selected"' : '' }>India</option>
                        <option value="Canada" ${country == "Canada" ? 'selected="selected"' : '' }>Canada</option>
                        <option value="USA" ${country == "USA" ? 'selected="selected"' : '' }>USA</option>
                        <option value="Italy" ${country == "Italy" ? 'selected="selected"' : '' }>Italy</option>
                    </select>
                </div>

                <div class="form-group">
                    <input class="form-control" id="dob" name="dateOfBirth" type="date" placeholder="Date of Birth"
                           value="${dateOfBirth}"/>
                </div>

                <div class="form-group">
                    <div class="custom-control custom-radio">
                        <input class="custom-control-input" type="radio" id="male" name="gender"
                               value="male" ${gender == "male" ? 'checked="checked"' : '' } />
                        <label class="custom-control-label" for="male">Male</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="female" name="gender" value="female"
                               class="custom-control-input"  ${gender == "female" ? 'checked="checked"' : '' }/>
                        <label class="custom-control-label" for="female">Female</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="other" name="gender" value="other"
                               class="custom-control-input"  ${gender == "other" ? 'checked="checked"' : '' }/>
                        <label class="custom-control-label" for="other">Other</label>
                    </div>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="internationalStockExchange"
                           value="1"
                           id="internationalStockExchange"
                    ${internationalStockExchange == '1' ? 'checked="checked"' : '' } >
                    <label class="form-check-label" for="internationalStockExchange">
                        International StockExchange
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="internationalDerivativeExchange"
                           value="1"
                           id="internationalDerivativeExchange"
                    ${internationalDerivativeExchange == '1' ? 'checked="checked"' : '' }>
                    <label class="form-check-label" for="internationalDerivativeExchange">
                        International Derivative Exchange
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="internationalCommodityExchange"
                           value="1"
                           id="internationalCommodityExchange"
                    ${internationalCommodityExchange == '1' ? 'checked="checked"' : '' }>
                    <label class="form-check-label" for="internationalCommodityExchange">
                        International Commodity Exchange
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="foreignExchange" value="1"
                           id="foreignExchange"
                    ${foreignExchange == '1' ? 'checked="checked"' : '' }>
                    <label class="form-check-label" for="foreignExchange">
                        Foreign Exchange
                    </label>
                </div>

                <button type="submit" class="btn btn-primary my-1">Update</button>

            </form>

        </div>

        <div class="col-sm-2"></div>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>