package fes.aragon.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Hoteles {
	private static Hoteles instancia = new Hoteles();
	// va a guardar todos los hoteles

	private ObservableList<Hotel> grupoHoteles = FXCollections.observableArrayList();
	private boolean modificarHotel = false;
	private int indice = -1;
	private int indiceHabitacion = -1;

	private Hoteles() {
		// estamos creando un hotel
		
		/*
		Hotel h = new Hotel();
		h.setNombre("Dato1");
		h.setDireccion("Dato2");
		h.setCorreo("@demo");
		h.setTelefono("33333445");
		Gerente g = new Gerente();
		g.setNombre("Zeki");
		g.setRfc("332ss");
		g.setTelefono("22222");
		h.setGerente(g);
		Habitacion hab = new Habitacion();
		hab.setNumero("A1");
		hab.setCosto(23.3f);
		hab.setRefrigerador(true);
		hab.setTipo("Mixto");
		h.getHabitaciones().add(hab);

		// se agrego un hotel al grupo hoteles
		 * 
		 
		this.grupoHoteles.add(h);
		*/
	}

	public static Hoteles getInstancia() {
		return instancia;
	}

	public boolean isModificarHotel() {
		return modificarHotel;
	}

	public void setModificarHotel(boolean modificarHotel) {
		this.modificarHotel = modificarHotel;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getIndiceHabitacion() {
		return indiceHabitacion;
	}

	public void setIndiceHabitacion(int indiceHabitacion) {
		this.indiceHabitacion = indiceHabitacion;
	}

	public ObservableList<Hotel> getGrupoHoteles() {
		return grupoHoteles;
	}

	public void setGrupoHoteles(ObservableList<Hotel> grupoHoteles) {
		this.grupoHoteles = grupoHoteles;
	}

}
