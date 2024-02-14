package daw.dwes.prueba;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

@Controller
public class QuizPruebaController {
	
	@GetMapping("/")
	public String inicio(HttpSession session, Model model) {
		Resultado resultado = new Resultado();
		session.setAttribute("resultado", resultado);
		return "inicio";	
	}
	
	@GetMapping("/pregunta1")
	public String pagina1() {
		return "pregunta1";
	}
	
	@PostMapping("/pregunta1")
	public String pagina1(
			@RequestParam(name = "respuesta") String respuesta,
			HttpSession session,
			Model model) {
		int puntos = 0;
		if (respuesta.equals("azul")) {
			puntos = 4;
		}if (respuesta.equals("verde")) {
			puntos = 3;
		}if (respuesta.equals("morado")) {
			puntos = 2;
		}if (respuesta.equals("rojo")) {
			puntos = 1;
		}
		Resultado resultado = (Resultado) session.getAttribute("resultado");
		resultado.setPuntos(resultado.getPuntos()+ puntos);
        model.addAttribute("resultado", resultado);
		return "pregunta2";
	}
	
	@PostMapping("/pregunta2")
	public String pagina2(
			HttpSession session,
			Model model,
			@RequestParam(name = "respuesta") String respuesta) {
		int puntos = 0;
		if (respuesta.equals("rojo")) {
			puntos = 1;
		}if (respuesta.equals("verde")) {
			puntos = 4;
		}if (respuesta.equals("morado")) {
			puntos = 3;
		}if (respuesta.equals("rojo")) {
			puntos = 2;
		}
		Resultado resultado = (Resultado) session.getAttribute("resultado");
		resultado.setPuntos(resultado.getPuntos()+puntos);
		model.addAttribute(resultado);
		return "pregunta3";
	}
	
	@PostMapping("/pregunta3")
	public String pagina3 (
			HttpSession session,
			Model model,
			@RequestParam(name="respuesta") String respuesta){
		
		int puntos = 0;
		switch(respuesta) {
			case "rojo": puntos=2;  break;
			case "azul": puntos=1; break;
			case "verde": puntos=3; break;
			case "morado": puntos=4; break;
			default: break;
		}
		Resultado resultado = (Resultado) session.getAttribute("resultado");
		resultado.setPuntos(resultado.getPuntos()+puntos);
		resultado.setCategoria(obtenerCategoria(resultado.getPuntos()));
		model.addAttribute(resultado);
		
		return "finalResultado";
				
	}
	
	private Categoria obtenerCategoria(int puntos) {
		if (puntos >= 13) {
			return Categoria.AZUL;
		} else if (puntos >= 9) {
			return Categoria.MORADO;
		} else if (puntos >= 6) {
			return Categoria.ROJO;
		} else {
			return Categoria.VERDE;
		}
	}
	
	
	private Resultado obtenerResultado(HttpSession session) {
		Resultado resultado = (Resultado) session.getAttribute("resultado");
		if (resultado == null) {
			resultado = new Resultado();
			session.setAttribute("resultado", resultado);		
		}
		return resultado;
		
	}

}
