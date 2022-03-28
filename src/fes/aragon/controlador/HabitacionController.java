package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HabitacionController implements Initializable {

	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	private ChoiceBox<String> chcTipo;

	@FXML
	private CheckBox chkRefrigedor;

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
		//me va a dar el mismo objeto de hotel
		Hotel hotel=Hotel.getHotel();
		Habitacion hab= new Habitacion();
		hab.setNumero(this.txtNumero.getText());
		hab.setCosto(Float.valueOf(txtCosto.getText()));
		hab.setRefrigedor(this.chkRefrigedor.isSelected());
		hab.setTipo(this.chcTipo.getValue());
		hotel.getHabitaciones().add(hab);
		System.out.println(hotel.getHabitaciones().get(hotel.getHabitaciones().size()-1));
		this.cerrar();
		}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.chcTipo.getItems().addAll("Individual", "Matrimonial", "Mixto");
	}

	private void cerrar() {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
}
