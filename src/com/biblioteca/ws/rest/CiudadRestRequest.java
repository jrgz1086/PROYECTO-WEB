package com.biblioteca.ws.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CiudadRestRequest {
	private Integer codigo;
	private String descripcion;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}