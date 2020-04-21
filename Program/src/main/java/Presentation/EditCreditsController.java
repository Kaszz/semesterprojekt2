package Presentation;


import Domain.Broadcast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditCreditsController implements Initializable {

    ObservableList<Broadcast> creation;

    ArrayList<Broadcast> loadCreations;

    @FXML
    private Button deleteCreditButton;

    @FXML
    private Button editCreditButton;

    @FXML
    private Button createCreditButton;

    @FXML
    private ComboBox creationCombo;

    @FXML
    void createCreditButtonClicked(ActionEvent event) {

    }

    @FXML
    void deleteCreditButtonClicked(ActionEvent event) {

    }

    @FXML
    void editCreditButtonClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creation = FXCollections.observableArrayList();
        loadCreations = new ArrayList<>();

        //App.domain.
    }
}


