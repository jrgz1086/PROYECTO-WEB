package com.biblioteca.util.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.biblioteca.entidad.Usuario;

@Stateless
public class LoginSession implements LoginSessionRemote {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Usuario validarUsuario(String name, String password) {
		Usuario usuario = null;
		try {
			String jpql = "SELECT o FROM Usuario o " + "WHERE o.username='"
					+ name + "' AND " + "o.password='" + password + "'";
			usuario = (Usuario) entityManager.createQuery(jpql, Usuario.class)
					.getSingleResult();
		} catch (NoResultException e) {
		}
		return usuario;
	}
}