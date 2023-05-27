<%@page import="model.Pessoa"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://getbootstrap.com/docs/5.3/examples/headers/"/>
	<link href="https://getbootstrap.com/docs/5.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
	
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
    
    <link href="css/headers.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" charset="UTF-8" href="https://www.gstatic.com/_/translate_http/_/ss/k=translate_http.tr.cYEbrOmw59Q.L.W.O/d=0/rs=AN8SPfpU282joXDlbkUblMtWLWoZn4bb2g/m=el_main_css">
	
	
</head>
<body>
    
	<main>
	
	  <header class="p-3 mb-3 border-bottom">
	    <div class="container">
	      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
	        <a href="#" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
	          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
	        </a>
	
	        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
	          <li><a href="#" class="nav-link px-2 link-dark"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Home</font></font></a></li>
	          <li><a href="http://localhost:8080/Aula01/paginar" class="nav-link px-2 link-dark"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Lista de Usuários</font></font></a></li>
	          <li><a href="http://localhost:8080/Aula01/alterar.jsp" class="nav-link px-2 link-secondary"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Alteração de Usuários</font></font></a></li>
	          <li><a href="#" class="nav-link px-2 link-dark"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Opção 4</font></font></a></li>
	        </ul>
	
	        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
	          <input type="search" class="form-control" placeholder="Procurar..." aria-label="Procurar">
	        </form>
	
	        <div class="dropdown text-end">
	          <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
	            <img src="img/img_login.png" alt="mdo" width="32" height="32" class="rounded-circle">
	          </a>
	          <ul class="dropdown-menu text-small" style="">
	            <li><a class="dropdown-item" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Configurações</font></font></a></li>
	            <li><a class="dropdown-item" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Perfil</font></font></a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="http://localhost:8080/Aula01/LogoutController"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Sair</font></font></a></li>
	          </ul>
	        </div>
	      </div>
	    </div>
	  </header>
	
	</main>
	
	<main class="form-signin w-100 m-auto">
		<div class="text-center h4">
		  <form method="post" action="http://localhost:8080/Aula01/alterar">
		    <h1 class="h3 mb-3 fw-normal">Alteração de Usuário</h1>
		
		     Email: <input type="email" name="email"> <br/>
		     Senha:	<input type="password" name="senha"> <br/>
		    
		  
		    <button type="submit">Confirmar</button>
		    
		  </form>
		  <br/>
		
		 <% 
	    
	    String mensagem = (String) request.getAttribute("mensagem");
	    if(mensagem != null){
		    //out.println("<div class=\"alert alert-info\" role=\"alert\">");
		    out.println(mensagem);
		    //out.println("</div>");
	    }
	  %>
			
	</div>
		
		
	</main>

	
	<script src="https://getbootstrap.com/docs/5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script><div id="goog-gt-" class="skiptranslate VIpgJd-yAWNEb-L7lbkb" dir="ltr"><div style="padding: 8px;"><div><div class="VIpgJd-yAWNEb-l4eHX"><img src="https://www.gstatic.com/images/branding/product/1x/translate_24dp.png" width="20" height="20" alt="Google Tradutor"></div></div></div><div style="padding: 8px; float: left; width: 100%;"><h1 class="VIpgJd-yAWNEb-r4nke VIpgJd-yAWNEb-mrxPge">Texto original</h1></div><div style="padding: 8px;"><div class="VIpgJd-yAWNEb-nVMfcd-fmcmS"></div></div><div class="VIpgJd-yAWNEb-cGMI2b" style="padding: 8px;"><div class="VIpgJd-yAWNEb-Z0Arqf-PLDbbf"><span class="VIpgJd-yAWNEb-Z0Arqf-hSRGPd">Sugerir uma tradução melhor</span></div><div class="VIpgJd-yAWNEb-fw42Ze-Z0Arqf-haAclf"><hr style="color: #ccc; background-color: #ccc; height: 1px; border: none;"><div class="VIpgJd-yAWNEb-Z0Arqf-H9tDt"></div></div></div><div class="VIpgJd-yAWNEb-jOfkMb-Ne3sFf" style="display: none;"></div></div>
      
</body>
</html>