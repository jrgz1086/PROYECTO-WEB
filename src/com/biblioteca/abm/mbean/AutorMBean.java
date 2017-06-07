package com.biblioteca.abm.mbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.biblioteca.abm.session.AutorSessionRemote;
import org.biblioteca.entidad.Autor;

public class AutorMBean {
	private Autor autor;
	@EJB
	AutorSessionRemote cfr;

	public List<Autor> listarAutores() {
		System.out.println("*** listarAutores *** ");
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
			cfr.actualizar(autor);
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
		return "LISTA_AUTOR";
	}

	public String irALaConsulta() {
		return "LISTA_AUTOR";
	}

	public String editar(Autor autorEditar) {
		System.out.println("*** editar ***");
		this.autor = autorEditar;
		return "DETALLE_AUTOR";
	}

	public String nuevo() {
		System.out.println("*** nuevo ***");
		this.autor = new Autor();
		this.autor.setCodigo(0);
		return "DETALLE_AUTOR";
	}
	
	public void eliminar(Autor autorEliminar){
		try {
			cfr.eliminar(autorEliminar.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getters y Setters
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}