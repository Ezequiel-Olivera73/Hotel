package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Hoteles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HabitacionController implements Initializable {
	private Hotel hotel;
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
		this.cerrar();
	}

	@FXML
	void nuevaHabitacion(ActionEvent event) {
		// me va a dar el mismo objeto de hotel

		Habitacion hab = new Habitacion();
		hab.setNumero(this.txtNumero.getText());
		hab.setCosto(Float.valueOf(txtCosto.getText()));
		hab.setRefrigerador(this.chkRefrigerador.isSelected());
		hab.setTipo(this.chcTipo.getValue());
		if (Hoteles.getInstancia().isModificarHotel()) {
			// Cambiar el objeto de la lista en su indice
		} else {
			hotel.getHabitaciones().add(hab);

		}
		this.cerrar();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.chcTipo.getItems().addAll("Individual", "Matrimonial", "Mixto");
		if (Hoteles.getInstancia().isModificarHotel()) {
			// Rellenamos los campos de los datos a cambiar
		} else {
			hotel = Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getGrupoHoteles().size() - 1);
		}
	}

	private void cerrar() {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
}
