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
<%@ include file="header.jsp" %>
<div class="container" style="margin-top: 70px; margin-bottom: 40px;">
    <div class="row">
	    <div class="col-sm-12 col-md-12 col-lg-6" style="background-color: #f6f6f6; padding: 20px; border: 5px solid #ffffff;">
	    <form class="profile-form" action="<c:url value='updateProfile' />" method='POST'>
	                <h4 class="text-center" style="margin: 0px 0px 10px 0px; padding: 0px; text-transform: uppercase;">User Profile</h4>
	
	                <proc:if test="${not empty error}">
	                    <div class="alert alert-danger"
	                         role="alert" ${not empty error ? '' : 'hidden="hidden"' }>${error}</div>
	                </proc:if>
	                <proc:if test="${not empty success}">
	                    <div class="alert alert-success"
	                         role="alert"  ${not empty success ? '' : 'hidden="hidden"' }>${success}</div>
	                </proc:if>

					<input class="form-control" name="funds" type="number" value="${funds}" style="display: none"/>
	
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
					<div class="input-group" style="display: flex; justify-content: center;">
					<button type="submit" class="btn btn-primary my-1">Update</button>
					</div>
	
	            </form>
	    </div>
	    <div class="col-sm-12 col-md-12 col-lg-6">
		    <div class="row">
			    <div class="col-sm-12 col-md-12 col-lg-12" style="background-color: #f6f6f6; padding: 20px; border: 5px solid #ffffff;" >
			    <h4 class="text-center" style="margin: 0px 0px 10px 0px; padding: 0px; text-transform: uppercase;">Add Funds</h4>

				    <form name="userFunds" method='POST' action="<c:url value='resetFunds' />" method='POST'>
						<proc:if test="${not empty errorFunds}">
							<div class="alert alert-danger"
								 role="alert" ${not empty errorFunds ? '' : 'hidden="hidden"' }>${errorFunds}</div>
						</proc:if>
						<proc:if test="${not empty successFunds}">
							<div class="alert alert-success"
								 role="alert"  ${not empty successFunds ? '' : 'hidden="hidden"' }>${successFunds}</div>
						</proc:if>

						<input class="form-control" name="userCode" type="text" value="${userCode}" style="display: none"/>
					    <div class="input-group">
					      <span class="input-group-addon"><i class="glyphicon glyphicon-usd"></i></span>
					      <input class="form-control" name="funds" type="text" value=${funds} readonly/>
					    </div>
					    <br>
					    <div class="input-group" style="display: flex; justify-content: center;">
					    	<button type="submit" class="btn btn-primary">Add Funds</button>
					    </div>
					</form>
			    </div>
			    <div class="col-sm-12 col-md-12 col-lg-12" style="background-color: #f6f6f6; padding: 20px; border: 5px solid #ffffff;">
			    <h4 class="text-center" style="margin: 0px 0px 10px 0px; padding: 0px; text-transform: uppercase;">Change Password</h4>
				    <form name="login"  action="<c:url value='changePassword' />" method='POST'>
						<proc:if test="${not empty errorChangePassword}">
							<div class="alert alert-danger"
								 role="alert" ${not empty errorChangePassword ? '' : 'hidden="hidden"' }>${errorChangePassword}</div>
						</proc:if>
						<proc:if test="${not empty successChangePassword}">
							<div class="alert alert-success"
								 role="alert"  ${not empty successChangePassword ? '' : 'hidden="hidden"' }>${successChangePassword}</div>
						</proc:if>
						<input type="hidden" id="userCode" name="userCode" type="text" placeholder="User Code" value="${userCode}" class="form-control input-lg">
					    <div class="input-group">
					      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
					      <input id="password" type="password" class="form-control" name="password" placeholder="Current Password">
					    </div>
					    <br>
					    <div class="input-group">
					      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
					      <input id="newPassword" type="password" class="form-control" name="newPassword" placeholder="New Password">
					    </div>
					    <br>
					    <div class="input-group">
					      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
					      <input id="confirmPassword" type="password" class="form-control" name="confirmPassword" placeholder="Confirm New Password">
					    </div>
					    <br>
					    <div class="input-group" style="display: flex; justify-content: center;">
					    	<button type="submit" class="btn btn-primary">Change Password</button>
					    </div>
					</form>
			    </div>
		    </div>
		</div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>