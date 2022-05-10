package fes.aragon.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Hoteles;
import fes.aragon.modelo.TipoError;
import fes.aragon.modelo.archivo.HotelArchivo;
import javafx.collections.FXCollections;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//sirve como base para todos los controladores que estamos ocupando
public class BaseController {

	protected boolean rfcValido = true;
	protected boolean correoValido = true;
	protected boolean telefonoValido = true;
	protected boolean habitacionValido = true;
	protected boolean costoValido = true;

	/*
	 * Expresiones regulares 0 palabras sin espacio 1 solo números 2 validar correo
	 * 3 validar telefono
	 */
	private String[] expresiones = { "(\\w+)", 
			"(\\d+)(\\.\\d{1,2})",
			"(\\w){13}",
			"^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
			"(\\d){10}" };

	public void nuevaVentana(String archivo) {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/" + archivo + ".fxml"));
			Scene scene = new Scene(root);
			Stage escenario = new Stage();
			escenario.setScene(scene);
			escenario.initStyle(StageStyle.UNDECORATED);
			escenario.initModality(Modality.APPLICATION_MODAL);
			//Esta linea de codigo permite enviar la ventana a la segunda pantalla
			//escenario.setX(Screen.getPrimary().getVisualBounds().getMaxX());
			escenario.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ventanaEmergente(String titulo , String encabezado , String contenido) {
		Alert alerta;
		alerta= new Alert(AlertType.INFORMATION);
		//Esta linea de codigo permite enviar la ventana a la segunda pantalla
		//alerta.setX(Screen.getPrimary().getVisualBounds().getMaxX()+300);
		alerta.setTitle(titulo);
		alerta.setHeaderText(encabezado);
		alerta.setContentText(contenido);
		alerta.showAndWait();
		
	}
	public void cerrarVentana(Button boton) {
		Stage stage = (Stage) boton.getScene().getWindow();
		stage.close();
		
	}
	//bloque de codigo para mostrar un error , si los datos que agregemos a nuestro hotel son erroneos
public void verificarEntrada(TextField caja, TipoError error) {
	caja.textProperty().addListener(evento ->{
		String text = caja.getText();
		if(text==null) {
			text="";
			
		}
		String patron=expresiones[error.ordinal()];
		Pattern pt=Pattern.compile(patron);
		Matcher match = pt.matcher(text);
		caja.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), !match.matches());
		if(error==TipoError.PALABRAS) {
			this.habitacionValido=match.matches();
		}else if(error==TipoError.NUMEROS) {
			this.costoValido=match.matches();
		}else if(error==TipoError.RFC) {
			this.costoValido=match.matches();
		}else if(error==TipoError.CORREO) {
			this.correoValido=match.matches();
		}else if(error==TipoError.TELEFONO) {
			this.telefonoValido=match.matches();
		}
		
	});
}
	public void abrirArchivo(Button boton) throws IOException, ClassNotFoundException{
		//TENEMOS QUE OBTENER LA ESCENA
		Stage stage = (Stage) boton.getScene().getWindow();
		//Abrimos una ventana de archivos
		FileChooser archivo = new FileChooser();
		archivo.setTitle("Abrir archivo de datos");
		archivo.setInitialDirectory(new File(System.getProperty("user.dir")));
		archivo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos fes", "*.fes"));
		//fileChooser.showOpenDialog(stage);
		 File ruta = archivo.showOpenDialog(stage);
		if(ruta!=null) {
			FileInputStream fi= new FileInputStream(ruta);
			ObjectInputStream entrada = new ObjectInputStream(fi); 	
			ArrayList<HotelArchivo> datos=(ArrayList<HotelArchivo>)entrada.readObject();
			//limpiamos el grupo de hoteles para mostrar los datos
			Hoteles.getInstancia().getGrupoHoteles().clear();
			for (HotelArchivo hotel : datos) {
				Hotel objeto=new Hotel();
				objeto.setNombre(hotel.getNombre());
				objeto.setDireccion(hotel.getDireccion());
				objeto.setCorreo(hotel.getCorreo());
				objeto.setTelefono(hotel.getTelefono());
				objeto.setGerente(hotel.getGerente());
				objeto.setHabitaciones(FXCollections.observableArrayList(hotel.getHabitaciones()));
				Hoteles.getInstancia().getGrupoHoteles().add(objeto);
				
			}
			fi.close();
			entrada.close();
		}

	}
	public void guardarArchivo(Button boton) throws IOException{
		//TENEMOS QUE OBTENER LA ESCENA
		Stage stage = (Stage) boton.getScene().getWindow();
		//Abrimos una ventana de archivos
		FileChooser archivo = new FileChooser();
		archivo.setTitle("Guardar archivo de datos");
		archivo.setInitialDirectory(new File(System.getProperty("user.dir")));
		archivo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos fes", "*.fes"));

		 File ruta= archivo.showSaveDialog(stage);
		if(ruta!=null) {
			FileOutputStream fo=new FileOutputStream(ruta);
			ObjectOutputStream salida= new ObjectOutputStream(fo);
			ArrayList<HotelArchivo> hoteles=new ArrayList<>();
			for (Hotel hotel : Hoteles.getInstancia().getGrupoHoteles()) {
				HotelArchivo objeto=new HotelArchivo();
				objeto.setNombre(hotel.getNombre());
				objeto.setDireccion(hotel.getDireccion());
				objeto.setCorreo(hotel.getCorreo());
				objeto.setTelefono(hotel.getTelefono());
				objeto.setGerente(hotel.getGerente());
				objeto.setHabitaciones(new ArrayList<>(hotel.getHabitaciones()));
				hoteles.add(objeto);
			}
			salida.writeObject(hoteles);
			salida.close();
			fo.close();
		}

	}
	
}

