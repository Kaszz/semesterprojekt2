package Presentation;

import Interfaces.IAccount;
import Interfaces.IUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BasicFrameworkController implements Initializable  {


    @FXML
    private SplitMenuButton splitMenu;

    @FXML
    private MenuItem splitMenuUsers, splitMenuPrograms, splitMenuCredits, splitMenuLogOut;

    @FXML
    private Circle redCircle;

    @FXML
    private Label countLabel;

    @FXML
    private Button notificationButton;

    private Thread threadNotificationUpdate;

    boolean status = false;
    
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

        Stage window = (Stage)splitMenu.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();


    }

    @FXML
    void creditsChoiceClicked(ActionEvent event) throws IOException{

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditCredits.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)splitMenu.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    void programsChoiceClicked(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditPrograms.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)splitMenu.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    
    @FXML
    void logoutChoiceClicked(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)splitMenu.getScene().getWindow();

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



    public void updateNotificationFlag() {

        if (status) {
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
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            threadNotificationUpdate.setDaemon(true);
            threadNotificationUpdate.start();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        status = App.domain.isAdmin();
        System.out.println("Admin status: " + status);

        if (!status) {
            splitMenuUsers.setVisible(false);
            redCircle.setVisible(false);
            countLabel.setVisible(false);
            notificationButton.setVisible(false);
            nameLoggedInLabel.setText(App.loginClient.getAccount().getFirstName() + " " + App.loginClient.getAccount().getLastName());
        }
        else {
            splitMenuUsers.setVisible(true);
            nameLoggedInLabel.setText("Admin");
            updateNotificationFlag();
        }
    }
}
