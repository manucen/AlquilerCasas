<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Mis casas</title>
		<jsp:directive.include file="includes/head.jspf" />
	</head>
	<body>
		<div id="container">
			<jsp:directive.include file="includes/header.jspf" />
			
			<h1>Mis casas</h1>
			
			<jsp:directive.include file="includes/messages.jspf" />
			
			<div id="listado-casas">
<c:forEach var="casa" items="${casas}">
				<div class="casa">
					<h2>Casa ${casa.id}</h2>
					<p>Provincia: ${casa.provincia}</p>
					<p>Región: ${casa.region}</p>
					<p>Direccion: ${casa.direccion}</p>
					<p>Habitaciones: ${casa.habitaciones}</p>
					<p>Camas: ${casa.camas}</p>
					<p>Baños: ${casa.banios}</p>
					<p>Puntos: ${casa.puntos}</p>
					<p><a href="${pageContext.request.contextPath}/editarcasa?id=${casa.id}">Editar Casa</a></p>
					<p><a href="${pageContext.request.contextPath}/registroofertacasa?casa_id=${casa.id}">Ofertar semana</a></p>
<c:if test="${casa.ofertas != null}">
					<h3>Ofertas:</h3>
					<ul class="ofertas">
<c:forEach var="oferta" items="${casa.ofertas}">
						<li>Semana del ${oferta.fechaInicioSemana}
<c:choose>
<c:when test="${oferta.reserva == null}">(libre)</c:when>
<c:otherwise>(reservado)</c:otherwise>
</c:choose>
						</li>
</c:forEach>
					</ul>
</c:if>
				</div>
</c:forEach>
			</div>
		</div>
	</body>
</html>