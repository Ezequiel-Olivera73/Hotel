package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Hoteles;
import fes.aragon.modelo.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class HabitacionController extends BaseController implements Initializable {
	private Hotel hotel;
	private String mensajes = "";
	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	private ChoiceBox<String> chcTipo;

	@FXML
	private CheckBox chkRefrigerador;

	@FXML
	private TextField txtCosto;

	@FXML
	private TextField txtNumero;

	@FXML
	void cancelarHabitacion(ActionEvent event) {
		this.cerrarVentana(btnCancelar);
	}

	@FXML
	void nuevaHabitacion(ActionEvent event) {
		if(this.verificar()) {

		Habitacion hab = new Habitacion();
		hab.setNumero(this.txtNumero.getText());
		hab.setCosto(Float.valueOf(txtCosto.getText()));
		hab.setRefrigerador(this.chkRefrigerador.isSelected());
		hab.setTipo(this.chcTipo.getValue());
		if (Hoteles.getInstancia().isModificarHotel()  &&  Hoteles.getInstancia().getIndiceHabitacion() !=-1) {

			hotel.getHabitaciones().set(Hoteles.getInstancia().getIndiceHabitacion(), hab);

		} else {
			hotel.getHabitaciones().add(hab);

		}
		this.cerrarVentana(btnAceptar);
		}else {
			this.ventanaEmergente("Mensaje", "Datos no correctos", this.mensajes);
			this.mensajes="";
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.chcTipo.getItems().addAll("Selecciona un tipo ", " Individual", "Matrimonial", "Mixto");
		this.chcTipo.getSelectionModel().select(0);
		this.verificarEntrada(txtCosto, TipoError.NUMEROS);
		this.verificarEntrada(txtNumero, TipoError.PALABRAS);
		if (Hoteles.getInstancia().isModificarHotel()) {
			// Rellenamos los campos de los datos a cambiar

			this.hotel = Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getIndice());
			int indice=Hoteles.getInstancia().getIndiceHabitacion();
			Habitacion hab= null;
			if(indice==-1) {
				hab= new Habitacion();
				hab.setNumero("Nueva habitacion");
			}else {
				hab=hotel.getHabitaciones().get(Hoteles.getInstancia().getIndiceHabitacion());
			}
			this.txtNumero.setText(hab.getNumero());
			this.txtCosto.setText(String.valueOf(hab.getCosto()));
			this.chkRefrigerador.setSelected(hab.isRefrigerador());
			this.chcTipo.setValue(hab.getTipo());

		} else {
			hotel = Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getGrupoHoteles().size() - 1);
		}
	}

	private boolean verificar() {
		boolean valido = true;
		if ((this.txtNumero.getText() == null) || (this.txtNumero != null && this.txtNumero.getText().isEmpty())) {
			this.mensajes += "El numero de de la habitacion no es valido , complete el campo\n";
			valido = false;
		}
		if ((this.txtNumero.getText() == null) || (this.txtNumero != null && !this.txtNumero.getText().isEmpty())) {
			if (!this.habitacionValido) {
				this.mensajes += "E l numero de la habitacion no es valido, no se permiten espacios\n";
			}
		}
		if ((this.txtCosto.getText() == null) || (this.txtCosto != null && this.txtCosto.getText().isEmpty())) {
			this.mensajes += "El costo de la habitacion no es valido , complete el campo\n";
			valido = false;
		}
		if ((this.txtCosto.getText() == null) || (this.txtCosto != null && !this.txtCosto.getText().isEmpty())) {
			try {
				if (!this.costoValido) {
					throw new NumberFormatException();
				}
				Float.parseFloat(this.txtCosto.getText());
			} catch (NumberFormatException ex) {
				this.mensajes += "El costo no es valido, debe de contener decimales\n";
				valido = false;
			}

		}
		if (this.chcTipo.getSelectionModel().getSelectedIndex() == 0
				|| this.chcTipo.getSelectionModel().getSelectedIndex() == -1) {
			this.mensajes += "Selecciones un tipo de habitación\n";
			valido = false;
		}
		return valido;

	}

}
