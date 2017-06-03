package com.biblioteca.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("NombreClienteValidator")
public class NombreClienteValidator implements Validator {
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null) {
			return;
		}
		String nombreCliente = (String) value;
		if (nombreCliente.length() <= 10 || nombreCliente.length() > 30) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Longitud de Campo debe estar entre 10 y 30 caracteres",
					"y"));
		}
	}
}