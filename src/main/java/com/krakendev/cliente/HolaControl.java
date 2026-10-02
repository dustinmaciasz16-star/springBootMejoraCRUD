package com.krakendev.cliente;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaControl {
	
	@GetMapping("/hola")
	public String saludo()	{
		return "hola desde Spring Boot";
	}

}
