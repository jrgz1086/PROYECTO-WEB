package com.biblioteca.inf.mbean;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;

import com.biblioteca.util.GlobalConfig;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@ManagedBean(name = "informeCiudadBean")
public class InformeCiudadMBean {
	private Integer desde;
	private Integer hasta;

	public String imprimir() {
		String connectionString = "jdbc:postgresql://"+
				GlobalConfig.REPORT_DB_HOST + "/" + GlobalConfig.REPORT_DB_NAME;
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(connectionString, GlobalConfig.REPORT_DB_USER, GlobalConfig.REPORT_DB_PASS);
			URL reportPathUrl = InformeCiudadMBean.class
					.getResource("InformeCiudad.jasper");
			String reportPathName = reportPathUrl.getFile();
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					reportPathName, new HashMap(), conn);
			String webContentPath = InformeCiudadMBean.class
					.getResource("")
					.getFile()
					.substring(
							0,
							InformeCiudadMBean.class.getResource("").getFile()
									.indexOf("WEB-INF"));
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					webContentPath + "InformeCiudad.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "INFORME_CIUDAD";
	} // Getters y Setters 
	
	public Integer getDesde() { 
		return desde; 
	}

	public void setDesde(Integer desde) {
		this.desde = desde;
	}

	public Integer getHasta() {
		return hasta;
	}

	public void setHasta(Integer hasta) {
		this.hasta = hasta;
	}
}