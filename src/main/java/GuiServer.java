
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiServer extends Application{

	//private TextArea messages = new TextArea();
	//private TextField input = new TextField();
	//private Button sendToAll = new Button("Send to All");
	//private Button sendToClients = new Button("Send to Clients");
	//private TextField targetClients = new TextField("Enter Client and your ID");
	private Client connection;
//
	
	
	public static void main(String[] args) {
		//Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//messages.setPrefHeight(550);
		//messages.setEditable(false);
//
		//VBox root = new VBox(8);
		//root.setAlignment(Pos.CENTER);
		//root.setStyle("-fx-background-color: LIGHTBLUE;");
		//root.getChildren().addAll(messages, input);
//
		//HBox buttons =  new HBox(8);
		//buttons.setAlignment(Pos.CENTER);
		//buttons.getChildren().addAll(sendToAll, sendToClients, targetClients);
//
		//root.getChildren().add(buttons);
//
		//Label infoLabel = new Label("Type your message and click 'Send to All' to send to everyone. " +
		//		"To send to specific clients, type their client numbers separated by commas in the text box next to 'Send to Clients' button and click it.");
		//infoLabel.setWrapText(true);
		//root.getChildren().add(infoLabel);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/clientfxml.fxml"));
		Parent root = loader.load();
		root.setStyle("-fx-font-family: Arial");
		clientController controller = loader.getController();
		controller.initialize(this);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Client GUI");
		primaryStage.show();

		primaryStage.setOnCloseRequest(t -> {
			Platform.exit();
			System.exit(0);
		});
	}


	public void initializeConnection(clientController controller) {
		connection = new Client(data -> {
			controller.getMessages().appendText(data.toString() + "\n");
		});
		connection.start();
	}

	public void sendMessage(String message) {
		if (!message.isEmpty()) {
			connection.send(message);
		}
	}


	public void sendSpecificClients(String message, String targets) {
		if (!message.isEmpty() && !targets.isEmpty()) {
			//The array below captures every client number into an array of integers.
			int[] targetArray = Arrays.stream(targets.split(","))
					.mapToInt(Integer::parseInt).toArray();
			connection.sendTo(message, targetArray);
		}
	}
}
