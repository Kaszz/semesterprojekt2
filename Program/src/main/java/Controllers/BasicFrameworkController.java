package Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BasicFrameworkController implements Initializable {


    @FXML
    private Label nameLoggedInLabel;

    @FXML
    private MenuButton menuButton;


    @FXML
    void homeButtonClicked(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Entry.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void menuButtonClicked(ActionEvent event) {

    }
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

        MenuButton menuButton = new MenuButton("Options");


        //Creating menu items
        MenuItem users = new MenuItem("Brugere");
        MenuItem programs = new MenuItem("Programmer");
        MenuItem credits = new MenuItem("Kreditteringer");

        //Adding menu items to dropdown






    }

}
