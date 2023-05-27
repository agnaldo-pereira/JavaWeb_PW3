package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.PessoaDAO;
import model.Pessoa;

@WebServlet(name = "JsonServlet", urlPatterns = "/json")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JsonServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pessoas = new Gson().toJson(new PessoaDAO().getPessoas());
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(pessoas);
		out.flush();
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Pessoa pessoa = new Pessoa();
		
		try {
			pessoa.setId(Integer.parseInt(request.getParameter("id")));
		}
		catch (Exception e) {System.out.print(e.toString());
			pessoa.setId(null);
		}
		
		pessoa.setNome(request.getParameter("nome"));
		pessoa.setEmail(request.getParameter("email"));
		pessoa.setSenha("123");
		
		PessoaDAO dao = new PessoaDAO();
		
		if (pessoa.getId() == null) {
			
			if (dao.inserir(pessoa)) {
				System.out.println("Usuário cadastrado com sucesso");
			}
			else {
				System.out.println("Erro ao cadastrar usuário");
			}
		}
		else {
			dao.atualizar(pessoa);
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean apagar = new PessoaDAO().apagar(id);
		response.setStatus(200);
	}
	

}