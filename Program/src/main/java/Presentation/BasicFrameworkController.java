package Presentation;

import javafx.application.Platform;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BasicFrameworkController implements Initializable  {


    @FXML
    private Label nameLoggedInLabel;

    @FXML
    private MenuButton menuButton;

    @FXML
    private Circle redCircle;
    @FXML
    private Label countLabel;

    private Thread threadNotificationUpdate;

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

    public void updateNotificationFlag() {
        threadNotificationUpdate = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            int count = App.domain.notificationCount();
                            if (count > 0) {
                                redCircle.setVisible(true);
                                countLabel.setVisible(true);
                                countLabel.setText(Integer.toString(count));

                            } else {
                                redCircle.setVisible(false);
                                countLabel.setVisible(false);
                            }
                        }
                    });

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadNotificationUpdate.setDaemon(true);
        threadNotificationUpdate.start();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameLoggedInLabel.setText(App.loginClient.getUser().getFirstName() + " " + App.loginClient.getUser().getLastName());

        MenuButton menuButton = new MenuButton("Options");


        //Creating menu items
        MenuItem users = new MenuItem("Brugere");
        MenuItem programs = new MenuItem("Programmer");
        MenuItem credits = new MenuItem("Kreditteringer");



        //Adding menu items to dropdown

        //Action to menu items

        updateNotificationFlag();





    }
}
