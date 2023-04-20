package com.bolsadeideas.springboot.form.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.models.entity.Usuario;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {

	//Se agrega un comentario
	//Se agrega otro comentario
	@Autowired
	UsuarioValidador validador;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
	}
	
	// Dos metodos handler
	// Uno para mostrar el formulario (GET)
	@GetMapping({ "/", "/form" })
	public String form(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("titulo", "FORMULARIO");
		
		usuario.setNombre("Andres");
		usuario.setApellido("Prado");
		usuario.setIdentificador("12.134.123-K");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	// Uno para procesar lo que escribio el usuario (POST)
	// RequestParam, captura datos que fueron redirigidos por otra pagina
	@PostMapping("/form")
	public String procesarFormulario(@Valid Usuario usuario, BindingResult result, SessionStatus status, Model model) {

		model.addAttribute("titulo", "FORM RESULTADO");		
		
		if (result.hasErrors()) {
			
			return "form";
		}

		model.addAttribute("usuario", usuario);
		
		status.setComplete();

		return "resultado";
	}
}
