package com.puntoventa.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.puntoventa.hibernate.Usuario;
import com.puntoventa.interfase.SecurityLogin;

/*@Component
@Path("/puntoventa")*/
public class RestServices {

	/*
	 * @Autowired
	 * 
	 * @Qualifier("securityLoginImpl") SecurityLogin securityLoginImpl;
	 * 
	 * 
	 * @GET
	 * 
	 * @Path("/test") public Response restTest(){ return
	 * Response.status(200).entity("..Servicio Rest OK").build(); }
	 * 
	 * @POST
	 * 
	 * @Path("/login")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Usuario
	 * login(@FormParam("nombre") String nombre,@FormParam("pass") String pass){
	 * Usuario user = new Usuario(); user.setNombre(nombre); user.setPass(pass);
	 * user = securityLoginImpl.getUser(user); return user; }
	 */
	
}
