package fes.aragon.modelo;

public class Habitacion {
	private String numero;
	private float costo;
	private boolean refrigedor;
	private String tipo;

	public Habitacion() {

	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public boolean isRefrigedor() {
		return refrigedor;
	}

	public void setRefrigedor(boolean refrigedor) {
		this.refrigedor = refrigedor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Habitacion [numero=" + numero + ", costo=" + costo + ", refrigedor=" + refrigedor + ", tipo=" + tipo
				+ "]";
	}

}
