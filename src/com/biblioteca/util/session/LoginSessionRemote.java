package com.biblioteca.util.session;

import javax.ejb.Remote;
import org.biblioteca.entidad.Usuario;

@Remote
public interface LoginSessionRemote {
	Usuario validarUsuario(String name, String password);
}
