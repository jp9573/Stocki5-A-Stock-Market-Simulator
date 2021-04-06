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
<script type="text/javascript">
	setTimeout(function(){ location.reload(); }, 40000);
	
	function getStockId(stockid,symbol,segment){
		$("#buystocksymbol").val(symbol);
		$("#buystockseg").val(segment);
		$("#buyModal").modal();
		$("#buystockid").val(stockid);
	}

	function getStockIdBuyPrice(stockid,symbol,segment){
		$("#setbuystocksymbol").val(symbol);
		$("#setbuystockseg").val(segment);
		$("#buyPriceModal").modal();
		$("#setbuystockid").val(stockid);
	}
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container" style="margin-top: 70px; margin-bottom: 40px;">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<proc:if test="${not empty error}">
					<div class="alert alert-danger" role="alert" ${not empty error ? '' : 'hidden="hidden"' }>${error}</div>
				</proc:if>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-6"
				style="background-color: #f6f6f6; padding: 20px; border: 5px solid #ffffff; height: calc(100vh - 110px); overflow-y: scroll; ">
				<div class="form-group">
					<input class="form-control" id="searchtext" name="searchtext"
						type="text" placeholder="Search Stocks" />
				</div>
				<c:forEach items="${stocks}" var="stock" varStatus="loop">
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
											<!-- <input class="form-control input-sm" id="buyprice" name="buyprice" type="number" placeholder="Buy Price" /> -->
											<button type="submit" style="margin-left: 5px;" class="btn btn-primary my-1 btn-sm" onclick="getStockIdBuyPrice('${stock.stockId}','${stock.symbol}','${stock.segment}')">Set Buy Price</button>
										</div>
										<div style="display: inline-flex; padding: 4px;">
											<button type="submit" class="btn btn-primary my-1 btn-sm" onclick="getStockId('${stock.stockId}','${stock.symbol}','${stock.segment}')" >Buy</button>
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
						<h3 style="text-align: center; text-transform: uppercase; margin-top: 0px; color: #5cb85c; font-weight: bold;">Top 5 Gainers</h3>
						<%-- <c:forEach items="${gainers}" var="gainers">
							<div style="display: flex; align-items: center; justify-content: space-between;">
								<div><span style="font-size: 14px; color: #337ab7; font-weight: bold">Symbol&nbsp;</span><span class="label label-info"><c:out value="${gainers.symbol}" /></span></div>
								<div><span style="font-size: 14px; color: #337ab7; font-weight: bold">Price&nbsp;</span><span class="label label-info"><c:out value="${gainers.price}" /></span></div>
								<span class="${gainers.percentIncreaseDecrease > 0 ? 'label label-success' : 'label label-danger'}" ><c:out value="${gainers.percentIncreaseDecrease}" />%&nbsp;<span class="${gainers.percentIncreaseDecrease > 0 ? 'glyphicon glyphicon-arrow-up' : 'glyphicon glyphicon-arrow-down'}"></span></span>
							</div>
						</c:forEach> --%>
						<table class="table" style="margin-bottom: 0px;">
						<thead>
							<tr>
								<th>SYMBOL</th>
								<th>SEGMENT</th>
								<th>CLOSING PRICE</th>
								<th>PRICE</th>
								<th>PERCENT INCREASE</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${gainers}" var="gainers">
								<tr>
									<td><c:out value="${gainers.symbol}" /></td>
									<td><c:out value="${gainers.segment}" /></td>
									<td><c:out value="${gainers.previousClose}" /></td>
									<td><c:out value="${gainers.price}" /></td>
									<td style="text-align: right;"><span class="${gainers.percentIncreaseDecrease > 0 ? 'label label-success' : 'label label-danger'}" ><c:out value="${gainers.percentIncreaseDecrease}" />%&nbsp;<span class="${gainers.percentIncreaseDecrease > 0 ? 'glyphicon glyphicon-arrow-up' : 'glyphicon glyphicon-arrow-down'}"></span></span></td>
								</tr>
							</c:forEach>
						</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 col-md-12 col-lg-12"
						style="background-color: #f6f6f6; padding: 20px; border: 5px solid #ffffff;">
						<h3 style="text-align: center; text-transform: uppercase; margin-top: 0px; color: #d9534f; font-weight: bold;">Top 5 Losers</h3>
						<%-- <c:forEach items="${losers}" var="losers">
							<div style="display: flex; align-items: center; justify-content: space-between;">
								<div><span style="font-size: 14px; color: #337ab7; font-weight: bold">Symbol&nbsp;</span><span class="label label-info"><c:out value="${losers.symbol}" /></span></div>
								<div><span style="font-size: 14px; color: #337ab7; font-weight: bold">Price&nbsp;</span><span class="label label-info"><c:out value="${losers.price}" /></span></div>
								<span class="${losers.percentIncreaseDecrease > 0 ? 'label label-success' : 'label label-danger'}" ><c:out value="${losers.percentIncreaseDecrease}" />%&nbsp;<span class="${losers.percentIncreaseDecrease > 0 ? 'glyphicon glyphicon-arrow-up' : 'glyphicon glyphicon-arrow-down'}"></span></span>
							</div>
						</c:forEach> --%>
						<table class="table" style="margin-bottom: 0px;">
						<thead>
							<tr>
								<th>SYMBOL</th>
								<th>SEGMENT</th>
								<th>CLOSING PRICE</th>
								<th>PRICE</th>
								<th>PERCENT INCREASE</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${losers}" var="losers">
								<tr>
									<td><c:out value="${losers.symbol}" /></td>
									<td><c:out value="${losers.segment}" /></td>
									<td><c:out value="${losers.previousClose}" /></td>
									<td><c:out value="${losers.price}" /></td>
									<td style="text-align: right;"><span class="${losers.percentIncreaseDecrease > 0 ? 'label label-success' : 'label label-danger'}" ><c:out value="${losers.percentIncreaseDecrease}" />%&nbsp;<span class="${losers.percentIncreaseDecrease > 0 ? 'glyphicon glyphicon-arrow-up' : 'glyphicon glyphicon-arrow-down'}"></span></span></td>
								</tr>
							</c:forEach>
						</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
	<div class="modal fade" id="buyModal" role="dialog">
	   <div class="modal-dialog">
	   
	     <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Buy Stock</h4>
	      </div>
	      <div class="modal-body">
			<form class="profile-form" action="<c:url value='buystock' />" method='POST'>
				<input style="display: none" class="form-control" id="buystockid" name="buystockid" type="text" readonly/>
				<div class="input-group">
				  <span class="input-group-addon">Symbol</span>
				  <input class="form-control" id="buystocksymbol" name="buystocksymbol" type="text" readonly/>
				</div>
				<br>
				<div class="input-group">
				  <span class="input-group-addon">Segment</span>
				  <input class="form-control" id="buystockseg" name="buystockseg" type="text" readonly/>
				</div>
				<br>
				<div class="input-group">
				  <span class="input-group-addon">Quantity</span>
				  <input class="form-control" min="1" max="5000" id="quantity" name="quantity" type="number"/>
				</div>
				<br>
				<div class="input-group" style="display: flex; justify-content: center;">
					<button type="submit" class="btn btn-primary">Buy Stock</button>
				</div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>

	  </div>
	</div>
	<div class="modal fade" id="buyPriceModal" role="dialog">
	   <div class="modal-dialog">

	     <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Set Buy Price</h4>
	      </div>
	      <div class="modal-body">
			<form class="profile-form" action="<c:url value='setbuystock' />" method='POST'>
				<input style="display: none" class="form-control" id="setbuystockid" name="setbuystockid" type="text" readonly/>
				<div class="input-group">
				  <span class="input-group-addon">Symbol</span>
				  <input class="form-control" id="setbuystocksymbol" name="setbuystocksymbol" type="text" readonly/>
				</div>
				<br>
				<div class="input-group">
				  <span class="input-group-addon">Segment</span>
				  <input class="form-control" id="setbuystockseg" name="setbuystockseg" type="text" readonly/>
				</div>
				<br>
				<div class="input-group">
				  <span class="input-group-addon">Quantity</span>
				  <input class="form-control" min="1" max="5000" id="setquantity" name="setquantity" type="number"/>
				</div>
				<br>
				<div class="input-group">
				  <span class="input-group-addon">Buy Price</span>
				  <input class="form-control" id="setbuyprice" name="setbuyprice" type="number"/>
				</div>
				<br>
				<div class="input-group" style="display: flex; justify-content: center;">
					<button type="submit" class="btn btn-primary">Set Buy Price</button>
				</div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>

	  </div>
	</div>
</body>
</html>