<%@page import="utils.MailUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/PessoaController" method="Post">
		<input name="nome"/>
		<input type="submit" value="Enviar"/>
	</form>
	<p> qualquer coisa </p>
	<%
	MailUtil mailUtil = new MailUtil();
	mailUtil.send("alessandra.2021006270@aluno.iffar.edu.br", "Assunto: Teste ","mensagem teste");
	%>
</body>
</html>