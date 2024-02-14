package daw.dwes.ud4;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Datos {
	
	@Min(value = (long) 1.40, message = "Altura mayor o igual a 1.40")
	private double altura;
	
	@Max(value = 100)
	@Min(value = 45)
	private double peso;

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	

}
