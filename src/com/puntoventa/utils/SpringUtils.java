package com.puntoventa.utils;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public final class SpringUtils implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final WebApplicationContext geWebApplicationContex(FacesContext faceContext) {
		HttpServletRequest httpServletRequest;
		httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
		
		return WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
		
	}

}
