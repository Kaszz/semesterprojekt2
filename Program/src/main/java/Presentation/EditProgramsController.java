package Presentation;


import Domain.BroadcastType;
import Domain.Episode;
import Domain.LiveShow;
import Domain.Movie;
import Interfaces.IBroadcast;
import Interfaces.IEpisode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class EditProgramsController implements Initializable {
    private Collection<ProgramsData> list;
    private TreeItem<ProgramsData> details;
    private TreeItem<ProgramsData> root;
    private int previousIndex;

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
        //See selectProgram method. Some kind of identifier should be saved in ProgramData. This could be broadcastID.
        //This can be used to find the correct area in the database.

        ProgramsData broadcast = programTreeTableView.getSelectionModel().getSelectedItem().getValue();

        if (broadcast.isMovie() || broadcast.isLiveShow()) {
            System.out.println(root.getChildren().contains(broadcast));
            System.out.println("Trying to delete: " + broadcast.getTitle().get());
            System.out.println(root.getChildren().contains(broadcast));
        }
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


    @FXML
    void selectProgram(MouseEvent event) {
        int currentIndex = programTreeTableView.getSelectionModel().getFocusedIndex();

        //If the same index is selected multiple times it's deselected. Might not work in all situations
        if (previousIndex == currentIndex) {
            programTreeTableView.getSelectionModel().clearSelection();
            previousIndex = -1; //Ensures that you can pick the same index after clearing
        } else {
            previousIndex = currentIndex;

            //Check if anything is selected
            if (programTreeTableView.getSelectionModel().getSelectedItem() != null) {

                //Only check selection if target has no children i.e is not a season or series.
                System.out.println(programTreeTableView.getSelectionModel().getSelectedItem().getChildren().size());
                if (programTreeTableView.getSelectionModel().getSelectedItem().getChildren().size() == 0) {
                    ProgramsData broadcast = programTreeTableView.getSelectionModel().getSelectedItem().getValue();

                    if (broadcast.isEpisode()) {
                        titleTextField.setText(broadcast.getTitle().get());
                        broadcastTypeComboBox.getSelectionModel().select(0);
                        descriptionTextField.setText(broadcast.getBio());
                        launchDatePicker.setText(broadcast.getYearMade().get());

                    } else if (broadcast.isMovie()) {
                        titleTextField.setText(broadcast.getTitle().get());
                        broadcastTypeComboBox.getSelectionModel().select(1);
                        descriptionTextField.setText(broadcast.getBio());
                        launchDatePicker.setText(broadcast.getYearMade().get());

                    } else if (broadcast.isLiveShow()) {
                        titleTextField.setText(broadcast.getTitle().get());
                        broadcastTypeComboBox.getSelectionModel().select(2);
                        descriptionTextField.setText(broadcast.getBio());
                        launchDatePicker.setText(broadcast.getYearMade().get());
                        locationTextField.setText(broadcast.getLocation());

                    }  else {
                        System.out.println("Nothing found");
                        //Do nothing (it's neither, maybe alert user that it's corrupt?)
                    }
                }
            }
        }
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
        //Creates an initial TreeItem which serves as a root. Every new TreeItem should be added to this one.
        root = new TreeItem<>();

        //An ArrayList containing all broadcasts
        ArrayList<IBroadcast> broadcasts = App.domain.getAllBroadcasts();

        for (IBroadcast b : broadcasts) {

            //Run through the all the series, seasons and episodes. Create any if they haven't already been. Only done for Episodes
            if (b instanceof IEpisode) {
                boolean foundSeries = false;
                int seriesIndex = 0;

                IEpisode episode = ((IEpisode) b);

                //Run through all series and check for existing series
                for (int i = 0; i < root.getChildren().size(); i++) {
                    //If there is a series with the same name as the showName of an episode check to see if its series exists.
                    //To convert a SimpleStringProperty to string use .get()
                    if (root.getChildren().get(i).getValue().getTitle().get().equals(episode.getShowName())) {
                        //If there are no seasons add the one contained in b
                        foundSeries = true;
                        seriesIndex = i;
                        System.out.println("Found existing series for " + episode.getTitle());
                    }
                }

                //If no existing series was found create a new one and find its index.
                if (!foundSeries) {
                    root.getChildren().add(new TreeItem<>(new ProgramsData(episode.getShowName(), "")));

                    for (int i = 0; i < root.getChildren().size(); i++) {
                        if (root.getChildren().get(i).getValue().getTitle().get().equals(episode.getShowName())) {
                            seriesIndex = i;
                            System.out.println("Creating new series for " + episode.getTitle());
                        }
                    }
                }

                //Create a new season if there is none
                if (root.getChildren().get(seriesIndex).getChildren().isEmpty()) {
                    root.getChildren().get(seriesIndex).getChildren().add(new TreeItem<>(new ProgramsData(episode.getSeason(), episode.getLaunchYear().toString())));
                    System.out.println("Series is empty, creating new series for: " + episode.getTitle());
                }

                //Check if there is an existing season
                if (!root.getChildren().get(seriesIndex).getChildren().isEmpty()) {
                    boolean foundSeason = false; //Check if any seasons where found

                    for (int j = 0; j < root.getChildren().get(seriesIndex).getChildren().size(); j++) {
                        if (root.getChildren().get(seriesIndex).getChildren().get(j).getValue().getSeason().equals(Integer.toString(episode.getSeason()))) {
                            root.getChildren().get(seriesIndex).getChildren().get(j).getChildren().add(new TreeItem<>(new ProgramsData(episode.getTitle(), episode.getLaunchYear().toString(),
                                    Integer.toString(episode.getSeason()), Integer.toString(episode.getEpisodeNum()), episode.getShowName(), episode.getBio())));
                            foundSeason = true;
                            System.out.println("Found season for: " + episode.getTitle());
                        }
                    }

                    //If a matching season weren't found create a new one and add the episode
                    if (!foundSeason) {
                        root.getChildren().get(seriesIndex).getChildren().add(new TreeItem<>(new ProgramsData(episode.getSeason(), episode.getLaunchYear().toString())));

                        for (int j = 0; j < root.getChildren().get(seriesIndex).getChildren().size(); j++) {
                            if (root.getChildren().get(seriesIndex).getChildren().get(j).getValue().getSeason().equals(Integer.toString(episode.getSeason()))) {
                                root.getChildren().get(seriesIndex).getChildren().get(j).getChildren().add(new TreeItem<>(new ProgramsData(episode.getTitle(), episode.getLaunchYear().toString(),
                                        Integer.toString(episode.getSeason()), Integer.toString(episode.getEpisodeNum()), episode.getShowName(), episode.getBio())));
                                System.out.println("Added new season and adding " + episode.getTitle());

                            }
                        }


                    }

                }

            //If a broadcast is not an Episode it's simply added.
            } else if (b instanceof Movie){
                root.getChildren().add(new TreeItem<>(new ProgramsData(b.getTitle(), b.getLaunchYear().toString(), b.getBio())));

            } else if (b instanceof LiveShow) {
                root.getChildren().add(new TreeItem<>(new ProgramsData(b.getTitle(), b.getLaunchYear().toString(), b.getBio(), ((LiveShow) b).getLocation())));
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
        }

        titleColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getTitle());
        yearMadeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getYearMade());

        programTreeTableView.setRoot(root);
        //Root should not be visible
        programTreeTableView.setShowRoot(false);

        //Adding choice options to the broadcast type combobox
        broadcastTypeComboBox.getItems().setAll(BroadcastType.values());
        previousIndex = -1;
    }
}
