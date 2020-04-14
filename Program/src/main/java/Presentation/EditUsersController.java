package Presentation;

import Interfaces.IAdmin;
import Interfaces.IUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUsersController implements Initializable {
    IAdmin admin = App.domain.createAdmin("Chris", "1234", "Christopher", "chris@chokolade.com");

    @FXML
    private TextField userFirstNameTextField;

    @FXML
    private TextField userLastNameTextField;

    @FXML
    private TextField userEmailTextField;

    @FXML
    private TextField userPasswordTextField;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button changeUserButton;

    @FXML
    private Button createUserButton;

    @FXML
    private ListView<?> usersListView;

    @FXML
    void changeUserButtonClicked(ActionEvent event) {

    }

    @FXML
    void createUserButtonClicked(ActionEvent event) {
        System.out.println(admin.getName());
        IUser user = App.domain.createUser("3", userEmailTextField.getText(), userPasswordTextField.getText(), userFirstNameTextField.getText(), userLastNameTextField.getText());
        System.out.println(user.getName() + user.getEmail() + user.getUserID());
        admin.createUser("4", userEmailTextField.getText(), userPasswordTextField.getText(), userFirstNameTextField.getText() + userLastNameTextField.getText(), userEmailTextField.getText());
    }

    @FXML
    void deleteUserButtonClicked(ActionEvent event) {

    }

    @FXML
    void userEmailEntered(ActionEvent event) {

    }

    @FXML
    void userFirstNameEntered(ActionEvent event) {

    }

    @FXML
    void userLastNameEntered(ActionEvent event) {

    }

    @FXML
    void userPasswordEntered(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}