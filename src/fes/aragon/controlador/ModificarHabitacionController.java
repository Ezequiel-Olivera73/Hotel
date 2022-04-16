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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModificarHabitacionController extends BaseController implements Initializable {
	private Hotel hotel;

	@FXML
	private Button btnModificar;

	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnNuevo;

	@FXML
	private TableColumn<Habitacion, Float> clmCosto;

	@FXML
	private TableColumn<Habitacion, String> clmNumero;

	@FXML
	private TableColumn<Habitacion, Boolean> clmRefrigerador;

	@FXML
	private TableColumn<Habitacion, String> clmTipo;

	@FXML
	private TableView<Habitacion> tblHabitacion;

	@FXML
	void modificar(ActionEvent event) {
		int indice = this.tblHabitacion.getSelectionModel().getSelectedIndex();
		if (indice >= 0) {
			Hoteles.getInstancia().setIndiceHabitacion(indice);
			this.nuevaVentana("Habitacion");
		} else {
			super.ventanaEmergente("Informacion", "Seleccionar una fila", "Seleecione la fila que desee modificar");
		}
	}

	@FXML
	void cancelar(ActionEvent event) {
		super.cerrarVentana(btnCancelar);
	}

	@FXML
	void nuevo(ActionEvent event) {
		Hoteles.getInstancia().setIndiceHabitacion(-1);
		this.nuevaVentana("Habitacion");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.clmNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
		this.clmCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
		this.clmRefrigerador.setCellValueFactory(new PropertyValueFactory<>("refrigerador"));
		this.clmTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		this.hotel = Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getIndice());
		this.tblHabitacion.setItems(hotel.getHabitaciones());
	}

}
