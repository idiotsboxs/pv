package com.puntoventa.Impl;


import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.puntoventa.dao.UsuarioDaoImpl;
import com.puntoventa.hibernate.Usuario;
import com.puntoventa.interfase.SecurityLogin;

/**
 * 
 * @author CHRISTIAN BUENDIA
 *
 */
@Component("securityLoginImpl")
public class SecurityLoginImpl extends UsuarioDaoImpl implements SecurityLogin, Serializable   {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Usuario getUser(Usuario user) {
		// TODO Auto-generated method stub
		
		return  this.getUserByName(user);
		
			
	}

}
