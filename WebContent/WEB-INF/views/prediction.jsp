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
        <h2 class="text-center">Prediction</h2>
        <form action="<c:url value='/predict' />" method='POST'
              style="display: flex; align-items: flex-end; justify-content: center;">
            <div class="form-group" style="width: 40%; margin: 0;">
                <label for="exampleInputEmail1">Search stock to predict</label>
                <input type="text" class="form-control" name="stockName" id="exampleInputEmail1"
                       placeholder="Stock name">
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>

        <c:if test="${empty predictionList}">
            Find prediction with the stock symbol.
        </c:if>
        <c:forEach items="${predictionList}" var="predictionList">
            <div class="mt-2"
                    style="display: flex; align-items: center; justify-content: space-between; width: 50%; margin: auto;">
                <div><span style="font-size: 14px; color: #337ab7; font-weight: bold">Symbol&nbsp;</span><span
                        class="label label-info"><c:out value="${predictionList.symbol}"/></span></div>
                <div><span style="font-size: 14px; color: #337ab7; font-weight: bold">Price&nbsp;</span><span
                        class="label label-info"><c:out value="${predictionList.price}"/></span></div>
                <span class="${predictionList.percentIncreaseDecrease > 0 ? 'label label-success' : 'label label-danger'}"><c:out
                        value="${predictionList.percentIncreaseDecrease}"/>%&nbsp;<span
                        class="${predictionList.percentIncreaseDecrease > 0 ? 'glyphicon glyphicon-arrow-up' : 'glyphicon glyphicon-arrow-down'}"></span></span>
            </div>
        </c:forEach>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>