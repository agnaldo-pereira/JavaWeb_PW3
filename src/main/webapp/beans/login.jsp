<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>	
</head>
<body>
	<body>
		<form action="autenticar.jsp" method="post">
			<h1 class="h3 mb-3 fw-normal">Faça o seu login</h1>
			
			 <label for="floatingInput">Email </label>
			 <input type="email" id="floatingInput" name="email"
						placeholder="name@example.com"> <br><br>
				
			<label for="floatingPassword">Senha</label>
			<input type="password" id="floatingPassword" name="senha" placeholder="Password"> <br><br>
				
			<button type="submit"> Entrar </button> <br><br>
			
			<a href="cadastrar.jsp">Cadastrar-se</a>
				
		</form>
	</body>
</html>