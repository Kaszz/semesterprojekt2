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
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
    private TextField titleTextField;

    @FXML
    private ComboBox<BroadcastType> broadcastTypeComboBox;

    @FXML
    private TextField trailerURLTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private DatePicker launchDatePicker;

    @FXML
    private TextField episodeTextField;

    @FXML
    private ComboBox<?> seasonComboBox;

    @FXML
    private ComboBox<?> episodeNoComboBox;

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


        titleColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getTitle());
        yearMadeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getYearMade());

        for (int i = 0; i < treeItems.size(); i++) {
            root.getChildren().add(treeItems.get(i));
        }

        programTreeTableView.setRoot(root);
        //programTreeTableView.setShowRoot(false);


        //Adding choice options to the broadcast type combobox
        broadcastTypeComboBox.getItems().setAll(BroadcastType.values());

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getBroadcastData();










    }
}
