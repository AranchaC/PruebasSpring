package daw.dwes.ud4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.validation.Valid;


@Controller
public class FormController implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/resultado").setViewName("resultado");
	}
	
	@GetMapping("/formPrueba")
	public String mostrarForm(Model model) {
		model.addAttribute("datos", new Datos());
		return "formulario";
	}

	
	@PostMapping("/formPrueba")
	public String enviarDatos(
			//@ModelAttribute Datos datos, 
			Model model,
			@Valid Datos datos,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "formulario";
		}
		double masa = datos.getPeso() / datos.getAltura() * datos.getAltura();
		String clasif = "tienes que ponerte fit";
		model.addAttribute("clasif",clasif);
		model.addAttribute("masa",masa);
		return "resultado";
		
	}
}
		