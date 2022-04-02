package fes.aragon.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Hoteles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HotelController implements Initializable {
	private Hotel hotel;
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
    
    	hotel.setNombre(this.txtNombre.getText());
    	hotel.setDireccion(this.txtDireccion.getText());
    	hotel.setCorreo(this.txtCorreo.getText());
    	hotel.setTelefono(this.txtTelefono.getText());
    	System.out.println(hotel.toString());
    	if(Hoteles.getInstancia().isModificarHotel()) {
    		//Se añaden los nuevos datos
    	}else {
    		Hoteles.getInstancia().getGrupoHoteles().set(Hoteles.getInstancia().getGrupoHoteles().size()-1, hotel);
    	}
    	this.cerrar();
    }
    private void nuevaVentana(String archivo) {
    	try {
			Pane root=(Pane)FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/"+archivo+".fxml"));
			Scene scene=new Scene(root);
			Stage escenario=new Stage();
			escenario.setScene(scene);
			escenario.setX(Screen.getPrimary().getVisualBounds().getMaxX());
			escenario.initStyle(StageStyle.UNDECORATED);
			escenario.initModality(Modality.APPLICATION_MODAL);
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(Hoteles.getInstancia().isModificarHotel()) {
			
		}else {
			hotel=Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getGrupoHoteles().size()-1);
		}
	}

}
