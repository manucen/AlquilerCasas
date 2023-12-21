<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Registro de una casa</title>
		<jsp:directive.include file="includes/head.jspf" />
	</head>
	<body>
		<div id="container">
			<jsp:directive.include file="includes/header.jspf" />
			
			<h1>Registro de una casa</h1>
			
			<jsp:directive.include file="includes/messages.jspf" />
			
			<form action="${pageContext.request.contextPath}/registrocasa" method='post'>
				provincia: <input type='text' name='province'/> <br/>
				región: <input type='text' name='region'/> <br/>
				dirección: <input type='text' name='address'/> <br/>
				habitaciones: <input type='number' name='rooms'/> <br/>
				camas: <input type='number' name='beds'/> <br/>
				baños: <input type='number' name='bathroom'/> <br/>
				<input value="Registrar" type='submit'> <br/>
			</form>
		</div>
	</body>
</html>