package com.biblioteca.ws.rest;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.biblioteca.entidad.Ciudad;

@XmlRootElement
public class CiudadRestResponse {
	private Ciudad ciudad;
	private List<Ciudad> ciudades;
	private String mensaje;

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad cliente) {
		this.ciudad = cliente;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> clientes) {
		this.ciudades = clientes;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}