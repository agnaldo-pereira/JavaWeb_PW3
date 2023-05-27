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
<!-- Esse atributo é preenchido no servlet. Se a página for executada diretamente, sem passar pelo servlet, ele ficará em branco.-->
	${mensagem}
	

<!-- Caso acesse a página diretamente (//endereço/pag.jsp por ex), pode ser feito assim: -->
	<fmt:setBundle basename="resources.mensagens"/>
	
	<fmt:setBundle basename="resources.mensagens"/>
	
	<fmt:message key="saudacao"/>

</body>
</html>