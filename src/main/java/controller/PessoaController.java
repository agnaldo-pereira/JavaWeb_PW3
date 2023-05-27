package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PessoaDAO;
import model.Pessoa;

@WebServlet(urlPatterns = {"/pessoacontroller","/listausuarios", "/procurar", "/editar", "/atualizar", "/alterar", "/apagar", "/paginar"})
public class PessoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public PessoaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String acao = request.getServletPath();
		
		if (acao.equals("/listausuarios")) {
			
			List<Pessoa> dados = new PessoaDAO().getPessoas();
			
			request.setAttribute("dados", dados);
			
			request.getRequestDispatcher("listar.jsp").forward(request, response);
			
		}
		else if (acao.equals("/paginar")) {
			PessoaDAO dao = new PessoaDAO();
			
			//início da busca
			int inicio = 1;
			
			//quantidade de registros a partir do início
			int quantidade = 15;
			
			//se o usuário enviar "início"
			if(request.getParameter("inicio")!= null){
				inicio = Integer.parseInt(request.getParameter("inicio"));
				
				if(inicio <= 0) {
					inicio = 1;
				}
			}
			
			if(request.getParameter("qtd")!= null){
				quantidade = Integer.parseInt(request.getParameter("qtd"));
			}
			
			
			List<Pessoa> dados = dao.buscar((inicio - 1)* quantidade, quantidade);
			
			//Total de registros da tabela			
			int totalDeRegistros = dao.getTotalDeRegistros();
			
			//total de páginas
			int totalDePaginas = (int )Math.ceil(totalDeRegistros * 1.0/quantidade);
			
			//atributos para utilizar no JSP, na navegação
			request.setAttribute("totalDeRegistros", totalDeRegistros);
			request.setAttribute("totalDePaginas", totalDePaginas);
			request.setAttribute("paginaAtual", inicio);
			
			request.setAttribute("dados", dados);
			request.getRequestDispatcher("listar.jsp").forward(request, response);

		}
		else if (acao.equals("/procurar")) {
			String id = request.getParameter("id");
			Pessoa p = new PessoaDAO().buscarPorId(Integer.parseInt(id));
			
			request.setAttribute("p", p);
			
			request.getRequestDispatcher("detalhar.jsp").forward(request, response);
		}
		else if (acao.equals("/editar")) {
			String id = request.getParameter("id");
			
			Pessoa p = new PessoaDAO().buscarPorId(Integer.parseInt(id));
			
			request.setAttribute("pessoa", p);
			request.getRequestDispatcher("editar.jsp").forward(request, response);
		}
		else if (acao.equals("/alterar")) {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			Pessoa p = new PessoaDAO().login(email, senha);
			
			if (p == null) {
				request.setAttribute("mensagem", "Usuário não encontrado!");
				request.getRequestDispatcher("alterar.jsp").forward(request, response);
			}
			else {
				request.setAttribute("pessoa", p);
				request.getRequestDispatcher("editar.jsp").forward(request, response);
			}
		}else if (acao.equals("/apagar")) {
			
			String id = request.getParameter("id");
			
			boolean resultado = new PessoaDAO().apagar(Integer.parseInt(id));
			
			response.sendRedirect(request.getContextPath() + "/paginar");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String acao = request.getServletPath();
		
		if (acao.equals("/cadastrar")) {
			String nome = request.getParameter("nome");
			
			Pessoa p1 = new Pessoa();
			p1.setNome(nome);
			
			request.setAttribute("mensagem", p1.toString());
			
			RequestDispatcher view = request.getRequestDispatcher("resposta.jsp");
			
			view.forward(request, response);
			
		} 
		else if (acao.equals("/atualizar")) {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			
			int idInteiro = Integer.parseInt(id);
			
			Pessoa p = new Pessoa(idInteiro, nome, email);
			
			p = new PessoaDAO().atualizar(p);
			
			request.setAttribute("p", p);
			
			request.getRequestDispatcher("detalhar.jsp").forward(request, response);
		}
		
		
	}

}
