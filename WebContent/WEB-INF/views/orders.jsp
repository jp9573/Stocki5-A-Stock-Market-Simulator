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
	<div class="container" style="margin-top: 70px; margin-bottom: 40px;">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<table class="table">
					<thead>
						<tr>
							<th>STOCK</th>
							<th>SEGMENT</th>
							<th>QUANTITY</th>
							<th>STOCK PRICE</th>
							<th>TOTAL ORDER AMOUNT</th>
							<th>ORDER TYPE</th>
							<th>STATUS</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="order" varStatus="loop">
							<tr class="${order.status == 'EXECUTED' ? 'success' : 'danger'}">
								<td><c:out value="${order.symbol}" /></td>
								<td><c:out value="${order.segment}" /></td>
								<td><c:out value="${order.quantity}" /></td>
								<td><c:out value="${String.valueOf(order.getBuySell()) == 'BUY' ? order.buyPrice : order.sellPrice}" /></td>
								<td><c:out value="${String.valueOf(order.getBuySell()) == 'BUY' ? order.totalBuyPrice : order.totalSellPrice}" /></td>
								<td><c:out value="${String.valueOf(order.buySell)}" /></td>
								<td><c:out value="${order.status}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>