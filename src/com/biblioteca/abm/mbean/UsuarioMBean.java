package com.biblioteca.abm.mbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.biblioteca.abm.session.UsuarioSessionRemote;
import org.biblioteca.entidad.Usuario;

public class UsuarioMBean {
	private Usuario usuario;
	@EJB
	UsuarioSessionRemote cfr;

	public List<Usuario> listarUsuarios() {
		System.out.println("*** listarUsuarios *** ");
		try {
			return cfr.buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String actualizar() {
		System.out.println("*** actualizar *** ");
		try {
			cfr.actualizar(usuario);
			String mensaje = FacesContext
					.getCurrentInstance()
					.getApplication()
					.getResourceBundle(FacesContext.getCurrentInstance(), "msg")
					.getString("label.guardadoConExito");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito",
							mensaje));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "LISTA_USUARIO";
	}

	public String irALaConsulta() {
		return "LISTA_USUARIO";
	}

	public String editar(Usuario usuarioEditar) {
		System.out.println("*** editar ***");
		this.usuario = usuarioEditar;
		return "DETALLE_USUARIO";
	}

	public String nuevo() {
		System.out.println("*** nuevo ***");
		this.usuario = new Usuario();
		this.usuario.setCodigo(0);
		return "DETALLE_USUARIO";
	}
	
	public void eliminar(Usuario usuarioEliminar){
		try {
			cfr.eliminar(usuarioEliminar.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getters y Setters
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}