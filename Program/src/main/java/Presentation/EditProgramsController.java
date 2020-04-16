package Presentation;


import Domain.ProgramsData;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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










    public void getBroadcastData2() throws IOException {
        File directory = new File("./src/txtfiles/broadcasts/");
        File[] files = directory.listFiles();

        //Iterate through the files in the directory.
        for (int i = 0; i < files.length ; i++) {
            try {
                this.list = Files.readAllLines(new File("./src/txtfiles/broadcasts/").toPath()).stream()
                        .map(line -> {
                            String[] details = line.split(":");
                            ProgramsData programsData = new ProgramsData();
                            programsData.setTitle(details[0]);
                            programsData.setYearMade(details[3]);
                            programsData.setSeason(details[5]);
                            programsData.setEpisode(details[6]);
                            return programsData;
                        }).collect(Collectors.toList());

                details = (TreeItem<ProgramsData>) FXCollections.observableArrayList(list);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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

        root.getChildren().setAll(getBroadcastData());
        programTreeTableView.setRoot(root);
        //programTreeTableView.setShowRoot(false);










    }
}
