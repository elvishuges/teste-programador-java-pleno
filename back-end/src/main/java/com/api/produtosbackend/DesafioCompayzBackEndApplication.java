package com.api.produtosbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
public class DesafioCompayzBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioCompayzBackEndApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Elvis Huges é o melhor bom ";
	}

}
