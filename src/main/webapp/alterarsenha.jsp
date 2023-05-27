<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"/>

	<style>
	
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
    
	</style>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sign-in.css"/>
</head>
	
<body class="text-center">
 
	<main class="form-signin w-100 m-auto">
	  <form method="post" action="http://localhost:8080/Aula01/alterarsenha">
	    <img class="mb-4" src="${pageContext.request.contextPath}/img/img_login.png" alt="" width="40" height="40"> 
	    <h1 class="h3 mb-3 fw-normal">Login</h1>
	    
	      <div class="form-floating">
	      <input type="email" value="${email}" class="form-control" id="floatingInput" placeholder="name@example.com" name="email">
	      <label for="floatingInput">Email</label>
	    </div>
	
	    <div class="form-floating">
	      <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="senha">
	      <label for="floatingPassword">Nova Senha</label>
	    </div>
	
	    <button class="w-100 btn btn-lg btn-primary" type="submit">Confirmar</button>
	    
	    <p class="mt-5 mb-3 text-muted">© 2023</p>
	  </form>
	  
	  <% 
	  	ArrayList<String> erros = (ArrayList)request.getAttribute("erros");
	    if(erros != null){
	    	for(int i=0; i <= erros.size() - 1; i++){
	    		out.println("<div class=\"alert alert-info\" role=\"alert\">");
	    		out.println(erros.get(i));
	    		out.println("</div>");
	    	}
	    }
	    
	    String mensagem = (String) request.getAttribute("mensagem");
	    if(mensagem != null){
		    out.println("<div class=\"alert alert-info\" role=\"alert\">");
		    out.println(mensagem);
		    out.println("</div>");
	    }
	  %>
	  
	</main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"/>
  
</body>

</html>