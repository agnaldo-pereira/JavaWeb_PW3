package controller;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Refreshable;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.ConexaoDAO;
import dao.PessoaDAO;
import model.Pessoa;
import utils.MailUtil;

@WebServlet(urlPatterns = {"/login", "/novousuario", "/recuperarsenha", "/validarcodigo", "/alterarsenha"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
			/*ConexaoDAO conexaoDAO = new ConexaoDAO();
			conexaoDAO.conectar();*/
			
			String acao = request.getServletPath();
			System.out.println(acao);
			 if (acao.equals("/novousuario")){
				 
//				 response.sendRedirect("cadastrar.jsp");
				 
			 }else if (acao.equals("/login")) {
				 response.sendRedirect("login.jsp");
			 }else {
				 //redirecionar para a página de login
				 //informando que não existe a url
			 }
		}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getServletPath();
	
		
		if (acao.equals("/novousuario")) {
			
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			
			FileItem imagem = null;
			String nome = null;
			String email = null;
			String senha = null;
			String caminho = null;
			
			 try
		        {
		            if(isMultipart)
		            {
		                DiskFileItemFactory factory = new DiskFileItemFactory();
		                ServletFileUpload upload = new ServletFileUpload(factory);

		                List<FileItem> items = upload.parseRequest(request);

		                for(FileItem item : items)
		                {
		                    if(item.isFormField())
		                    {

		                    	if(item.getFieldName().equals("nome")) {
		                    		 nome = item.getString();
		                    	}

		                        if(item.getFieldName().equals("email")) {
		                        	 email = item.getString();
		                        }

		                        if(item.getFieldName().equals("senha")){
		                 
		                        	 senha = item.getString();
		                        }
		                        
		                    }
		                    else
		                    {
		                        imagem = item;
		                    }
		                }

		            }
		        }
		        catch(Exception ex)
		        {
		            System.out.println("ERRO: " + ex.getMessage());
		        }

			
			List<String>erros = new ArrayList<String>();
			
			if(email == null || email.isEmpty()) {
				erros.add("O e-mail precisa ser preenchido.");
			}
			
			if(nome == null || nome.isEmpty()) {
				erros.add("O nome precisa ser preenchido.");
			}
			
			if(senha == null || senha.isEmpty()) {
				erros.add("A senha precisa ser preenchida.");
			}
			
			if(erros.isEmpty()) {
				
				//Faz o upload da imagem
				
				//File uploadFile = new File("C:\\Users\\Aluno\\eclipse-workspace\\Aula01\\src\\main\\webapp\\img\\"+nome+imagem.getName());
				
			    System.out.println("Caminho upload "+request.getServletContext().getRealPath("img")+File.separator+nome+imagem.getName());
                File uploadFile = new File(request.getServletContext().getRealPath("img")+File.separator+nome+imagem.getName());
                try {
					imagem.write(uploadFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                caminho = nome+imagem.getName();
				
				//instacia um objeto Pessoa
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(nome);
				pessoa.setEmail(email);
				pessoa.setSenha(senha);
				pessoa.setImagem(caminho);
				
				//armazena no banco de dados
				PessoaDAO dao = new PessoaDAO();
				if (dao.inserir(pessoa)) {
					
					MailUtil mailUtil = new MailUtil();
					mailUtil.send(email, "Boas-vindas!","Seja Bem-vindo ao nosso site!");
					
					System.out.println("Usuário cadastrado com sucesso");
					
					request.setAttribute("mensagem", "Usuário cadastrado com sucesso");
					RequestDispatcher view =
							request.getRequestDispatcher("/admin/login.jsp");
					
					view.forward(request, response);
					
				}
				else {
					System.out.println("Erro ao cadastrar usuário");
				}
				
				
			}
			else {
				request.getSession().invalidate();
				request.setAttribute("erros", erros);
				RequestDispatcher view =
						request.getRequestDispatcher("novousuario.jsp");
				
				view.forward(request, response);
			}
			
			
			
		}else if (acao.equals("/login")) {
			
			//1 - Receber os parâmetros
			//2 - Validar o preenchimento
			//3 - Consultar a base de dados
			//4 - Redirecionar para página interna ou voltar para o login
			
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String lembrar = request.getParameter("lembrar");
			String usuario = "";
			
			System.out.println(email);
			
			List<String>erros = new ArrayList<String>();
			
			if(email == null || email.isEmpty()) {
				erros.add("O e-mail precisa ser preenchido.");
			}
			
			if(senha == null || senha.isEmpty()) {
				erros.add("A senha precisa ser preenchida.");
			}
			
			//consulta em banco de dados
		
			Pessoa p = new PessoaDAO().login(email, senha);
			
			if (p == null) {
				erros.add("Usuário ou senha inválidos.");
			}
			
			if(erros.isEmpty()) {
				//se nenhum erro foi adicionado na lista de erros, usuario ok
				request.getSession().setAttribute("usuario", p.getNome());
	
				response.sendRedirect("./admin/areainterna.jsp");
				//response.sendRedirect("./admin");
			}else {
				request.getSession().invalidate();
				request.setAttribute("erros", erros);
				RequestDispatcher view =
						request.getRequestDispatcher("./admin/login.jsp");
				
				view.forward(request, response);
			}
			
			
			
		}else if (acao.equals("/recuperarsenha")) {
			
			System.out.println("encontrou recuperar senha");
			
			String email = request.getParameter("email");
			
			System.out.println("email "+email);
			
			List<String>erros = new ArrayList<String>();
			
			if(email == null || email.isEmpty()) {
				erros.add("O e-mail precisa ser preenchido.");
			}
			
			if(erros.isEmpty()) {
				
				Pessoa p = new PessoaDAO().buscarPorEmail(email);
				
				if (p == null) {
					erros.add("Email não encontrado.");
				}
				
				if(erros.isEmpty()) {
					//se nenhum erro foi adicionado na lista de erros, usuario ok
					request.getSession().setAttribute("codigo", p.getCodigo());
					
					String link = "http://localhost:8080/Aula01/validarcodigo.jsp";
		
					MailUtil mailUtil = new MailUtil();
					mailUtil.send(email, "Recuperação de Senha ","Clique <a href=\""+link+"\">Aqui</a>"+" O código de verificação é "+p.getCodigo());
					
					//request.getSession().invalidate();
					//request.setAttribute("erros", erros);
					RequestDispatcher view =
							request.getRequestDispatcher("./admin/login.jsp");
					
					view.forward(request, response);
					
				}else {
					request.getSession().invalidate();
					request.setAttribute("erros", erros);
					RequestDispatcher view =
							request.getRequestDispatcher("./admin/login.jsp");
					
					view.forward(request, response);
				}
				
			}
			else {
				request.getSession().invalidate();
				request.setAttribute("erros", erros);
				RequestDispatcher view =
						request.getRequestDispatcher("recuperarsenha.jsp");
				
				view.forward(request, response);
			}
			
		}else if (acao.equals("/validarcodigo")) {
			
			String email = request.getParameter("email");
			String codigo = request.getParameter("codigo");
			
			System.out.println("email no validar "+email+"-"+codigo);
			
			
			List<String>erros = new ArrayList<String>();
			
			if(email == null || email.isEmpty()) {
				erros.add("O e-mail precisa ser preenchido.");
			}
			if(codigo == null || codigo.isEmpty()) {
				erros.add("O código precisa ser preenchido.");
			}
			
			if(erros.isEmpty()) {
				
				Pessoa p = new PessoaDAO().buscarPorEmail(email);
				
				if (p == null) {
					erros.add("Email não encontrado.");
				}
				
				if(erros.isEmpty()) {
					//se nenhum erro foi adicionado na lista de erros, usuario ok
					request.getSession().setAttribute("codigo", p.getCodigo());
					
					if (Integer.parseInt(codigo) == p.getCodigo()) {
						
						request.setAttribute("email", email);
						RequestDispatcher view =
								request.getRequestDispatcher("./alterarsenha.jsp");
						
						view.forward(request, response);
						
					}else {
						
						erros.add("Código de verificação inválido.");
						
						request.getSession().invalidate();
						request.setAttribute("erros", erros);
						RequestDispatcher view =
								request.getRequestDispatcher("./validarcodigo.jsp");
						
						view.forward(request, response);
					}
					
					//request.getSession().invalidate();
					//request.setAttribute("erros", erros);
					RequestDispatcher view =
							request.getRequestDispatcher("./admin/login.jsp");
					
					view.forward(request, response);
					
				}else {
					request.getSession().invalidate();
					request.setAttribute("erros", erros);
					RequestDispatcher view =
							request.getRequestDispatcher("./admin/login.jsp");
					
					view.forward(request, response);
				}
				
			}
			else {
				request.getSession().invalidate();
				request.setAttribute("erros", erros);
				RequestDispatcher view =
						request.getRequestDispatcher("recuperarsenha.jsp");
				
				view.forward(request, response);
			}
			
		}else if (acao.equals("/alterarsenha")) {
	
			
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			List<String>erros = new ArrayList<String>();
			
			if(email == null || email.isEmpty()) {
				erros.add("O e-mail precisa ser preenchido.");
			}
			
			if(senha == null || senha.isEmpty()) {
				erros.add("A senha precisa ser preenchida.");
			}
			
			//consulta em banco de dados
		
			Boolean resultado = new PessoaDAO().atualizarsenha(senha, email);
			
			if (resultado == false) {
				erros.add("Não encontrou o registro.");
			}
			
			if(erros.isEmpty()) {
					
				response.sendRedirect("./admin/login.jsp");
				//response.sendRedirect("./admin");
			}else {
				request.getSession().invalidate();
				request.setAttribute("erros", erros);
				RequestDispatcher view =
						request.getRequestDispatcher("./recuperarsenha.jsp");
				
				view.forward(request, response);
			}
		}
				
		
	}

}
