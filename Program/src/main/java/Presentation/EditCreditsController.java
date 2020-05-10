package Presentation;


import Domain.BroadcastType;
import Domain.Credit;
import Domain.CreditType;
import Interfaces.IBroadcast;
import Interfaces.ICredit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditCreditsController implements Initializable {
    ArrayList<IBroadcast> allBroadcasts;
    ArrayList<IBroadcast> myBroadcasts;

    @FXML
    private Button deleteCreditButton;

    @FXML
    private Button editCreditButton;

    @FXML
    private Button createCreditButton;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private ComboBox creationCombo;

    @FXML
    private ComboBox<CreditType> creditCombo;

    @FXML
    private TableView<CreditTable> creditsTable;

    @FXML
    private TableColumn<CreditTable, String> columnFirstName;

    @FXML
    private TableColumn<CreditTable, String> columnLastName;

    @FXML
    private TableColumn<CreditTable, String> columnRole;

    @FXML
    void createCreditButtonClicked(ActionEvent event) {
        ICredit credit = App.domain.createCredit(firstName.getText(), lastName.getText(), creditCombo.getSelectionModel().getSelectedItem());
        IBroadcast broadcast = (IBroadcast) creationCombo.getSelectionModel().getSelectedItem();

        App.domain.addCredit(broadcast.getTitle(), credit);
        updateCredits();
    }

    @FXML
    void deleteCreditButtonClicked(ActionEvent event) {
        if (creditsTable.getSelectionModel().getSelectedItem() != null) {
            CreditTable creditTable = creditsTable.getSelectionModel().getSelectedItem();
            IBroadcast broadcast = (IBroadcast) creationCombo.getSelectionModel().getSelectedItem();

            ICredit credit = App.domain.createCredit(creditTable.getfName(), creditTable.getlName(), creditTable.getRole());
            App.domain.deleteCredit(broadcast.getTitle(), credit);
            updateCredits();
        }
    }

    public void updateCredits() {
        //Saves the chosen elements index from the ComboBox (drop down menu)
        int index = creditsTable.getSelectionModel().getSelectedIndex();

        ArrayList<String> rawCredits;

        for (int i = 0; i < myBroadcasts.size(); i++) {
            rawCredits = App.domain.getBroadcastCredits(myBroadcasts.get(i).getTitle());
            myBroadcasts.get(i).deleteAllCredits();

            if (!rawCredits.isEmpty()) {
                for (int j = 0; j < rawCredits.size(); j++) {
                    String[] creditSplit = rawCredits.get(j).split(":");
                    myBroadcasts.get(i).addCredit(myBroadcasts.get(i).getTitle(), creditSplit[0], creditSplit[1], CreditType.valueOf(creditSplit[2]));
                }
            }
        }
        creditsTable.setItems(getCredits());
        creditsTable.getSelectionModel().select(index);
    }

    public ObservableList<CreditTable> getCredits() {
        ObservableList<CreditTable> creditList = FXCollections.observableArrayList();
        IBroadcast broadcast = (IBroadcast) creationCombo.getSelectionModel().getSelectedItem();
        ArrayList<ICredit> credits = App.domain.getCredits(broadcast);

        for (int i = 0; i < credits.size() ; i++) {
            creditList.add(new CreditTable(credits.get(i).getfName(), credits.get(i).getlName(), credits.get(i).getRole().name()));
        }

        return creditList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<IBroadcast> creations;

        myBroadcasts = new ArrayList<>();
        allBroadcasts = App.domain.getAllBroadcasts();

        for (IBroadcast b : allBroadcasts) {
            if (b.getUserID() != App.loginClient.getAccount().getUserID() && !App.loginClient.isAdmin())
                continue;
            myBroadcasts.add(b);
        }



        creations = FXCollections.observableArrayList();

        columnFirstName.setCellValueFactory(new PropertyValueFactory<CreditTable, String>("fName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<CreditTable, String>("lName"));
        columnRole.setCellValueFactory(new PropertyValueFactory<CreditTable, String>("role"));

        creations.setAll(myBroadcasts);
        creationCombo.setItems(creations);
        System.out.println(CreditType.SUPPORT_CAST);
        creditCombo.getItems().setAll(CreditType.values());

        if (!myBroadcasts.isEmpty()) {
            creationCombo.getSelectionModel().select(0);
            updateCredits();
        }

    }
}


