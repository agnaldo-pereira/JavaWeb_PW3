<%@page import="model.Pessoa"%>
<%@page import="dao.PessoaDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>

<body>

	<jsp:useBean id="pessoa" class="model.Pessoa" scope="request"/>
	
	<%--  <jsp:setProperty property="*" name="pessoa" />--%>

	<jsp:setProperty property="email" name="pessoa" />
	
	<jsp:setProperty property="senha" name="pessoa" />
	

	<%
		PessoaDAO pessoaDAO = new PessoaDAO();
	
		Pessoa p = pessoaDAO.login(pessoa.getEmail(), pessoa.getSenha());
	
		if (p!=null) {
			out.print("Pessoa logada, etc...");
		} else {
			out.print("erro...");
		}
	%>
</body>
</html>