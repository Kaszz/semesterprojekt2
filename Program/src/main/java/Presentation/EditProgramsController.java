package Presentation;


import Domain.BroadcastType;
import Domain.Episode;
import Interfaces.IBroadcast;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EditProgramsController implements Initializable {
    Collection<ProgramsData> list;
    TreeItem<ProgramsData> details;

    @FXML
    private TreeTableView<ProgramsData> programTreeTableView;

    @FXML
    private TreeTableColumn<ProgramsData, String> titleColumn;

    @FXML
    private TreeTableColumn<ProgramsData, String> subTitleColumn;

    @FXML
    private TreeTableColumn<ProgramsData, String> yearMadeColumn;

    @FXML
    private Label episodeLabel;

    @FXML
    private Label seasonLabel;

    @FXML
    private Label episodeNumberLabel;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private ComboBox<BroadcastType> broadcastTypeComboBox;

    @FXML
    private TextField trailerURLTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField launchDatePicker;

    @FXML
    private TextField episodeTextField;

    @FXML
    private ComboBox<?> seasonComboBox;

    @FXML
    private ComboBox<?> episodeNoComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button deleteButton;


    @FXML
    void broadcastTypeComboBoxOnAction(ActionEvent event) {
        if (broadcastTypeComboBox.getSelectionModel().isSelected(0)) {
            episodeLabel.setText("Episode");
            episodeTextField.setVisible(true); //e
            episodeLabel.setVisible(true); // e
            episodeNoComboBox.setVisible(true); // e
            seasonLabel.setVisible(true); // e
            seasonComboBox.setVisible(true); // e
            episodeNoComboBox.setVisible(true); //e
            episodeNumberLabel.setVisible(true); //e
            locationTextField.setVisible(false);
        } else if (broadcastTypeComboBox.getSelectionModel().isSelected(1)) {
            episodeTextField.setVisible(false);
            episodeLabel.setVisible(false);
            episodeNoComboBox.setVisible(false);
            seasonLabel.setVisible(false);
            seasonComboBox.setVisible(false);
            episodeNoComboBox.setVisible(false);
            episodeNumberLabel.setVisible(false);
            locationTextField.setVisible(false);
        } else if (broadcastTypeComboBox.getSelectionModel().isSelected(2)) {
            episodeLabel.setText("Lokation");
            episodeLabel.setVisible(true);
            locationTextField.setVisible(true);
            episodeTextField.setVisible(false);
            episodeNoComboBox.setVisible(false);
            seasonLabel.setVisible(false);
            seasonComboBox.setVisible(false);
            episodeNoComboBox.setVisible(false);
            episodeNumberLabel.setVisible(false);
        }
    }

    @FXML
    void deleteButtonOnAction(ActionEvent event) {

    }

    @FXML
    void saveButtonOnAction(ActionEvent event) throws MalformedURLException {
        trailerURLTextField.getText();

        if(broadcastTypeComboBox.getSelectionModel().isSelected(1)) {
            App.domain.createMovie(titleTextField.getText(), descriptionTextField.getText(), Year.of(Integer.parseInt(launchDatePicker.getText())));
        }

    }

    @FXML
    void descriptionTextFieldOnAction(ActionEvent event) {

    }

    @FXML
    void episodeNoComboBoxOnAction(ActionEvent event) {

    }

    @FXML
    void episodeTextFieldOnAction(ActionEvent event) {

    }

    @FXML
    void launchDatePickerOnAction(ActionEvent event) {

    }

    @FXML
    void seasonComboBoxOnAction(ActionEvent event) {

    }

    @FXML
    void titleTextFieldOnAction(ActionEvent event) {

    }

    @FXML
    void trailerURLTextFieldOnAction(ActionEvent event) {

    }

    public void getBroadcastData() {
        TreeItem<ProgramsData> root = null;
        TreeItem<ProgramsData> programsData = null;

        ArrayList<IBroadcast> broadcastTitles = App.domain.getAllBroadcasts();

        for (IBroadcast b: broadcastTitles) {
            if (b instanceof Episode) {
                root = new TreeItem<>(new ProgramsData(b.getTitle(), b.getLaunchYear().toString()));
                ArrayList<TreeItem> treeItems = new ArrayList<>();

                programsData = new TreeItem<>(new ProgramsData(b.getTitle(), b.getLaunchYear().toString()));

                treeItems.add(programsData);

                for (int i = 0; i < treeItems.size(); i++) {
                    root.getChildren().add(treeItems.get(i));
                }

                programTreeTableView.setRoot(root);
            } else {
                root = new TreeItem<>(new ProgramsData(b.getTitle(), b.getLaunchYear().toString()));

            }

        }

        /*
        File directory = new File("./src/txtfiles/broadcasts/");
        //Makes array of files in directory.
        File[] files = directory.listFiles();
        Scanner scan = null;
        TreeItem<ProgramsData> root = null;
        TreeItem<ProgramsData> programsData = null;
        ArrayList<TreeItem> treeItems = new ArrayList<>();
        //Iterate through the files in the directory.
        for (File f: files) {
            try {
                scan = new Scanner(f);
                String[] text;
                //Get the title name of current file.
                String firstLine = scan.nextLine();
                text = firstLine.split(":");
                String title = text[0];
                String yearMade = text[3];

                root = new TreeItem<>(new ProgramsData(title, yearMade));

                programsData = new TreeItem<>(new ProgramsData(title, yearMade));

                treeItems.add(programsData);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                scan.close();
            }

        }

         */




        titleColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getTitle());
        yearMadeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getYearMade());

        //programTreeTableView.setShowRoot(false);

        //Adding choice options to the broadcast type combobox
        broadcastTypeComboBox.getItems().setAll(BroadcastType.values());

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getBroadcastData();










    }
}
