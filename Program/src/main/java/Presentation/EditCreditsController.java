package Presentation;


import Domain.BroadcastType;
import Domain.CreditType;
import Interfaces.IBroadcast;
import Interfaces.ICredit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditCreditsController implements Initializable {
    ArrayList<IBroadcast> broadcasts;

    @FXML
    private Button deleteCreditButton;

    @FXML
    private Button editCreditButton;

    @FXML
    private Button createCreditButton;

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

    }

    @FXML
    void deleteCreditButtonClicked(ActionEvent event) {

    }

    @FXML
    void editCreditButtonClicked(ActionEvent event) {

    }

    public void updateCredits() {
        //Saves the chosen elements index from the ComboBox (drop down menu)
        int index = creditsTable.getSelectionModel().getSelectedIndex();

        ArrayList<String> rawCredits;

        for (int i = 0; i < broadcasts.size(); i++) {
            rawCredits = App.domain.getBroadcastCredits(broadcasts.get(i).getTitle());
            broadcasts.get(i).deleteAllCredits();

            if (!rawCredits.isEmpty()) {
                for (int j = 0; j < rawCredits.size(); j++) {
                    String[] creditSplit = rawCredits.get(j).split(":");
                    broadcasts.get(i).addCredit(broadcasts.get(i).getTitle(), creditSplit[0], creditSplit[1], CreditType.valueOf(creditSplit[2]));
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
        ArrayList<String> rawBroadcasts;
        broadcasts = new ArrayList<>();

        rawBroadcasts = App.domain.getAllBroadcasts();
        creations = FXCollections.observableArrayList();

        for (int i = 0; i < rawBroadcasts.size(); i++) {
            String[] broadcast = rawBroadcasts.get(i).split(":");

            if (broadcast.length <= 6) {
                try {
                    broadcasts.add(App.domain.createMovie(broadcast[0],new URL(broadcast[1] + ":" + broadcast[2]), broadcast[3],
                            Year.of(Integer.parseInt(broadcast[4]))));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (broadcast.length == 7) {
                try {
                    broadcasts.add(App.domain.createLiveShow(broadcast[0],new URL(broadcast[1] + ":" + broadcast[2]), broadcast[3],
                            Year.of(Integer.parseInt(broadcast[4])), broadcast[5]));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (broadcast.length >= 8) {
                try {
                    broadcasts.add(App.domain.createEpisode(broadcast[0],new URL(broadcast[1] + ":" + broadcast[2]), broadcast[3],
                            Year.of(Integer.parseInt(broadcast[4])), broadcast[5], Integer.parseInt(broadcast[6]), Integer.parseInt(broadcast[7])));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }

        columnFirstName.setCellValueFactory(new PropertyValueFactory<CreditTable, String>("fName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<CreditTable, String>("lName"));
        columnRole.setCellValueFactory(new PropertyValueFactory<CreditTable, String>("role"));

        creations.setAll(broadcasts);
        creationCombo.setItems(creations);
        System.out.println(CreditType.SUPPORT_CAST);
        creditCombo.getItems().setAll(CreditType.values());

        if (!broadcasts.isEmpty()) {
            creationCombo.getSelectionModel().select(0);
            updateCredits();
        }

    }
}


