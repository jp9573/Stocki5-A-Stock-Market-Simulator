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
                    <th>BUY PRICE</th>
                    <th>TOTAL BUY AMOUNT</th>
                    <th>CURRENT VALUE</th>
                    <th>PROFIT/LOSS</th>
                    <th>ACTION</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order" varStatus="loop">
                    <tr>
                        <td><c:out value="${order.symbol}" /></td>
                        <td><c:out value="${order.segment}" /></td>
                        <td><c:out value="${order.quantity}" /></td>
                        <td><c:out value="${order.buyPrice}" /></td>
                        <td><c:out value="${order.totalBuyPrice}" /></td>
                        <td><c:out value="${order.totalSellPrice}" /></td>
                        <td><c:out value="${order.profitLoss}" /></td>
                        <td>
                            <form action="<c:url value='sellstock' />" method='POST'>
                                <input name="sellstockid" value="${order.stockId}" hidden>
                                <input name="quantity" value="${order.quantity}" hidden>
                                <input name="tradeBuyNumber" value="${order.tradeNumber}" hidden>
                                <button type="submit" class="btn btn-primary">Sell Stock</button>
                            </form>
                        </td>
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