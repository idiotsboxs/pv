package com.puntoventa.manage.bean;

import java.util.List;







import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;




import org.springframework.beans.factory.annotation.Autowired;

import com.puntoventa.dao.UsuarioDaoImpl;
import com.puntoventa.hibernate.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioManage {

	
	private UsuarioDaoImpl usuarioDaoImpl;

	private List<Usuario> lista;
	
	private String mensaje;

	public UsuarioManage() {
		
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public List<Usuario> getLista() {
		System.out.println("Entro");
		this.lista = usuarioDaoImpl.getListaUsuario();
		return this.lista;

	}

	
	
	public void setMensaje(String mensaje){
		
		this.mensaje = mensaje;
		
	}
	
	
	public String getMensaje(){
		
		this.mensaje = "ManageBean";
		
		return this.mensaje;
	}
	
	
}
