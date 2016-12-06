<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/bootstrap.min.css" />" />
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<title><spring:message code='product.shop.name'/></title>
</head>
<body>

	<c:set var="totalPrice" value="${shoppingCart.totalPrice}"/>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code='product.shop.name'/></h1>
			</div>
		</div>
	</section>
		
	<spring:url value="/product.detail" var="menuBack" htmlEscape="true"/> 
	<spring:url value="/" var="menuAllURL" htmlEscape="true"/> 
	<spring:url value="/order.payment" var="productPayment" htmlEscape="true"/> 		
		
	<section class="container">
		<div class="row">	
		
		<c:forEach items="${productSC}" var="productSCEntry" varStatus="count">
			<div class="row">
				<div class="col-md-6">
					<div class="col-md-12">	
						<c:forEach items="${productSCEntry.value}" var="productOrderID">
							<div class="thumbnail">
								<div class="caption">																				
									
									<h4>
										<p>
											<span>${productSCEntry.key.name}</span>
										</p>
									</h4>
									
									<c:forEach items="${flavourSC[productOrderID]}" var="flavourEntry">
										<p>
										  ${flavourEntry.key.name} 									  
										  <fmt:formatNumber pattern="#0.00" value="${flavourEntry.key.price}"/>									  									  								  									  									 
										  X ${flavourEntry.value}
										</p>
									</c:forEach>
									
									<h4>
										<spring:message code="product.price.total.by.productId"/> : 						
										<span class="label label-info">									
											<fmt:formatNumber pattern="#0.00" value="${totalPrices[productOrderID]}"/>
										</span>
									</h4>				    			
									
									<a href="${menuBack}?productId=${productSCEntry.key.id}" class="btn btn-default btn-md"> 
										<span class="glyphicon-info-sign glyphicon"></span> 
										<spring:message code='product.menu.back.to.detail'/> 
									</a>
																																	      									
								</div>
							</div>	
						</c:forEach>					
					</div>									
				</div>										
			</div>															
		</c:forEach>
		
			<div class="row">
				<div class="col-md-6">
					<div class="col-md-12">	
						<div class="thumbnail">
							<div class="caption">
											
							<h4>
								<spring:message code='product.price.total.all'/> : 
								<span class="label label-info">																
									<fmt:formatNumber pattern="#0.00" value="${totalPrice}"/>
								</span>																
							</h4>																	
							
							
							<c:if test="${totalPrice !=0 }">
															
								<form action="${productPayment}" method="POST">							  							  
								  	<input type="submit" value="<spring:message code='product.confirm'/> " class="btn btn-success">
								  	<input type="hidden" value="${totalPrice}">						  
								</form>		
								
							</c:if>
							<a href="${menuAllURL}" class="btn btn-default btn-md"> 
								<span class="glyphicon-hand-left glyphicon"></span> 
								<spring:message code='product.menu.back'/> 
							</a>
																																				      								
							</div>
						</div>						
					</div>
												
				</div>
			</div>	
		</div>		
	</section>
	
	 
	
</body>
</html>
