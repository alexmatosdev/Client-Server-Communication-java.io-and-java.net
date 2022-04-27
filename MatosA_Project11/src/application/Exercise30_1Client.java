package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Class: CMSC214 
 * Instructor: Cristopher Fallon
 * Description: (Give a brief description for each Program)
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Alex Matos
*/

public class Exercise30_1Client extends Application{

	DataOutputStream out = null;
	DataInputStream in = null;
	Socket socket = null;

	String host = "localhost";
	int port = 8000;

	@Override
	public void start(Stage primaryStage) {

		BorderPane pane = new BorderPane();
		VBox vBox = new VBox();

		TextField aIRTextField = new TextField();
		TextField yrsTextField = new TextField();
		TextField lATextField = new TextField();
		Button submitButton = new Button("Submit Button");

		HBox hBoxAIR = new HBox(new Label("Annual Interest Rate"),aIRTextField);
		HBox hBoxYrs = new HBox(new Label("Number Of Years"),yrsTextField);
		HBox hBoxLA = new HBox(new Label("Loan Amount"),lATextField,submitButton);
		
		hBoxAIR.setSpacing(8);
		hBoxYrs.setSpacing(8);
		hBoxLA.setSpacing(8);
		
		vBox.getChildren().addAll(hBoxAIR, hBoxYrs, hBoxLA);

		TextArea textArea = new TextArea();
		pane.setTop(vBox);
		pane.setBottom(textArea);

		Scene scene = new Scene(pane, 500, 200);
		primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		try {
			socket = new Socket(host, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		

		submitButton.setOnAction(e -> {
			try {
				double aIR = Double.parseDouble(aIRTextField.getText().trim());
				out.writeDouble(aIR);
				double nYrs = Double.parseDouble(yrsTextField.getText().trim());
				out.writeDouble(nYrs);
				double loanAm = Double.parseDouble(lATextField.getText().trim());
				out.writeDouble(loanAm);
				
				out.flush();
				
				textArea.appendText("Annual Interest Rate " + aIR + " Number of Years " + nYrs + " Loan Amount " + loanAm);
				
				double monPayment = in.readDouble();
				
				textArea.appendText("\nMonthly payment is : " + monPayment);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
