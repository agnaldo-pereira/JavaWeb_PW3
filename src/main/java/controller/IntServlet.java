package controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Teste")
public class IntServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IntServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getLocale());
		
		ResourceBundle bundle = ResourceBundle.getBundle("resources.mensagens", request.getLocale());
		
		String mensagem  = bundle.getString("saudacao");
		
		request.setAttribute("mensagem", mensagem);
		
		request.getRequestDispatcher("/int.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
