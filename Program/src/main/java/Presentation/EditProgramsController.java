package Presentation;


import Domain.BroadcastType;
import Domain.Episode;
import Interfaces.IBroadcast;
import javafx.beans.property.SimpleStringProperty;
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

        if (broadcastTypeComboBox.getSelectionModel().isSelected(1)) {
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

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<ProgramsData> root = new TreeItem<>();

        ArrayList<IBroadcast> broadcasts = App.domain.getAllBroadcasts();

        for (IBroadcast b : broadcasts) {
            //Run through the all the series, seasons and episodes. Create any if they haven't already been.

            if (b instanceof Episode) {
                boolean foundSeries = false;
                int seriesIndex = 0;

                //Run through all series
                for (int i = 0; i < root.getChildren().size(); i++) {
                    System.out.println("Size is: " + root.getChildren().size());
                    //If there is a series with the same name as the showName of an episode check to see if its series exists.
                    if (root.getChildren().get(i).getValue().getTitle().get().equals(((Episode) b).getShowName())) {
                        //If there are no seasons add the one contained in b
                        foundSeries = true;
                        seriesIndex = i;
                        System.out.println("Found existing series for " + b.getTitle());
                    }
                }

                if (!foundSeries) {
                    root.getChildren().add(new TreeItem<>(new ProgramsData(((Episode) b).getShowName(), "")));
                    System.out.println(root.getChildren().get(0).getValue().getTitle());

                    for (int i = 0; i < root.getChildren().size(); i++) {
                        //To convert a SimpleStringProperty to string use .get()
                        if (root.getChildren().get(i).getValue().getTitle().get().equals(((Episode) b).getShowName())) {
                            seriesIndex = i;
                            System.out.println("Creating new series for " + b.getTitle());
                        }
                    }
                }

                //Create a new series if there is none
                if (root.getChildren().get(seriesIndex).getChildren().isEmpty()) {
                    root.getChildren().get(seriesIndex).getChildren().add(new TreeItem<>(new ProgramsData(((Episode) b).getSeason(), b.getLaunchYear().toString())));
                    System.out.println("Series is empty, creating new series for: " + b.getTitle());
                }

                //Check if there is an existing season
                if (!root.getChildren().get(seriesIndex).getChildren().isEmpty()) {
                    boolean foundSeason = false; //Check if any seasons where found

                    for (int j = 0; j < root.getChildren().get(seriesIndex).getChildren().size(); j++) {
                        if (root.getChildren().get(seriesIndex).getChildren().get(j).getValue().getSeason().equals(Integer.toString(((Episode) b).getSeason()))) {
                            root.getChildren().get(seriesIndex).getChildren().get(j).getChildren().add(new TreeItem<>(new ProgramsData(b.getTitle(), b.getLaunchYear().toString())));
                            foundSeason = true;
                            System.out.println("Found season for: " + b.getTitle());
                        }

                    }

                    //If a matching season weren't found create a new one and add the episode
                    if (!foundSeason) {
                        root.getChildren().get(seriesIndex).getChildren().add(new TreeItem<>(new ProgramsData(((Episode) b).getSeason(), b.getLaunchYear().toString())));

                        for (int j = 0; j < root.getChildren().get(seriesIndex).getChildren().size(); j++) {
                            if (root.getChildren().get(seriesIndex).getChildren().get(j).getValue().getSeason().equals(((Episode) b).getSeason())) {
                                root.getChildren().get(seriesIndex).getChildren().get(j).getChildren().add(new TreeItem<>(new ProgramsData(b.getTitle(), b.getLaunchYear().toString())));
                                System.out.println("Added new season and adding " + b.getTitle());

                            }
                        }


                    }

                }

            } else {
                root.getChildren().add(new TreeItem<>(new ProgramsData(b.getTitle(), b.getLaunchYear().toString())));
            }

                /*
                root.getChildren().

                        //root = new TreeItem<>(new ProgramsData(b.getTitle(), b.getLaunchYear().toString()));
                        ArrayList < TreeItem > treeItems = new ArrayList<>();

                programsData = new TreeItem<>(new ProgramsData(b.getTitle(), b.getLaunchYear().toString()));

                treeItems.add(programsData);

                for (int i = 0; i < treeItems.size(); i++) {
                    root.getChildren().add(treeItems.get(i));
                }

                 */

            titleColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getTitle());
            yearMadeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getYearMade());

            programTreeTableView.setRoot(root);
            programTreeTableView.setShowRoot(false);
            //programTreeTableView.setShowRoot(false);

            //Adding choice options to the broadcast type combobox
            broadcastTypeComboBox.getItems().setAll(BroadcastType.values());


        }
    }
}
