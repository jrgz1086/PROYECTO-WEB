package com.biblioteca.mov.mbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.biblioteca.abm.session.LibroSessionRemote;
import org.biblioteca.entidad.Prestamo;
import org.biblioteca.entidad.PrestamoLibro;
import org.biblioteca.mov.session.PrestamoLibroSessionRemote;
import org.biblioteca.mov.session.PrestamoSessionRemote;

public class PrestamoMBean {
	private Prestamo prestamo;
	private PrestamoLibro prestamoLibro;
	private List<PrestamoLibro> prestamoLibroList = new ArrayList<PrestamoLibro>();
	@EJB
	PrestamoSessionRemote pfr;
	@EJB
	PrestamoLibroSessionRemote plifr;
	@EJB
	LibroSessionRemote lsfr;

	public List<Prestamo> listarPrestamos() {
		System.out.println("*** listarPrestamos ***");
		try {
			return pfr.buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Actions del Caso de Uso
	// Metodos que retornan String y se mapean en el archivo faces-config.xml
	public String actualizar() {
		System.out.println("*** actualizar ***");
		try {
			pfr.actualizar(prestamo, prestamoLibroList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "LISTA_PRESTAMO";
	}

	public String anular(Prestamo prestamoAnular) {
		System.out.println("*** anular ***");
		try {
			pfr.anular(prestamoAnular.getNumero());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "LISTA_PRESTAMO";
	}

	public String irALaConsulta() {
		return "LISTA_PRESTAMO";
	}

	public String editar(Prestamo prestamoEditar) {
		System.out.println("*** editar ***");
		this.prestamo = prestamoEditar;
		this.prestamoLibro = new PrestamoLibro();
		try {
			this.prestamoLibroList = plifr
					.buscarPorNumeroPrestamo(prestamoEditar.getNumero());
		} catch (Exception e) {
			System.out.println("Error al recuperar lista de libros prestados");
			System.out.println(e);
		}
		return "DETALLE_PRESTAMO";
	}

	public String nuevo() {
		System.out.println("*** nuevo ***");
		this.prestamo = new Prestamo();
		this.prestamo.setNumero(0);
		this.prestamoLibro = new PrestamoLibro();
		this.prestamoLibroList = new ArrayList<PrestamoLibro>();
		return "DETALLE_PRESTAMO";
	}

	public List<PrestamoLibro> listarPrestamosLibros() {
		System.out.println("*** listarPrestamosLibros ***");
		try {
			return prestamoLibroList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String agregar() {
		System.out.println("*** agregar ***");
		try {
			prestamoLibro.setLibro(lsfr.buscarPorCodigo(this.prestamoLibro
					.getLibro().getCodigo()));
			prestamoLibro.setEstado(0);
			prestamoLibroList.add(prestamoLibro);
			prestamoLibro = new PrestamoLibro();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "DETALLE_PRESTAMO";
	}

	public String quitar(PrestamoLibro prestamoLibro) {
		System.out.println("*** quitar ***");
		this.prestamoLibroList.remove(prestamoLibro);
		return "DETALLE_PRESTAMO";
	}

	public String devolver(PrestamoLibro prestamoLibro) {
		System.out.println("*** devolver ***");
		int index = prestamoLibroList.indexOf(prestamoLibro);
		prestamoLibroList.get(index).setFechaDevolucion(new Date());
		prestamoLibroList.get(index).setEstado(1);
		return "DETALLE_PRESTAMO";
	}

	// Getters y Setters
	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public PrestamoLibro getPrestamoLibro() {
		return prestamoLibro;
	}

	public void setPrestamoLibro(PrestamoLibro prestamoLibro) {
		this.prestamoLibro = prestamoLibro;
	}
}
