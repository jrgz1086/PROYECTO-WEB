package com.biblioteca.abm.mbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.biblioteca.abm.session.ClienteSessionRemote;
import org.biblioteca.entidad.Cliente;

public class ClienteMBean {
	private Cliente cliente;
	@EJB
	ClienteSessionRemote cfr;

	public List<Cliente> listarClientes() {
		System.out.println("*** listarClientes *** ");
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
			cfr.actualizar(cliente);
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
		return "LISTA_CLIENTE";
	}

	public String irALaConsulta() {
		return "LISTA_CLIENTE";
	}

	public String editar(Cliente clienteEditar) {
		System.out.println("*** editar ***");
		this.cliente = clienteEditar;
		return "DETALLE_CLIENTE";
	}

	public String nuevo() {
		System.out.println("*** nuevo ***");
		this.cliente = new Cliente();
		this.cliente.setCodigo(0);
		return "DETALLE_CLIENTE";
	}

	// Getters y Setters
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}