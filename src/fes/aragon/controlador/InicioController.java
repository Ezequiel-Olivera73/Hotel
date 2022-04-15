package fes.aragon.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Hoteles;
import javafx.application.Platform;

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
import static javafx.scene.control.ButtonType.OK;
public class InicioController implements Initializable {

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnModificar;

	@FXML
	private Button btnNuevo;

	@FXML
	private Button btnSalir;

	@FXML
	private TableColumn<Hotel, String> clmCorreo;

	@FXML
	private TableColumn<Hotel, String> clmDireccion;

	@FXML
	private TableColumn<Hotel, String> clmGerente;

	@FXML
	private TableColumn<Hotel, String> clmNombre;

	@FXML
	private TableColumn<Hotel, String> clmTelefono;

	@FXML
	private TableView<Hotel> tblTabla;

	@FXML
	void eliminarHotel(ActionEvent event) {
		//codigo para eliminar los hoteles que seleccionemos
		
		int indice = this.tblTabla.getSelectionModel().getSelectedIndex();
		if(indice>0) {
			this.tblTabla.getItems().remove(indice);
		}else {
			//Alerta de que no a seleccionado un hotel
			Alert alerta;
			alerta=new Alert(AlertType.INFORMATION);
			alerta.setTitle("AVISO");
			alerta.setHeaderText("Seleccione una fila");
			alerta.setContentText("Seleccione el hotel que desea modificar");
			Optional<ButtonType> resultado=alerta.showAndWait();
			if(resultado.get().equals(OK));{
				
			}
		}
	}

	@FXML
	void modificarHotel(ActionEvent event) {
		//indica que fila esta señalando el usuario para modificar
		int indice=this.tblTabla.getSelectionModel().getSelectedIndex();
		if(indice>=0) {
			Hoteles.getInstancia().setModificarHotel(true);
			Hoteles.getInstancia().setIndice(indice);
			this.nuevaVentana("Hotel");
		}else {
			Alert alerta;
			alerta=new Alert(AlertType.INFORMATION);
			alerta.setTitle("AVISO");
			alerta.setHeaderText("Seleccione una fila");
			alerta.setContentText("Seleccione el hotel que desea modificar");
			Optional<ButtonType> resultado=alerta.showAndWait();
			if(resultado.get().equals(OK));
		}
	}

	@FXML
	void nuevoHotel(ActionEvent event) {
		Hoteles.getInstancia().setIndice(-1);
		Hoteles.getInstancia().setModificarHotel(false);
		Hoteles.getInstancia().setIndiceHabitacion(-1);
		Hoteles.getInstancia().getGrupoHoteles().add(new Hotel());
		this.nuevaVentana("Hotel");
	}

	@FXML
	void salir(ActionEvent event) {
		Platform.exit();
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


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.clmNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.clmDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));

		this.clmCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
		this.clmTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		this.clmGerente.setCellValueFactory(new PropertyValueFactory<>("gerente"));
		this.tblTabla.setItems(Hoteles.getInstancia().getGrupoHoteles());
	}
}
