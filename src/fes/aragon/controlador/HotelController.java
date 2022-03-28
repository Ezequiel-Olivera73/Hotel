package fes.aragon.controlador;

import java.io.IOException;

import fes.aragon.modelo.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HotelController {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGerente;

    @FXML
    private Button btnHabitacion;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    void cancelarHotel(ActionEvent event) {
    	this.cerrar();
    }

    @FXML
    void nuevaHabitacion(ActionEvent event) {
    	this.nuevaVentana("Habitacion");
    }

    @FXML
    void nuevoGerente(ActionEvent event)  {
    	this.nuevaVentana("Gerente");
    }
    
    

    @FXML
    void nuevoHotel(ActionEvent event) {
    	Hotel hotel=Hotel.getHotel();
    	hotel.setNombre(this.txtNombre.getText());
    	hotel.setDireccion(this.txtDireccion.getText());
    	hotel.setCorreo(this.txtCorreo.getText());
    	hotel.setTelefono(this.txtTelefono.getText());
    	System.out.println(hotel.toString());
    	this.cerrar();
    }
    private void nuevaVentana(String archivo) {
    	try {
			Pane root=(Pane)FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/"+archivo+".fxml"));
			Scene scene=new Scene(root);
			Stage escenario=new Stage();
			escenario.setScene(scene);
			escenario.setX(Screen.getPrimary().getVisualBounds().getMaxX());
			//escenario.initStyle(StageStyle.UNDECORATED);
			//escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void cerrar() {
    	Stage stage= (Stage)btnCancelar.getScene().getWindow();
    	stage.close();
    }

}
