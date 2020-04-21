package Presentation;


import Domain.BroadcastType;
import Domain.ProgramsData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            episodeTextField.setVisible(true); //e
            episodeLabel.setVisible(true); // e
            episodeNoComboBox.setVisible(true); // e
            seasonLabel.setVisible(true); // e
            seasonComboBox.setVisible(true); // e
            episodeNoComboBox.setVisible(true); //e
            episodeNumberLabel.setVisible(true); //e
        } else {
            episodeTextField.setVisible(false);
            episodeLabel.setVisible(false);
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
            App.domain.createMovie(titleTextField.getText(), new URL(trailerURLTextField.getText()), descriptionTextField.getText(), Year.of(Integer.parseInt(launchDatePicker.getText())));
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

    /*

    public void updatePrograms() {
        int index = updatePrograms().getSelectionModel().getSelectedIndex();
        programTreeTableView().setItems(getBroadcastData());
        programTreeTableView().getSelectionModel().select(index);
    }*/

    public TreeItem<ProgramsData> getBroadcastData() {
        File directory = new File("./src/txtfiles/broadcasts/");
        //Makes array of files in directory.
        File[] files = directory.listFiles();
        String[] text;
        Scanner scan = null;

        //Iterate through the files in the directory.
        for (File f: files) {
            try {
                scan = new Scanner(f);

                //Get the title name of current file.
                String firstLine = scan.nextLine();
                text = firstLine.split(":");
                String title = text[0];
                String yearMade = text[3];
                TreeItem<ProgramsData> programsData = new TreeItem<>(new ProgramsData(title,yearMade));
                return programsData;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                scan.close();
            }
        }

        return null;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TreeItem<ProgramsData> mercedes1 = new TreeItem<>(new ProgramsData("hej", "hej"));
        //TreeItem<ProgramsData> mercedes2 = new TreeItem<>(new ProgramsData("hej", "hej"));

        TreeItem<ProgramsData> root = new TreeItem<>(new ProgramsData("Title", "YearMade"));

        titleColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getTitle());
        yearMadeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getYearMade());

        System.out.println(getBroadcastData().getValue().getTitle());
        System.out.println(getBroadcastData().getValue().getYearMade());

        root.getChildren().setAll(getBroadcastData());
        programTreeTableView.setRoot(root);
        //programTreeTableView.setShowRoot(false);

        //Adding choice options to the broadcast type combobox
        broadcastTypeComboBox.getItems().setAll(BroadcastType.values());






    }
}
