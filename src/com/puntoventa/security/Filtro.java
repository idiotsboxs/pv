package com.puntoventa.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.puntoventa.hibernate.Usuario;



public class Filtro implements Filter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String LOGIN_PATH = "/faces/login.xhtml";
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		
		Usuario usuario = (Usuario)session.getAttribute("sessionUsuario");
		
		if(usuario == null){
			RequestDispatcher rd = httpRequest.getRequestDispatcher((LOGIN_PATH));
			rd.forward(request, response);
		}
		else{
			
			chain.doFilter(request, response);
		}
		
			
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
