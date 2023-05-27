package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AuthFilter extends HttpFilter implements Filter {
       
    
    public AuthFilter() {
        super();
        
    }
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

		boolean estaLogado = (session != null && session.getAttribute("usuario") != null);
		
		String urlLogin = httpRequest.getContextPath() + "/login";
		
		boolean eRequisicaoLogin = httpRequest.getRequestURI().equals(urlLogin);

		boolean ePaginaLogin = httpRequest.getRequestURI().endsWith("login.jsp");

		if (estaLogado || eRequisicaoLogin) {
			chain.doFilter(request, response);
		}
		else if(estaLogado && (eRequisicaoLogin || ePaginaLogin)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
            dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login.jsp");
            dispatcher.forward(request, response);
		}
	
	}

	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
