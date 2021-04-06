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
    	
        function getStockIdSellPrice(stockid,symbol,segment, buyTradeNumber, setquantity){
            $("#setsellstocksymbol").val(symbol);
            $("#setsellstockseg").val(segment);
            $("#sellPriceModal").modal();
            $("#setsellstockid").val(stockid);
            $("#setbuyTradeNumber").val(buyTradeNumber);
            $("#setquantity").val(setquantity);
        }
    </script>
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
                    <th>SELL</th>
                    <th>SET SELL PRICE</th>
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
                        <td style="text-align: center;">
                            <form action="<c:url value='sellstock' />" method='POST'>
                                <input name="sellstockid" value="${order.stockId}" hidden>
                                <input name="quantity" value="${order.quantity}" hidden>
                                <input name="tradeBuyNumber" value="${order.tradeNumber}" hidden>
                                <button type="submit" class="btn btn-primary my-1 btn-sm">Sell Stock</button>
                            </form>
                        </td>
                        <td style="text-align: center;"><button type="submit" style="margin-left: 5px;" class="btn btn-primary my-1 btn-sm" onclick="getStockIdSellPrice('${order.stockId}','${order.symbol}','${order.segment}', '${order.tradeNumber}', '${order.quantity}')">Set Sell Price</button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="sellPriceModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Set Sell Price</h4>
            </div>
            <div class="modal-body">
                <form class="profile-form" action="<c:url value='setsellstock' />" method='POST'>
                    <input style="display: none" class="form-control" id="setsellstockid" name="setsellstockid" type="text" readonly/>
                    <div class="input-group">
                        <span class="input-group-addon">Symbol</span>
                        <input class="form-control" id="setsellstocksymbol" name="setsellstocksymbol" type="text" readonly/>
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">Segment</span>
                        <input class="form-control" id="setsellstockseg" name="setsellstockseg" type="text" readonly/>
                    </div>
                    <input class="form-control" min="1" max="5000" id="setquantity" name="setquantity" type="hidden" />
                    <input class="form-control" id="setbuyTradeNumber" name="tradeBuyNumber" type="hidden" />
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">Sell Price</span>
                        <input class="form-control" id="setsellprice" name="setsellprice" type="number"/>
                    </div>
                    <br>
                    <div class="input-group" style="display: flex; justify-content: center;">
                        <button type="submit" class="btn btn-primary">Set Sell Price</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>