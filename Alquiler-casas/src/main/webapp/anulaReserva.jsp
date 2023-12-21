<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Anular una reserva</title>
		<jsp:directive.include file="includes/head.jspf" />
	</head>
	<body>
		<div id="container">
			<jsp:directive.include file="includes/header.jspf" />
			
			<h1>Anular una reserva</h1>
			
			<jsp:directive.include file="includes/messages.jspf" />
			
			<form action="${pageContext.request.contextPath}/anulareservacasa" method='post'>
				<input name='reserva_id' type='hidden' value="${param.reserva_id}"/>
				¿Estás seguro de que quieres anular la reserva?
				<input value="Anular reserva" type='submit'> <br/>
			</form>
		</div>
	</body>
</html>