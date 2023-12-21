<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Registro de una reserva</title>
		<jsp:directive.include file="includes/head.jspf" />
	</head>
	<body>
		<div id="container">
			<jsp:directive.include file="includes/header.jspf" />
			
			<h1>Registro de una reserva</h1>
			
			<jsp:directive.include file="includes/messages.jspf" />
			
			<form action="${pageContext.request.contextPath}/registroreservacasa" method='post'>
				<input name='oferta_id' type='hidden' value="${param.oferta_id}"/>
				¿Estás seguro de que quieres reservar esta semana?
				<input value="Reservar" type='submit'> <br/>
			</form>
		</div>
	</body>
</html>