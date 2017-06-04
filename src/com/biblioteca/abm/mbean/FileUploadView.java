package com.biblioteca.abm.mbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.biblioteca.abm.session.LibroSessionRemote;
import org.biblioteca.entidad.Libro;
import org.primefaces.model.UploadedFile;

import com.opencsv.CSVReader;

@ManagedBean(name = "fileUploadView")
public class FileUploadView {
	private UploadedFile file;
	@EJB
	LibroSessionRemote cfr;

	public void upload() {
		System.out.println("file");
		System.out.println(file);
		String carpeta = System.getProperty("jboss.home.dir");
		
		if (file != null) {
			try {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Succesful",
						file.getFileName() + " is uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				File file2 = new File(carpeta, file.getFileName());
				OutputStream out = new FileOutputStream(file2);
				out.write(file.getContents());
				out.close();
				CSVReader reader = new CSVReader(new FileReader(file2));
				String[] nextLine;
				while ((nextLine = reader.readNext()) != null) { 
					// nextLine[] is an array of values from the line	
					System.out.println(nextLine[0] + " - " + nextLine[1]
							+ " - " + nextLine[2] + " - etc...");
					Libro libro = new Libro();
					libro.setCodigo(new Integer(nextLine[0].trim()));
					libro.setDescripcion(nextLine[1]);
					libro.setCantidad(new Integer(nextLine[2].trim()));
					guardarLibro(libro);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void guardarLibro(Libro libro) {
		try {
			cfr.actualizar(libro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
}