<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stocki5</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h2>Stocks</h2>
	<div class="container" style="margin-top: 70px; margin-bottom: 40px;">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-6"
				style="background-color: #f6f6f6; padding: 20px; border: 5px solid #ffffff;">
				<c:forEach items="${stocks}" var="stock">
					<div>
						<span>Symbol : <c:out value="${stock.symbol}" /></span> <span>Segment
							: <c:out value="${stock.segment}" />
						</span> <span>Current PRice : <c:out value="${stock.price}" /></span>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>