package com.bolsadeideas.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsadeideas.springboot.form.app.models.entity.Usuario;

@Component
public class UsuarioValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Usuario usuario = (Usuario) target;

		// IDENTIFICADOR
		if (usuario.getIdentificador().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identificador", "NotEmpty.usuario.identificador");
		} else if (!usuario.getIdentificador().matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")) {
			errors.rejectValue("identificador", "Pattern.usuario.identificador");
		}

		// NOMBRE
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre");

		// APELLIDOS
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "NotEmpty.usuario.apellido");

		// USERNAME
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.usuario.username");
		if (usuario.getUsername().length() < 3 || usuario.getUsername().length() > 8) {
			errors.rejectValue("username", "Pattern.usuario.username");
		}

		// PASSWORD
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.usuario.password");
		if (usuario.getPassword().length() < 2 || usuario.getPassword().length() > 5) {
			errors.rejectValue("password", "Pattern.usuario.password");
		}

		// EMAIL
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.usuario.email");

		// CUENTA
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cuenta", "NotEmpty.usuario.cuenta");

		// FECHA
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaNac", "NotEmpty.usuario.fechaNac");

	}

}
