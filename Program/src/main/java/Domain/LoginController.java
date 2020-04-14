package Domain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Domain.AccountTest;
import Interfaces.IReader;
import Data.Reader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Login implements Initializable {



    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label displayErrorMessage;

    @FXML
    private Button loginButton;



    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException {


        try {
            Login.login(usernameTextField.getText(), passwordTextField.getText());
            if(Login.isloggedIn()) {
                displayErrorMessage.setText("Logged ind");
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("Entry.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
            } else {
                displayErrorMessage.setText("Forkert brugernavn eller password");
            }
        } catch(IllegalArgumentException e) {

        }

    }

    @FXML
    public void passwordAdded() {

    }

    @FXML
    public void usernameAdded() {

    }

    public void checkuserType() {

    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
