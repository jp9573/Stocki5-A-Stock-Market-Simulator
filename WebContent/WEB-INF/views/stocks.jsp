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
			<div class="col-sm-12 col-md-12 col-lg-6"
				style="background-color: #f6f6f6; padding: 20px; border: 5px solid #ffffff; height: calc(100vh - 110px); overflow-y: scroll; ">
				<div class="form-group">
					<input class="form-control" id="searchtext" name="searchtext"
						type="text" placeholder="Search Stocks" />
				</div>
				<c:forEach items="${stocks}" var="stock">
					<div style="margin-bottom: 15px; border: 1px solid #337ab7; border-radius: 4px; padding: 8px;">
						<table style="width:100%; table-layout: fixed; font-size: 16px; border-collapse: separate; border-spacing: 5px;">
							<tr>
								<td colspan="2"><span style="font-size: 14px; color: #337ab7; font-weight: bold">Symbol&nbsp;</span><span class="label label-info"><c:out value="${stock.symbol}" /></span></td>
								<td colspan="2" style="text-align: right;"><span style="font-size: 14px; color: #337ab7; font-weight: bold">Price&nbsp;</span><span class="label label-info"><c:out value="${stock.price}" /></span></td>
							</tr>
							<tr>
								<td colspan="2"><span style="font-size: 14px; color: #337ab7; font-weight: bold">Segment&nbsp;</span><span class="label label-info"><c:out value="${stock.segment}" /></span></td>
								<td colspan="2" style="text-align: right;"><span class="${stock.percentIncreaseDecrease > 0 ? 'label label-success' : 'label label-danger'}" ><c:out value="${stock.percentIncreaseDecrease}" />%&nbsp;<span class="${stock.percentIncreaseDecrease > 0 ? 'glyphicon glyphicon-arrow-up' : 'glyphicon glyphicon-arrow-down'}"></span></span></td>
							</tr>
							<tr>
							<td colspan="4">
								<div style="display: flex; justify-content: space-around; align-items: center; flex-wrap: wrap;">
									<div style="display: inline-flex; padding: 4px;"><span style="font-size: 14px; color: #337ab7; font-weight: bold">Open&nbsp;</span><span class="label label-info"><c:out value="${stock.open}" /></span></div>
									<div style="display: inline-flex; padding: 4px;"><span style="font-size: 14px; color: #337ab7; font-weight: bold">Day&nbsp;High&nbsp;</span><span class="label label-info"><c:out value="${stock.high}" /></span></div>
									<div style="display: inline-flex; padding: 4px;"><span style="font-size: 14px; color: #337ab7; font-weight: bold">Day&nbsp;Low&nbsp;</span><span class="label label-info"><c:out value="${stock.low}" /></span></div>
									<div style="display: inline-flex; padding: 4px;"><span style="font-size: 14px; color: #337ab7; font-weight: bold">Closing&nbsp;</span><span class="label label-info"><c:out value="${stock.previousClose}" /></span></div>
								</div>
							</td>
							</tr>
							<tr>
								<td colspan="4">
									<div style="display: flex; justify-content: space-around; align-items: center; flex-wrap: wrap;">
										<div style="display: inline-flex; padding: 4px;">
											<input class="form-control input-sm" id="buyprice" name="buyprice" type="number" placeholder="Buy Price" />
											<button type="submit" style="margin-left: 5px;" class="btn btn-primary my-1 btn-sm">Set Buy Price</button>
										</div>
										<div style="display: inline-flex; padding: 4px;">
											<button type="submit" class="btn btn-primary my-1 btn-sm">Buy</button>
											<button type="submit" style="margin-left: 5px;" class="btn btn-primary my-1 btn-sm">Sell</button>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</c:forEach>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-6">
				<div class="row">
					<div class="col-sm-12 col-md-12 col-lg-12"
						style="background-color: #f6f6f6; padding: 20px; border: 5px solid #ffffff;">
						<h3>Top 5 Gainers</h3>
						<c:forEach items="${gainers}" var="gainers">
							<div style="display: flex; align-items: center; justify-content: space-between;">
								<div><span style="font-size: 14px; color: #337ab7; font-weight: bold">Symbol&nbsp;</span><span class="label label-info"><c:out value="${gainers.symbol}" /></span></div>
								<div><span style="font-size: 14px; color: #337ab7; font-weight: bold">Price&nbsp;</span><span class="label label-info"><c:out value="${gainers.price}" /></span></div>
								<span class="${gainers.percentIncreaseDecrease > 0 ? 'label label-success' : 'label label-danger'}" ><c:out value="${gainers.percentIncreaseDecrease}" />%&nbsp;<span class="${gainers.percentIncreaseDecrease > 0 ? 'glyphicon glyphicon-arrow-up' : 'glyphicon glyphicon-arrow-down'}"></span></span>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 col-md-12 col-lg-12"
						style="background-color: #f6f6f6; padding: 20px; border: 5px solid #ffffff;">
						<h3>Top 5 Losers</h3>
						<c:forEach items="${losers}" var="losers">
							<div style="display: flex; align-items: center; justify-content: space-between;">
								<div><span style="font-size: 14px; color: #337ab7; font-weight: bold">Symbol&nbsp;</span><span class="label label-info"><c:out value="${losers.symbol}" /></span></div>
								<div><span style="font-size: 14px; color: #337ab7; font-weight: bold">Price&nbsp;</span><span class="label label-info"><c:out value="${losers.price}" /></span></div>
								<span class="${losers.percentIncreaseDecrease > 0 ? 'label label-success' : 'label label-danger'}" ><c:out value="${losers.percentIncreaseDecrease}" />%&nbsp;<span class="${losers.percentIncreaseDecrease > 0 ? 'glyphicon glyphicon-arrow-up' : 'glyphicon glyphicon-arrow-down'}"></span></span>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>