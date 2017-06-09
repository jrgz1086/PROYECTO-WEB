package com.biblioteca.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

import com.biblioteca.util.GlobalConfig;

@Startup
@Singleton
public class LoadGlobalConfig {
	
	private static final Logger log = Logger.getLogger(LoadGlobalConfig.class);
	
	@PostConstruct
    public void init() {
        log.info("*****LOAD GLOBAL CONFIG*****");
        String propFileName = System.getProperty("jboss.home.dir") + 
        		"/standalone/configuration/"+
        		"biblioteca-global-config.properties";
        
		Properties propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream(propFileName));
			GlobalConfig.REPORT_DB_HOST = propiedades.getProperty("report.db.host");
			GlobalConfig.REPORT_DB_NAME = propiedades.getProperty("report.db.name");
			GlobalConfig.REPORT_DB_USER = propiedades.getProperty("report.db.user");
			GlobalConfig.REPORT_DB_PASS = propiedades.getProperty("report.db.pass");
			
		} catch (FileNotFoundException e) {
			log.error("Archivo no encontrado :" + propFileName);
		} catch (IOException e) {
			log.error("No se puede leer el archivo: " + propFileName);
		}
    }

}
