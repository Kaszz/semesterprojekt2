package Presentation;

import Data.Reader;
import Domain.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUsersController implements Initializable {

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
    private TreeTableView<?> usersTreeTableView;

    @FXML
    private TreeTableColumn<?, ?> nameUserTreeTableView;

    @FXML
    private TreeTableColumn<?, ?> emailUserTreeTableView;

    @FXML
    private TreeTableColumn<?, ?> passwordUserTreeTableView;

    @FXML
    void changeUserButtonClicked(ActionEvent event) {

    }

    @FXML
    void createUserButtonClicked(ActionEvent event) {

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

        TreeTableColumn<>

    }
}
