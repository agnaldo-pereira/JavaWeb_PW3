<%@page import="model.Pessoa"%>
<%@page import="java.util.List"%>
<%@page import="dao.PessoaDAO"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	 
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:useBean id="dao" class="dao.PessoaDAO" /> 


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>

<body>
	<c:forEach var="pessoa" items="${dao.pessoas}">
		 <p>Email: ${pessoa.email}</p>
	</c:forEach>
</body>
</html>