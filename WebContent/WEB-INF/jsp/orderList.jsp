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
				
				
				<legend><spring:message code="order.list"/></legend>						
				
				<div class="table-responsive">
					<table id="example" class="table table-striped table-bordered">
					
						<thead>
							<tr>
								<th><spring:message code="order.id"/></th>				
								<th><spring:message code="order.date"/></th>
								<th><spring:message code="order.price"/></th>
								<th><spring:message code="order.detail"/></th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${customerOrders}" var="order">
								<tr>
									<td><c:out value="${order.id}" /></td>
									<td><fmt:formatDate type="both" dateStyle="long" timeStyle="long"  value="${order.orderDate}" /></td>
									<td><fmt:formatNumber pattern="#0.00" value="${order.price}"/></td>
									<td>
										<a href="${orderDetail}${order.id}">
											<c:out value="Detail" />
										</a>	
									</td>																															  
								</tr>
							</c:forEach>
						</tbody>
						
						<tfoot>
							<tr>
						    	<td><spring:message code="order.total"/></td>
						      	<td></td>
						      	<td>${ordersTotal} </td>
						    </tr>
						</tfoot> 
						
					</table>
				</div>
					
			</div>
		</div>	
			
	</section>
</body>
</html>
