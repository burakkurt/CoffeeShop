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
<title><spring:message code='category.page.title'/></title>
</head>
<body>

<spring:url value="/" var="menuAll" htmlEscape="true"/> 	

<c:url var="actionURL" value="category.add"/>
	
<c:set var="buttonName">
	<spring:message code='category.add.button'/>
</c:set>
<c:if test="${ !empty update}">
	<c:url var="actionURL" value="category.update"/>
	<c:set var="buttonName">
		<spring:message code='category.update.button'/>
	</c:set>
</c:if>
	
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code="category.category"/></h1>				
			</div>										
		</div>
	</section>
	
	<section class="container">
		<div class="row">
			<div class="col-md-12">
				<form:form  commandName="newCategory" class="form-horizontal" method="post" action="${actionURL}">
					<fieldset>
						<legend><spring:message code="category.add.newproduct"/>				
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
							<spring:message code="category.name"/>
						</label>
						<div class="col-lg-10">													
							<form:hidden path="id" />
							<form:input id="name" path="name" type="text" class="form:input-large"/>
							<form:errors path="name" cssClass="text-danger"/>
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
				<legend><spring:message code="category.list"/></legend>
				<div class="table-responsive">
					<table id="example" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th><spring:message code="category.id"/></th>
								<th><spring:message code="category.name"/></th>
								<th><spring:message code="category.action"/></th>
							</tr>
						</thead>
						<tbody>
					
							<c:forEach items="${categoryList}" var="category">
								<tr>
									<td><c:out value="${category.id}" /></td>
									<td><c:out value="${category.name}" /></td>		
									<td colspan="2">
										<a href="category.delete?id=${category.id}"
											class="btn btn-danger" type="button">
											<spring:message code="category.delete.button"/>
										</a>
									
										<a href="category.edit?id=${category.id}"
										class="btn btn-warning" type="button">
											<spring:message code="category.edit.button"/>
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
