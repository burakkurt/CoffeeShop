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

	<spring:url value="/product.add.to.cart?productId=" var="addToCartURL" htmlEscape="true"/>
	<spring:url value="/" var="menuAllURL" htmlEscape="true"/> 		
	<spring:url value="/remove.all.orders.of.product" var="productDeleteAllCartURL" htmlEscape="true"/>
	<spring:url value="/remove.order.from.product" var="productDeleteFromCartURL" htmlEscape="true"/>
	<spring:url value="/shopping.cart" var="productGoToShoppingCart" htmlEscape="true"/>
	
	<spring:url value="/remove.flavour.from.cart" var="cartRemoveFlavour" htmlEscape="true"/>
	<spring:url value="/add.flavour.to.cart" var="cartAddFlavour" htmlEscape="true"/>
	
	<c:set var="orderIDOfProduct" scope="session" value="${shoppingCart.productSC[product]}"/>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code='product.shop.name'/></h1>
			</div>
		</div>
	</section>
	
	<section class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="col-md-12">
					<h4>
						<spring:message code='product.name'/> : 
						<span class="label label-info">${product.name}</span>
					</h4>				
					<p>
						<spring:message code='product.id'/> :
						<span>${product.id}</span>
					</p>
					
					<p>
						<spring:message code='product.category'/>: 
						<span>${product.category.name}</span>
					</p>
					
					<p>
						<spring:message code='product.description'/>: 
						<span>${product.description}</span>
					</p>
										
					<p>
						<spring:message code='product.price'/> :
						<strong><span class="label label-info">
							<fmt:formatNumber pattern="#0.00" value="${product.price}"/>
						</span></strong>
					</p>
					<p>																					
					
					<a href="${addToCartURL}${product.id}" class="btn btn-default btn-sm"> 
						<span class="glyphicon-shopping-cart glyphicon"></span>
						<spring:message code='product.add.to.productSC'/>  
					</a>
														
					<a href="${menuAllURL}" class="btn btn-default btn-sm"> 
						<span class="glyphicon-hand-left glyphicon"></span> 
						<spring:message code='product.menu.back'/> 
					</a>
					
					<a href="${productDeleteAllCartURL}" class="btn btn-danger btn-sm"> 
						<span class="glyphicon-remove glyphicon"></span> 
						<spring:message code='product.remove.all'/> 
					</a>
																			
					</p>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="col-md-12">
																																				
					<a href="${productGoToShoppingCart}" class="btn btn-success btn-sm"> 
						<span class="glyphicon-shopping-cart glyphicon"></span>
						<spring:message code='product.go.to.productSC'/>  
					</a>												
					
				</div>
			</div>
				
		</div>		
		
		<c:forEach items="${orderIDOfProduct}" var="orderID">
			<div class="row">
				<div class="col-md-4">
					<div class="col-md-12">	
						<div class="thumbnail">
							<div class="caption">
							
								<p>
									<span>${product.name}</span>
								</p>
								
								<p>						
									<span class="label label-info">									
										<fmt:formatNumber pattern="#0.00" value="${product.price}"/>
									</span>
								</p>
										      					    			
				      			<a href="${productDeleteFromCartURL}?orderID=${orderID} " class="btn btn-danger btn-sm"> 
									<span class="glyphicon glyphicon-remove"></span> <spring:message code='product.remove.from.productSC'/> 
								</a>
														      									
							</div>
						</div>						
					</div>
									
				</div>
				
				<div class="col-md-4">
					<div class="col-md-12">	
						<div class="thumbnail">
							<div class="caption">
																																															
								<c:forEach items="${flavourSC[orderID]}" var="current">
								
									<p>
									  ${current.key.name} 									  
									  <fmt:formatNumber pattern="#0.00" value="${current.key.price}"/>									  									  								  									  									 
									  X ${current.value}	
										<a href="${cartRemoveFlavour}?orderID=${orderID}&flavourID=${current.key.id}" id="deleteFlavour"
										class="btn btn-danger btn-xs"> 
										<span class="glyphicon glyphicon-remove"></span> <spring:message code='flavour.delete.from.fsc'/> 
										</a>
									   
									</p>
									
								 </c:forEach>																
																																									
								  <div class="form-group">										
										<select class="form-control" id="flavour${orderID}">
											<c:forEach items="${allFlavours}" var="element">										  
											 	<option value="${element.id}" name="${element.name}"> 
											 		${element.name}, <fmt:formatNumber pattern="#0.00" value="${element.price}"/>
											 	</option>
											 </c:forEach>				
										</select>
								  </div>
																
								   <a  href="${cartAddFlavour}?orderID=${orderID}" onclick="addFlavourFunction(this)"  id="addFlavour${orderID}" 
								   name="${orderID}"
								   class="btn btn-default btn-sm"> 
										<span class="glyphicon glyphicon-plus"></span> 
										<spring:message code='product.add.flavour'/> 
								   </a>
								
								</form>
			      			</div>
						</div>
					</div>							
				</div>
							
			</div>	
								
		</c:forEach>			
		
	</section>
	
	<script type="text/javascript">
	function addFlavourFunction(obj){
		
		var link= obj.id;
		var value=obj.name;
		var e = document.getElementById('flavour'+value);
		var selected = e.options[e.selectedIndex].value;
		
// 		alert(e.options[e.selectedIndex]);
// 		e.options[e.selectedIndex].setAttribute("disabled", true);
		
		obj.href=obj.href+"&flavourID="+selected;

	}
	
	</script>
	
</body>
</html>
