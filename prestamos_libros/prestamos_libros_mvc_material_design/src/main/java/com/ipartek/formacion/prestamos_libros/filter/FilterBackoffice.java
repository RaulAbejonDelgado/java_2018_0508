package com.ipartek.formacion.prestamos_libros.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.prestamos_libros.pojo.UsuarioLogin;

/**
 * Servlet Filter implementation class FilterBackoffice
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST } 
, urlPatterns = { "/backoffice/*" })
public class FilterBackoffice implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("se ejecuta al destruir el filtro");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		try {			
			HttpSession session = req.getSession();
			UsuarioLogin usuario = (UsuarioLogin) session.getAttribute("usuarioLogin");
			
			if (usuario != null) {
				chain.doFilter(request, response);
			}else {
				res.sendRedirect( req.getContextPath() + "/inicio");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("se ejecuta al inciar la App Web");
	}

}
