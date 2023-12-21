<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Registro de una oferta</title>
		<jsp:directive.include file="includes/head.jspf" />
	</head>
	<body>
		<div id="container">
			<jsp:directive.include file="includes/header.jspf" />
			
			<h1>Registro de una oferta</h1>
			
			<jsp:directive.include file="includes/messages.jspf" />
			
			<form action="${pageContext.request.contextPath}/registroofertacasa" method='post'>
				<input name='casa_id' type='hidden' value="${param.casa_id}"/>
				Semana: <input type='date' name='week'/> <br/>
				<input value="Registrar" type='submit'> <br/>
			</form>
		</div>
	</body>
</html>