package Presentation;

import Data.Reader;
import Domain.UserData;
import Domain.main;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EditUsersController implements Initializable {

    Collection<UserData> list;
    ObservableList<UserData> details;

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
    private TableView<Domain.UserData> usersTableView;

    @FXML
    private TableColumn<Domain.UserData, String> columnNameTableView;

    @FXML
    private TableColumn<Domain.UserData, String> columnEmailTableView;

    @FXML
    private TableColumn<Domain.UserData, String> columnPasswordTableView;

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

    public void readFile () throws Exception {
        this.list = Files.readAllLines(new File("./src/txtfiles/users/users.txt").toPath()).stream()
                .map(line ->{
                    String[] details = line.split(":");
                    UserData userData = new UserData();
                    userData.setUdEmail(details[1]);
                    userData.setUdPassword(details[2]);
                    userData.setUdFirstName(details[3]);
                    userData.setUdLastName(details[4]);
                    return userData;
                }).collect(Collectors.toList());

        details = FXCollections.observableArrayList(list);
    }



    @Override
    public void initialize (URL location, ResourceBundle resources) {

        try {
            readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        usersTableView.getColumns().addAll(columnNameTableView, columnEmailTableView, columnPasswordTableView);

        columnNameTableView.setCellValueFactory(data -> data.getValue().udFirstNameProperty());
        columnEmailTableView.setCellValueFactory(data -> data.getValue().udEmailProperty());
        columnPasswordTableView.setCellValueFactory(data -> data.getValue().udPasswordProperty());

        usersTableView.setItems(details);


    }


}
