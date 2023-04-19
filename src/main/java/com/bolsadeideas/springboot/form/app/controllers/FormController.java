package com.bolsadeideas.springboot.form.app.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.models.entity.Usuario;

@Controller
@SessionAttributes("usuario")
public class FormController {

	// Dos metodos handler
	// Uno para mostrar el formulario (GET)
	@GetMapping({ "/", "/form" })
	public String form(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("titulo", "Hola soy un titulo XD");
		
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

		if (result.hasErrors()) {
			
			return "form";
		}

		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Area de resultado");
		status.setComplete();

		return "resultado";
	}
}
