package Presentation;


import Domain.ProgramsData;
import Domain.UserData;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TreeItem<ProgramsData> mercedes1 = new TreeItem<>(new ProgramsData("hej", "hej"));
        TreeItem<ProgramsData> mercedes2 = new TreeItem<>(new ProgramsData("hej", "hej"));

        TreeItem<ProgramsData> root = new TreeItem<>(new ProgramsData("Title", "YearMade"));

        titleColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getTitle());
        yearMadeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getYearMade());

        root.getChildren().setAll(mercedes1,mercedes2);
        programTreeTableView.setRoot(root);
        //programTreeTableView.setShowRoot(false);










    }
}
