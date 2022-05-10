package fes.aragon;
	
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//esta linea indica que va a abrir
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/inicio.fxml"));
			//Le estas indicado un panel
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/fes/aragon/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			//primaryStage.setX(Screen.getPrimary().getVisualBounds().getMaxX());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//comentario
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
