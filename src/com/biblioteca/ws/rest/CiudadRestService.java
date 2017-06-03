package com.biblioteca.ws.rest;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.biblioteca.abm.session.CiudadSession;
import org.biblioteca.abm.session.CiudadSessionRemote;
import org.biblioteca.entidad.Ciudad;

@Path("/rest/CiudadRestService")
public class CiudadRestService {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ActualizarCiudad")
	public CiudadRestResponse actualizarCiudad(CiudadRestRequest request) {
		CiudadRestResponse response = new CiudadRestResponse();
		try {
			final CiudadSessionRemote csr = lookupCiudadSessionRemote();
			Ciudad ciudad = new Ciudad();
			ciudad.setCodigo(request.getCodigo());
			ciudad.setDescripcion(request.getDescripcion());
			response.setCiudad(csr.actualizar(ciudad));
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ConsultarCiudades")
	public CiudadRestResponse ConsultarCiudades() {
		CiudadRestResponse response = new CiudadRestResponse();
		try {
			final CiudadSessionRemote csr = lookupCiudadSessionRemote();
			response.setCiudades(csr.buscarTodos());
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private static CiudadSessionRemote lookupCiudadSessionRemote()
			throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		jndiProperties.put(Context.PROVIDER_URL, "remote://localhost:4447");
		jndiProperties.put(Context.SECURITY_PRINCIPAL, "admin");
		jndiProperties.put(Context.SECURITY_CREDENTIALS, "admin123");
		final Context context = new InitialContext(jndiProperties);
		final String earName = "PROYECTO";
		final String ejbModuleName = "PROYECTO-EJB";
		final String beanName = CiudadSession.class.getSimpleName();
		final String fullClassName = CiudadSessionRemote.class.getName();
		String jndiCompleteName = "ejb:" + earName + "/" + ejbModuleName + "/"
				+ beanName + "!" + fullClassName;
		System.out.println("lockup: " + jndiCompleteName);
		return (CiudadSessionRemote) context.lookup(jndiCompleteName);
	}
}