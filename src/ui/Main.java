package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is in charge to show the user interface to use the program
 * @author Santiago Valencia Ramirez
 */

public class Main extends Application{
	
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("screen.fxml"));
		Parent root = loader.load();
		
		ControllerClass pc = loader.getController();
		pc.setStage(stage);
		
       	Scene scene= new Scene(root);
       	stage.setTitle("Catch the Pac-Man");
       	stage.setScene(scene);
       	stage.show();
		
	}
	

}
