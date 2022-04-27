package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/*
 * Class: CMSC214 
 * Instructor: Cristopher Fallon
 * Description: (Give a brief description for each Program)
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Alex Matos
*/

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Exercise30_1Server server = new Exercise30_1Server();
		server.start(primaryStage);
		Exercise30_1Client client = new Exercise30_1Client();
		client.start(primaryStage);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
