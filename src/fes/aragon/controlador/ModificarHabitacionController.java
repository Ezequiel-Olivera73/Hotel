package fes.aragon.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.scene.control.ButtonType.OK;
import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Hoteles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ModificarHabitacionController implements Initializable {
	private Hotel hotel;
	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

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
	void aceptar(ActionEvent event) {
		int indice = this.tblHabitacion.getSelectionModel().getSelectedIndex();
		if (indice >= 0) {
			Hoteles.getInstancia().setIndiceHabitacion(indice);
			this.nuevaVentana("Habitacion");
		} else {
			Alert alerta;
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("AVISO");
			alerta.setHeaderText("Seleccione una fila");
			alerta.setContentText("Seleccione el hotel que desea modificar");
			Optional<ButtonType> resultado = alerta.showAndWait();
			if (resultado.get().equals(OK));
		}
	}

	@FXML
	void cancelar(ActionEvent event) {
		Stage stage = (Stage)btnCancelar.getScene().getWindow();
		stage.close();
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

	private void nuevaVentana(String archivo) {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/" + archivo + ".fxml"));
			Scene scene = new Scene(root);
			Stage escenario = new Stage();
			escenario.setScene(scene);
			escenario.initStyle(StageStyle.UNDECORATED);
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.setX(Screen.getPrimary().getVisualBounds().getMaxX());
			escenario.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
