<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<body>

	<fmt:setBundle basename="resources.labels"/>
	
	<fmt:setBundle basename="resources.labels"/>

	<form action="salvar.jsp" method="post">
		<h1>Faça o seu registro</h1>

		<!--  <label for="floatingInput">Email </label> -->
				
		<fmt:message key="email"/>
		
		<input type="email" id="floatingInput" name="email" placeholder="Seu email"> <br><br>

		<label for="floatingInput2">Nome </label> 
		<input type="text" id="floatingInput2" name="nome" placeholder="Seu nome"> <br><br>
			
			
		<label for="floatingPassword">Senha</label>
		<input type="password" id="floatingPassword" name="senha" placeholder="senha"> <br><br>


		<button type="submit">Cadastrar</button>

	</form>

</body>
</html>