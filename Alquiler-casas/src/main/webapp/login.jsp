<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Iniciar sesión</title>
		<jsp:directive.include file="includes/head.jspf" />
	</head>
	<body>
		<div id="container">
			<jsp:directive.include file="includes/header.jspf" />
			
			<h1>Iniciar Sesión</h1>
			
			<jsp:directive.include file="includes/messages.jspf" />
			
			<form action="${pageContext.request.contextPath}/login" method='post'>
				email: <input type='email' name='email'/> <br/>
				contraseña: <input type='password' name='password'/> <br/>
				<input value="Iniciar sesión" type='submit'> <br/>
			</form>
		</div>
	</body>
</html>