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

	<jsp:useBean id="pessoa" class="model.Pessoa" />

	<jsp:setProperty property="*" name="pessoa" />

	<%
		PessoaDAO pessoaDAO = new PessoaDAO();
	
		boolean status = pessoaDAO.inserir(pessoa);
	
		if (status) {
			out.print("Pessoa cadastrada, etc...");
		} else {
			out.print("erro...");
		}
	%>
</body>
</html>