package com.biblioteca.util.mbean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.biblioteca.entidad.Usuario;
import com.biblioteca.util.session.LoginSessionRemote;

@ManagedBean(name = "loginMBean")
public class LoginMBean implements Serializable {
	@EJB
	private LoginSessionRemote lfr;
	private String username;
	private String password;
	boolean isLoginPage = (FacesContext.getCurrentInstance().getViewRoot()
			.getViewId().lastIndexOf("login.xhtml") > -1);

	public String verificarLogin() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		Usuario usuario = null;
		try {
			usuario = lfr.validarUsuario(getUsername(), getPassword());
		} catch (Exception e) {
			FacesContext
					.getCurrentInstance()
					.getApplication()
					.getNavigationHandler()
					.handleNavigation(FacesContext.getCurrentInstance(), null,
							"/pages/login/login.xhtml");
		}
		if (isLoginPage && usuario != null) {
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("username", username);
			return "LOGIN_OK";
		} else {
			return "LOGIN_NOK";
		}
	}

	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null,
						"/pages/login/login.xhtml");
	}

	public void inicio() {
		FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null,
						"/pages/templates/template.xhtml");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}