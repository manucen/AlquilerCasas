<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Registro de usuario</title>
		<jsp:directive.include file="includes/head.jspf" />
	</head>
	<body>
		<div id="container">
			<jsp:directive.include file="includes/header.jspf" />
			
			<h1>Registro de usuario</h1>
			
			<jsp:directive.include file="includes/messages.jspf" />
			
			<form action="${pageContext.request.contextPath}/registrousuario" method='post'>
				nombre: <input type='text' name='name'/> <br/>
				apellidos: <input type='text' name='surname'/> <br/>
				telefono: <input type='tel' name='phone'/> <br/>
				email: <input type='email' name='email'/> <br/>
				contraseña: <input type='password' name='password'/> <br/>
				<input value="Registrar" type='submit'> <br/>
			</form>
		</div>
	</body>
</html>