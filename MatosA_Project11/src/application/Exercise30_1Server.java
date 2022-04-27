package application;

/*
 * Class: CMSC214 
 * Instructor: Cristopher Fallon
 * Description: (Give a brief description for each Program)
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Alex Matos
*/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Exercise30_1Server extends Application{

	DataOutputStream out;
	DataInputStream in;
	ServerSocket serverSocket;
	Socket socket;
	int port = 8000;
	String local = "localHost";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane pane = new BorderPane();
		TextArea textArea = new TextArea();

		Scene scene = new Scene(textArea, 500, 200);
		primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		new Thread( () -> {
			try {
				serverSocket = new ServerSocket(port);
				socket = serverSocket.accept();

				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());

				while (true) {

					double aIR = in.readDouble();
					double nYrs = in.readDouble();
					double loanAm = in.readDouble();

					double a = (loanAm*aIR);
					double b = (nYrs*12);
					double monPayment = a / b;

					out.writeDouble(monPayment);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
