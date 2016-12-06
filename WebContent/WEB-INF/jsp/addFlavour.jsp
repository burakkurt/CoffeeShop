<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/bootstrap.min.css" />" />
<title><spring:message code='flavour.page.title'/></title>
</head>
<body>

<spring:url value="/" var="menuAll" htmlEscape="true"/> 	

<c:url var="actionURL" value="flavour.add"/>
	
<c:set var="buttonName">
	<spring:message code='flavour.add.button'/>
</c:set>
<c:if test="${ !empty update}">
	<c:url var="actionURL" value="flavour.update"/>
	<c:set var="buttonName">
		<spring:message code='flavour.update.button'/>
	</c:set>
</c:if>
	
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code="flavour.enhancers"/></h1>				
			</div>										
		</div>
	</section>
	
	<section class="container">
		<div class="row">
			<div class="col-md-12">
				<form:form  commandName="newFlavour" class="form-horizontal" method="post" action="${actionURL}">
					<fieldset>
						<legend><spring:message code="flavour.add.newproduct"/>				
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
						
					</legend>
	    
					<div class="form-group">
						<label class="control-label col-lg-2" for="name">
							<spring:message code="flavour.name"/>
						</label>
						<div class="col-lg-10">													
							<form:hidden path="id" />
							<form:input id="name" path="name" type="text" class="form:input-large"/>
							<form:errors path="name" cssClass="text-danger"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-lg-2" for="price">
							<spring:message code="flavour.price"/>					
						</label>
						<div class="col-lg-10">
							<div class="form:input-prepend">
								<form:input id="price" path="price" type="text" class="form:input-large"/>							
								<form:errors path="price" cssClass="text-danger" cssStyle="display: inline-flex;"/>	
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-lg-offset-2 col-lg-10">						
							<input type="submit" id="btnAdd" class="btn btn-primary" value ="${buttonName}"/>
						</div>
					</div>								
					
				   </fieldset>
			    </form:form>
		     </div>
	    </div>
	    
	    
		<div class="row">
			<div class="col-md-12">
				<legend><spring:message code="flavour.list"/></legend>
				<div class="table-responsive">
					<table id="example" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th><spring:message code="flavour.id"/></th>
								<th><spring:message code="flavour.name"/></th>
								<th><spring:message code="flavour.price"/></th>
								<th><spring:message code="flavour.action"/></th>
							</tr>
						</thead>
						<tbody>
					
							<c:forEach items="${flavourList}" var="flavour">
								<tr>
									<td><c:out value="${flavour.id}" /></td>
									<td><c:out value="${flavour.name}" /></td>		
									<td><c:out value="${flavour.price}" /></td>		
									<td colspan="2">
										<a href="flavour.delete?id=${flavour.id}"
											class="btn btn-danger" type="button">
											<spring:message code="flavour.delete.button"/>
										</a>
									
										<a href="flavour.edit?id=${flavour.id}"
										class="btn btn-warning" type="button">
											<spring:message code="flavour.edit.button"/>
										</a>
									</td>					    
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>	
			
					
	</section>
</body>
</html>
