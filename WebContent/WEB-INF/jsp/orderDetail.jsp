<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<spring:url value="/order.list" var="orderListAll" htmlEscape="true"/>
<spring:url value="/order.list?groupBy=customerName" var="orderListAllGroupByName" htmlEscape="true"/> 
<spring:url value="/" var="menuAll" htmlEscape="true"/> 	

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/bootstrap.min.css" />" />
<title><spring:message code='product.shop.name'/></title>
</head>
<body>
	
	<spring:url value="/order.list" var="backToOrderList" htmlEscape="true"/> 	
	<spring:url value="/order.detail?id=" var="orderDetail" htmlEscape="true"/>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code="order.list"/></h1>				
			</div>										
		</div>
	</section>
	
	<section class="container">
		<div class="row">
			<div class="col-md-12">
				
				<span style="float:right">
					<a href="?language=en" >
						<img src="<c:url value="/resources/images/UK.png" />" />							
					</a>
					|
					<a href="?language=tr">
						<img src="<c:url value="/resources/images/Turkey.png" />" />
					</a>
					|
					<a href="${menuAll}">
						<img src="<c:url value="/resources/images/icon_home.png" />" />
					</a>						
				</span>
				
				
				<legend><spring:message code="orderItem"/></legend>						
				
				<div class="table-responsive">
					<table id="example" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th><spring:message code="orderItem.id"/></th>	
								<th><spring:message code="orderItem.name"/></th>
								<th><spring:message code="orderItem.date"/></th>
								<th><spring:message code="orderItem.price"/></th>
								
							</tr>
						</thead>
						
						
						<tbody>
							<c:forEach items="${orderItemList}" var="orderItem">
								<tr>
									<td><c:out value="${orderItem.id}" /></td>
									<td><c:out value="${orderItem.product.name}" /></td>
									<td><fmt:formatDate type="both" dateStyle="long" timeStyle="long"  value="${orderItem.orderItemDate}" /></td>
									<td><c:out value="${orderItem.product.price}" /></td>
								</tr>
							</c:forEach>
						</tbody>
						
						
						<tfoot>
							<tr>
						    	<td><spring:message code="order.total"/></td>
						      	<td></td>
						      	<td></td>
						      	<td>${totalPrice} </td>
						    </tr>
						</tfoot> 
						
					</table>
				</div>
				<a href="${backToOrderList}" class="btn btn-default btn-sm"> 
					<span class="glyphicon-hand-left glyphicon"></span> 
					<spring:message code='order.menu.back'/> 
				</a>	
			</div>
			
		</div>	
			
	</section>
</body>
</html>
