package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Hoteles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GerenteController implements Initializable {
	private Hotel hotel;
	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	private TextField txtApellidoMaterno;

	@FXML
	private TextField txtApellidoPaterno;

	@FXML
	private TextField txtCorreo;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtRfc;

	@FXML
	private TextField txtTelefono;

	@FXML
	void cancelarGerente(ActionEvent event) {

	}

	@FXML
	void nuevoGerente(ActionEvent event) {
		// Patron singlenton

		
		hotel.getGerente().setApellidoMaterno(this.txtApellidoMaterno.getText());
		hotel.getGerente().setApellidoPaterno(this.txtApellidoPaterno.getText());
		hotel.getGerente().setRfc(this.txtRfc.getText());
		hotel.getGerente().setCorreo(this.txtCorreo.getText());
		hotel.getGerente().setTelefono(this.txtTelefono.getText());
		hotel.getGerente().setNombre(this.txtNombre.getText());
		this.cerrar();
	}

	private void cerrar() {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if (Hoteles.getInstancia().isModificarHotel()) {
			// rellenar datos
		} else {
			hotel= Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getGrupoHoteles().size() - 1);
		}
	}

}
