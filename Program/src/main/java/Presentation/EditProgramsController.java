package Presentation;


import Domain.ProgramsData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EditProgramsController implements Initializable {


    @FXML
    private TreeTableView<ProgramsData> programTreeTableView;

    @FXML
    private TreeTableColumn<ProgramsData, String> titleColumn;

    @FXML
    private TreeTableColumn<ProgramsData, String> subTitleColumn;

    @FXML
    private TreeTableColumn<ProgramsData, String> yearMadeColumn;










    public void getBroadcastData() {
        File directory = new File("./src/txtfiles/broadcasts/");
        //Makes array of files in directory.
        File[] files = directory.listFiles();
        String[] text;
        Scanner scan = null;
        ArrayList<String> returnList = new ArrayList<String>();

        //Iterate through the files in the directory.
        for (File f: files) {
            try {
                scan = new Scanner(f);

                //Get the title name of current file.
                String firstLine = scan.nextLine();

                text = firstLine.split(":");

                ProgramsData programsData = new ProgramsData();
                programsData.setTitle(text[0]);
                programsData.setYearMade(text[3]);
                programsData.setSeason(text[5]);
                programsData.setEpisode(text[6]);


                /*
                String titleNew = text[0];
                String yearMade = text[3];
                String season = text[5];
                String episode = text[6];


                String returnString = titleNew + " " + yearMade + " " + season + " " + episode;
                returnList.add(returnString);
                */

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                scan.close();
            }
        }

        //return returnList;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getBroadcastData();

        //Add TreeTableColumn to TreeTableView
        TreeTableView<ProgramsData> treeTableView = new TreeTableView<ProgramsData>();

        titleColumn.setCellValueFactory(data -> data.getValue().());
        yearMadeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("yearMade"));

        treeTableView.getColumns().add(titleColumn);
        treeTableView.getColumns().add(yearMadeColumn);

        columnNameTableView.setCellValueFactory(data -> data.getValue().udFirstNameProperty());
        columnEmailTableView.setCellValueFactory(data -> data.getValue().udEmailProperty());
        columnPasswordTableView.setCellValueFactory(data -> data.getValue().udPasswordProperty());








    }
}
