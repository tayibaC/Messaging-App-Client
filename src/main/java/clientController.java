import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class clientController {

        public void initialize(GuiServer mainApp) {
                this.main = mainApp;
                mainApp.initializeConnection(this);
        }

        public TextArea getMessages() {
                return messages;
        }

        private GuiServer main;
        private Client connection;
        @FXML
        private HBox buttons;

        @FXML
        private Label infoLabel;

        @FXML
        private TextField input;

        @FXML
        private TextArea messages;

        @FXML
        private VBox root;

        @FXML
        private Button sendToAll;

        @FXML
        private Button sendToClients;

        @FXML
        private TextField targetClients;

        public void getSendToAll(ActionEvent actionEvent) {
                String message = input.getText();
                main.sendMessage(message);
                input.clear();
        }

        public void sendSpecificClients(ActionEvent actionEvent) {
                String message = input.getText();
                String targets = targetClients.getText();
                main.sendSpecificClients(message, targets);
                input.clear();
                targetClients.clear();
        }
}

