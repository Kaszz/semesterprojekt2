package Presentation;

import Interfaces.ILogin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BasicFrameworkController implements Initializable  {

    @FXML
    private MenuItem usersOptionClicked;

    @FXML
    private SplitMenuButton spitMenuButton;


    @FXML
    void splitMenuButtonClicked(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Entry.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    void usersChoiceClicked(ActionEvent event) throws IOException {


        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditUsers.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)spitMenuButton.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();


    }

    @FXML
    void creditsChoiceClicked(ActionEvent event) throws IOException{

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditCredits.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)spitMenuButton.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    void programsChoiceClicked(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditPrograms.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)spitMenuButton.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    void logoutChoiceClicked(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)spitMenuButton.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    private Label nameLoggedInLabel;


    @FXML
    void notificationsButtonClicked(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Notifications.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameLoggedInLabel.setText(App.loginClient.getUser().getFirstName() + " " + App.loginClient.getUser().getLastName());


    }

}
