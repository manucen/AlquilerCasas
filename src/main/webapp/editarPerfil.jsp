<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Editar el perfil</title>
		<jsp:directive.include file="includes/head.jspf" />
	</head>
	<body>
		<div id="container">
			<jsp:directive.include file="includes/header.jspf" />
			
			<h1>Editar el perfil</h1>
			
			<jsp:directive.include file="includes/messages.jspf" />
			
			<form action="${pageContext.request.contextPath}/editarperfil" method='post'>
				nombre: <input type='text' name='name' value="${requestScope.usuario.nombre}"/> <br/>
				apellidos: <input type='text' name='surname' value="${requestScope.usuario.apellido}"/> <br/>
				email: <input type='email' name='email' value="${requestScope.usuario.email}"/> <br/>
				telefono: <input type='tel' name='phone' value="${requestScope.usuario.telefono}"/> <br/>
				password: <input type='password' name='password' value="${requestScope.usuario.password}"/> <br/>
				<input value="Editar" type='submit'> <br/>
			</form>
		</div>
	</body>
</html>