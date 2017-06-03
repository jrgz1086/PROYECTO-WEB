package com.biblioteca.abm.mbean;

import java.util.List;

import javax.ejb.EJB;

import org.biblioteca.abm.session.LibroSessionRemote;
import org.biblioteca.entidad.Libro;

public class LibroMBean {
	private Libro libro;
	@EJB
	LibroSessionRemote cfr;

	public List<Libro> listarLibros() {
		System.out.println("*** listarLibros *** ");
		try {
			return cfr.buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String actualizar() {
		try {
			cfr.actualizar(libro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "LISTA_LIBRO";
	}

	public String irALaConsulta() {
		return "LISTA_LIBRO";
	}

	public String editar(Libro libroEditar) {
		System.out.println("*** editar ***");
		this.libro = libroEditar;
		return "DETALLE_LIBRO";
	}

	public String nuevo() {
		System.out.println("*** nuevo ***");
		this.libro = new Libro();
		this.libro.setCodigo(0);
		return "DETALLE_LIBRO";
	}

	// Getters y Setters
	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
}