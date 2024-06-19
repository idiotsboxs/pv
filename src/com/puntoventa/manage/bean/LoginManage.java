package com.puntoventa.manage.bean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.puntoventa.Impl.SecurityLoginImpl;
import com.puntoventa.hibernate.Usuario;
import com.puntoventa.interfase.SecurityLogin;
import com.puntoventa.utils.SpringUtils;

@ManagedBean(name="loginManage")
@SessionScoped
public class LoginManage {

	
	public Usuario usuario;
	private SecurityLogin login;
	private HttpServletRequest httpServletRequest;
	private FacesContext faceContext;
	    
	public LoginManage(){
		//login = new SecurityLoginImpl();
		faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();

		usuario = new Usuario();
		login = (SecurityLoginImpl) SpringUtils.geWebApplicationContex(faceContext).getBean("securityLoginImpl");
    
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String login(){
		
		///faceContext=FacesContext.getCurrentInstance();
        //httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();

		Usuario usuarioPassword = new Usuario();
		usuarioPassword = login.getUser(usuario);
		
		if (usuario.getNombre().equals(usuarioPassword.getNombre()) && usuario.getPass().equals(usuarioPassword.getPass()) ){
			
			faceContext=FacesContext.getCurrentInstance();
	        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
	
			httpServletRequest.getSession().setAttribute("sessionUsuario", usuarioPassword);
			
			return "success";
			
		}
		else{
		
			return "fail";
		}
								
		
	}
	
	
	
	
	
}
