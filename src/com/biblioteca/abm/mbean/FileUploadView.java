package com.biblioteca.abm.mbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import com.opencsv.CSVReader;

@ManagedBean(name = "fileUploadView")
public class FileUploadView {
	private UploadedFile file;

	public void upload() {
		System.out.println("file");
		System.out.println(file);
		if (file != null) {
			try {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Succesful",
						file.getFileName() + " is uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				File file2 = new File(
						"C:\\jboss-as-7.1.1.Final\\standalone\\data",
						file.getFileName());
				OutputStream out = new FileOutputStream(file2);
				out.write(file.getContents());
				out.close();
				CSVReader reader = new CSVReader(new FileReader(file2));
				String[] nextLine;
				while ((nextLine = reader.readNext()) != null) { 
					// nextLine[] is an array of values from the line	
					System.out.println(nextLine[0] + " - " + nextLine[1]
							+ " - " + nextLine[2] + " - etc...");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
}