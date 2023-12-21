<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Home</title>
		<jsp:directive.include file="includes/head.jspf" />
	</head>
	<body>
		<div id="container">
			<jsp:directive.include file="includes/header.jspf" />
			
			<h1>Página principal</h1>
			
			<jsp:directive.include file="includes/messages.jspf" />
			
<c:if test="${sessionScope.logged != null}">
			<div id="listado-casas">
<c:forEach var="casa" items="${casas}">
				<div class="casa">
					<h2>Casa ${casa.id}</h2>
					<p>Provincia: ${casa.provincia}</p>
					<p>Región: ${casa.region}</p>
					<p>Direccion: ${casa.direccion}</p>
					<p>Habitaciones: ${casa.habitaciones}</p>
					<p>Camas: ${casa.camas}</p>
					<p>Baños: ${casa.banio}</p>
					<p>Puntos: ${casa.puntosdia}</p>
					<p>Propietario (id): ${casa.usuario.nombre}</p>
<c:if test="${casa.ofreces != null}">
					<ul class="ofertas">
<c:forEach var="oferta" items="${casa.ofreces}">
						<li>Semana del ${oferta.fechaInicioSemana}
<c:choose>
<c:when test="${oferta.reserva == null}">
(libre - <a href="registroreservacasa?oferta_id=${oferta.id}">Reservar</a>)
</c:when>
<c:when test="${oferta.reserva.usuario.id == sessionScope.logged}">
(reservado por mí - <a href="anulareservacasa?reserva_id=${oferta.reserva.id}">Anular reserva</a>)
</c:when>
<c:otherwise>
(reservado)
</c:otherwise>
</c:choose>
						</li>
</c:forEach>
					</ul>
</c:if>
				</div>
</c:forEach>
			</div>
</c:if>
		</div>
	</body>
</html>