package com.biblioteca.abm.mbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.biblioteca.abm.session.CiudadSessionRemote;
import org.biblioteca.entidad.Ciudad;
import org.jboss.logging.Logger;

public class CiudadMBean {
	
	private static final Logger log = Logger.getLogger(CiudadMBean.class);
	
	private Ciudad ciudad;
	@EJB
	CiudadSessionRemote cfr;

	public List<Ciudad> listarCiudades() {
		log.info("*** listarCiudades *** ");
		try {
			return cfr.buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Actions del Caso de Uso
	// Metodos que retornan String y se mapean en el archivo faces-config.xml
	public String actualizar() {
		try {
			cfr.actualizar(ciudad);
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
		return "LISTA_CIUDAD";
	}

	public String irALaConsulta() {
		return "LISTA_CIUDAD";
	}

	public String editar(Ciudad ciudadEditar) {
		log.info("*** editar ***");
		this.ciudad = ciudadEditar;
		return "DETALLE_CIUDAD";
	}

	public String nuevo() {
		log.info("*** nuevo ***");
		this.ciudad = new Ciudad();
		this.ciudad.setCodigo(0);
		return "DETALLE_CIUDAD";
	}

	// Getters y Setters
	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
}