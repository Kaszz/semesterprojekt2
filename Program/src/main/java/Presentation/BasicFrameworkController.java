package Presentation;

import Interfaces.IAccount;
import Interfaces.IUser;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
    private MenuItem splitMenuUsers;

    @FXML
    private Circle redCircle;

    @FXML
    private Label countLabel;

    @FXML
    private Button notificationButton;

    @FXML
    private Label nameLoggedInLabel;

    private static Thread notificationThread;

    static int count = 0;

    static boolean started = false;

    boolean status = false;
    ExecutorService executor = Executors.newFixedThreadPool(1);
    int tester = 0;
    
    @FXML
    void splitMenuButtonClicked(ActionEvent event) throws IOException {
        NotificationsController.stopUpdateSeenThread();

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Entry.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void usersChoiceClicked(ActionEvent event) throws IOException {
        NotificationsController.stopUpdateSeenThread();

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditUsers.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)splitMenu.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();


    }

    @FXML
    void creditsChoiceClicked(ActionEvent event) throws IOException{
        NotificationsController.stopUpdateSeenThread();

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditCredits.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)splitMenu.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    void programsChoiceClicked(ActionEvent event) throws IOException {
        NotificationsController.stopUpdateSeenThread();

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditPrograms.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)splitMenu.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    
    @FXML
    void logoutChoiceClicked(ActionEvent event) throws IOException {
        NotificationsController.stopUpdateSeenThread();

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)splitMenu.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    void notificationsButtonClicked(ActionEvent event) throws IOException {

        NotificationsController.stopUpdateSeenThread();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Notifications.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    public void updateNotificationFlag() {
        //If admin is logged in
        if (status) {
            started = true;

            //Defines task the thread shall run
            Task task = new Task<Void>() {
                @Override
                public Void call() throws Exception {
                    while (true) {
                        Platform.runLater(new Runnable() {
                            @Override
                            //The actual run method which the thread will run.
                            public void run() {
                                //Updates notification count.
                                count = App.domain.notificationCount();
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
                        Thread.sleep(1000);
                    }
                }
            };
            //gives task to thread, sets it daemon and starts it.
            notificationThread = new Thread(task);
            notificationThread.setDaemon(true);
            notificationThread.start();

        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        status = App.domain.isAdmin();
        System.out.println("Admin status: " + status);
        
        if (started) {
            notificationThread.interrupt();
            started = false;
        }

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
