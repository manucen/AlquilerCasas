<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Editar una casa</title>
		<jsp:directive.include file="includes/head.jspf" />
	</head>
	<body>
		<div id="container">
			<jsp:directive.include file="includes/header.jspf" />
			
			<h1>Editar una casa</h1>
			
			<jsp:directive.include file="includes/messages.jspf" />
			
			<form action="${pageContext.request.contextPath}/editarcasa" method='post'>
				provincia: <input type='text' name='province' value="${requestScope.casa.provincia}"/> <br/>
				región: <input type='text' name='region' value="${requestScope.casa.region}"/> <br/>
				dirección: <input type='text' name='address' value="${requestScope.casa.direccion}"/> <br/>
				habitaciones: <input type='number' name='rooms' value="${requestScope.casa.habitaciones}"/> <br/>
				camas: <input type='number' name='beds' value="${requestScope.casa.camas}"/> <br/>
				baños: <input type='number' name='bathroom' value="${requestScope.casa.banios}"/> <br/>
				<input name='id' type='hidden' value="${param.id}"/>
				<input value="Editar" type='submit'> <br/>
			</form>
		</div>
	</body>
</html>