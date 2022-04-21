package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Hoteles;
import fes.aragon.modelo.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GerenteController  extends BaseController  implements Initializable {
	private Hotel hotel;
	private String mensajes="";
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
		this.cerrarVentana(btnCancelar);
	}

	@FXML
	void nuevoGerente(ActionEvent event) {
		// Patron singlenton

		if(this.verificar()) {
		hotel.getGerente().setApellidoMaterno(this.txtApellidoMaterno.getText());
		hotel.getGerente().setApellidoPaterno(this.txtApellidoPaterno.getText());
		hotel.getGerente().setRfc(this.txtRfc.getText());
		hotel.getGerente().setCorreo(this.txtCorreo.getText());
		hotel.getGerente().setTelefono(this.txtTelefono.getText());
		hotel.getGerente().setNombre(this.txtNombre.getText());
		this.cerrarVentana(btnAceptar);
		}else {
			this.ventanaEmergente("Mensaje","Datos no correctos", this.mensajes);
			this.mensajes="";
		}
	}

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.verificarEntrada(txtRfc, TipoError.RFC);
		this.verificarEntrada(txtCorreo, TipoError.CORREO);
		this.verificarEntrada(txtTelefono, TipoError.TELEFONO);
		
		if (Hoteles.getInstancia().isModificarHotel()) {
			// rellenar datos
			this.hotel=Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getIndice());
			this.txtNombre.setText(hotel.getGerente().getNombre());
			this.txtApellidoPaterno.setText(hotel.getGerente().getApellidoPaterno());
			this.txtApellidoMaterno.setText(hotel.getGerente().getApellidoMaterno());
			this.txtRfc.setText(hotel.getGerente().getRfc());
			this.txtCorreo.setText(hotel.getGerente().getCorreo());
			this.txtTelefono.setText(hotel.getGerente().getTelefono());
			
		} else {
			hotel= Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getGrupoHoteles().size() - 1);
		}
	}
	
	
	private boolean verificar() {
		boolean valido=true;
		if((this.txtNombre.getText()==null)||(this.txtNombre.getText() != null && this.txtNombre.getText().isEmpty())) {
			this.mensajes +="El nombre del gerente no es valido , complete el espacio\n";
			valido=false;
		}
		if((this.txtApellidoPaterno.getText()==null ) || (this.txtApellidoPaterno.getText().isEmpty())) {
			this.mensajes += "El apellido paterno no es valido , complete el espacio\n";
		}
		if((this.txtApellidoMaterno.getText()==null) || (this.txtApellidoMaterno.getText() !=null && this.txtApellidoMaterno.getText().isEmpty())) {
			this.mensajes += "El apellido materno no es valido , complete el espacio\n";
			valido=false;
		}
		if((this.txtRfc.getText()==null) || (this.txtRfc.getText() !=null && this.txtRfc.getText().isEmpty())) {
			this.mensajes += "El RFC del gerente no es valido , complete el espacio\n";
			valido=false;
		}
		if((this.txtRfc.getText()==null) || (this.txtRfc.getText() !=null && !this.txtRfc.getText().isEmpty())){
			if(!this.rfcValido) {
				this.mensajes += "El RFC del gerente no es valido , minimo=13 , maximo=13 caracteres\n";
				valido=false;
			}
		}
		if((this.txtCorreo.getText()==null) || (this.txtCorreo.getText() !=null && this.txtCorreo.getText().isEmpty())) {
			this.mensajes += "El correo del gerente no es valido , complete el espacio";
			valido=false;
		}
		if((this.txtCorreo.getText()==null ) || (this.txtCorreo.getText() !=null && !this.txtCorreo.getText().isEmpty())) {
			if(!this.correoValido) {
				this.mensajes += "El correo del gerente no es valido , esta mal estructurado";
				valido=false;
			}
		}
		if((this.txtTelefono.getText()==null) || (this.txtTelefono.getText() !=null && this.txtTelefono.getText().isEmpty())) {
			this.mensajes += "El telefono del gerente no es valido , complete el espacio ";
			valido=false;
		}
		if((this.txtTelefono.getText()==null) || (this.txtTelefono.getText()!=null && !this.txtTelefono.getText().isEmpty())) {
			if(!this.telefonoValido) {
				this.mensajes += "El telefono del gerente no es valido, minimo 10 digitos , maximo 10 digitos";
				valido=false;
			}
		}
		return valido;
	}
}
