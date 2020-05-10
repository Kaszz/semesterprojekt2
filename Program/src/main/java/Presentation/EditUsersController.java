package Presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
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
    private Button editUserButton;

    @FXML
    private Button createUserButton;

    @FXML
    private TableView<UserData> tableView;

    @FXML
    private TableColumn<UserData, String> columnNameTableView;

    @FXML
    private TableColumn<UserData, String> columnEmailTableView;

    @FXML
    private TableColumn<UserData, String> columnPasswordTableView;

    @FXML
    void createUserButtonClicked(ActionEvent event){
        App.domain.createUser(userEmailTextField.getText(), userPasswordTextField.getText(), userFirstNameTextField.getText(), userLastNameTextField.getText());
        updateUsers();
    }

    @FXML
    void deleteUserButtonClicked(ActionEvent event) {
        UserData temp = tableView.getSelectionModel().getSelectedItem();
        App.domain.deleteUser(temp.getUdID(), temp.getUdEmail(), temp.getUdPassword(), temp.getUdFirstName(), temp.getUdLastName(), temp.getUdEnabled());
        updateUsers();
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

    public ObservableList<UserData> getUsers() {
        ObservableList<UserData> details = FXCollections.observableArrayList();
        ArrayList<String> users = App.domain.getAllUsers();

        //Traverses the users
        String[] data = null;
        for (String s : users) {
            data = s.split(":");
            details.add(new UserData(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5]));
        }

        return details;
    }

    public void updateUsers() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        tableView.setItems(getUsers());
        tableView.getSelectionModel().select(index);
    }


    @Override
    public void initialize (URL location, ResourceBundle resources) {
        columnNameTableView.setCellValueFactory(new PropertyValueFactory<UserData, String>("udFirstName"));
        columnEmailTableView.setCellValueFactory(new PropertyValueFactory<UserData, String>("udEmail"));
        columnPasswordTableView.setCellValueFactory(new PropertyValueFactory<UserData, String>("udPassword"));

        updateUsers();
    }


}
