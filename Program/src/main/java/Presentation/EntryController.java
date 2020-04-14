package Presentation;

import Interfaces.ILogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EntryController implements Initializable {

    @FXML
    void creditsButtonClicked(ActionEvent event) throws IOException {
    Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditCredits.fxml"));
    Scene tableViewScene = new Scene(tableViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(tableViewScene);
    window.show();

    }

    @FXML
    void programsButtonClicked(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditPrograms.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    void usersButtonClicked(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditUsers.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    void logoutButtonClicked(ActionEvent event) throws IOException {
        App.loginClient.logout();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
