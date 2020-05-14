package Presentation;


import Domain.BroadcastType;
import Domain.Episode;
import Domain.LiveShow;
import Domain.Movie;
import Interfaces.IBroadcast;
import Interfaces.IEpisode;
import Interfaces.ILiveShow;
import Interfaces.IMovie;
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
    private Label locationLabel;

    @FXML
    private Label episodeLabel;

    @FXML
    private Label showNameLabel;

    @FXML
    private Label seasonLabel;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField showNameTextField;

    @FXML
    private ComboBox<BroadcastType> broadcastTypeComboBox;

    @FXML
    private TextField episodeTextField;

    @FXML
    private TextField seasonTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField launchDatePicker;


    @FXML
    private Button saveButton;

    @FXML
    private Button deleteButton;


    @FXML
    void broadcastTypeComboBoxOnAction(ActionEvent event) {
        if (broadcastTypeComboBox.getSelectionModel().isSelected(0)) {
            locationLabel.setVisible(false);
            episodeLabel.setVisible(true); // e
            seasonLabel.setVisible(true); // e
            showNameLabel.setVisible(true);
            showNameTextField.setVisible(true);
            episodeTextField.setVisible(true); // e
            seasonTextField.setVisible(true); //e
            locationTextField.setVisible(false);
        } else if (broadcastTypeComboBox.getSelectionModel().isSelected(1)) {
            locationLabel.setVisible(false);
            episodeLabel.setVisible(false);
            seasonLabel.setVisible(false);
            showNameLabel.setVisible(false);
            showNameTextField.setVisible(false);
            episodeTextField.setVisible(false);
            seasonTextField.setVisible(false);
            locationTextField.setVisible(false);
        } else if (broadcastTypeComboBox.getSelectionModel().isSelected(2)) {
            locationLabel.setVisible(true);
            episodeLabel.setVisible(false);
            locationTextField.setVisible(true);
            showNameLabel.setVisible(false);
            showNameTextField.setVisible(false);
            seasonLabel.setVisible(false);
            episodeTextField.setVisible(false);
            seasonTextField.setVisible(false);
        }
    }

    @FXML
    void deleteButtonOnAction(ActionEvent event) { //TODO : change to use ID instead of title
        //Amount of children on the selected row.
        int childAmount = programTreeTableView.getSelectionModel().getSelectedItem().getChildren().size();
        //If the selected row is a whole series or season.
        if (childAmount != 0) {
            System.out.println("Cant be deleted");
        }
        //If the selected row is a broadcast and therefore deletable
        else {
            ProgramsData selectedBroadcast = programTreeTableView.getSelectionModel().getSelectedItem().getValue();

            if (!selectedBroadcast.isEpisode()) {
                App.domain.deleteBroadcast(selectedBroadcast.broadcastID, selectedBroadcast.getTitle().get());
            }
            else {
                App.domain.deleteEpisode(selectedBroadcast.broadcastID, selectedBroadcast.getTitle().get());
            }

            update();
        }
    }

    @FXML
    void saveButtonOnAction(ActionEvent event) throws MalformedURLException {

        BroadcastType type = broadcastTypeComboBox.getSelectionModel().getSelectedItem();

        if (type.name().equals("SERIE")) {
            App.domain.createEpisode(titleTextField.getText(), descriptionTextField.getText(),
                    Year.of(Integer.parseInt(launchDatePicker.getText())), showNameTextField.getText(),
                    Integer.parseInt(seasonTextField.getText()), Integer.parseInt(episodeTextField.getText()), App.loginClient.getAccount().getUserID());
        }
        else if (type.name().equals("FILM")) {
            App.domain.createMovie(titleTextField.getText(), descriptionTextField.getText(),
                    Year.of(Integer.parseInt(launchDatePicker.getText())), App.loginClient.getAccount().getUserID());
        }
        else if (type.name().equals("LIVE")) {
            App.domain.createLiveShow(titleTextField.getText(), descriptionTextField.getText(),
                    Year.of(Integer.parseInt(launchDatePicker.getText())), locationTextField.getText(), App.loginClient.getAccount().getUserID());
        }

        update();
    }

    @FXML
    void descriptionTextFieldOnAction(ActionEvent event) {

    }

    @FXML
    void episodeNoComboBoxOnAction(ActionEvent event) {

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
                //System.out.println(programTreeTableView.getSelectionModel().getSelectedItem().getChildren().size());
                if (programTreeTableView.getSelectionModel().getSelectedItem().getChildren().size() == 0) {
                    ProgramsData broadcast = programTreeTableView.getSelectionModel().getSelectedItem().getValue();

                    if (broadcast.isEpisode()) {
                        titleTextField.setText(broadcast.getTitle().get());
                        broadcastTypeComboBox.getSelectionModel().select(0);
                        descriptionTextField.setText(broadcast.getBio());
                        launchDatePicker.setText(broadcast.getYearMade().get());
                        showNameTextField.setText(broadcast.getShowName());
                        episodeTextField.setText(broadcast.getEpisodeNum());
                        seasonTextField.setText(broadcast.getSeason());

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        update();

        //Adding choice options to the broadcast type combobox
        broadcastTypeComboBox.getItems().setAll(BroadcastType.values());
        previousIndex = -1;
    }


    void update() {
        //Creates an initial TreeItem which serves as a root. Every new TreeItem should be added to this one.
        root = new TreeItem<>();


        //An ArrayList containing all broadcasts
        ArrayList<IBroadcast> broadcasts = App.domain.getAllBroadcasts();

        for (IBroadcast b : broadcasts) {
            //Checks if broadcast object belongs to logged in user. Skips if it doesnt.
            //If logged in as admin it never skips.
            if (b.getUserID() != App.loginClient.getAccount().getUserID() && !App.loginClient.isAdmin())
                continue;
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
                        //System.out.println("Found existing series for " + episode.getTitle());
                    }
                }

                //If no existing series was found create a new one and find its index.
                if (!foundSeries) {
                    root.getChildren().add(new TreeItem<>(new ProgramsData(episode.getShowName(), "")));

                    for (int i = 0; i < root.getChildren().size(); i++) {
                        if (root.getChildren().get(i).getValue().getTitle().get().equals(episode.getShowName())) {
                            seriesIndex = i;
                            //System.out.println("Creating new series for " + episode.getTitle());
                        }
                    }
                }

                //Create a new season if there is none
                if (root.getChildren().get(seriesIndex).getChildren().isEmpty()) {
                    root.getChildren().get(seriesIndex).getChildren().add(new TreeItem<>(new ProgramsData(episode.getSeason(), episode.getLaunchYear().toString())));
                    //System.out.println("Series is empty, creating new series for: " + episode.getTitle());
                }

                //Check if there is an existing season
                if (!root.getChildren().get(seriesIndex).getChildren().isEmpty()) {
                    boolean foundSeason = false; //Check if any seasons where found

                    for (int j = 0; j < root.getChildren().get(seriesIndex).getChildren().size(); j++) {
                        if (root.getChildren().get(seriesIndex).getChildren().get(j).getValue().getSeason().equals(Integer.toString(episode.getSeason()))) {
                            root.getChildren().get(seriesIndex).getChildren().get(j).getChildren().add(new TreeItem<>(new ProgramsData(episode.getTitle(), episode.getLaunchYear().toString(),
                                    Integer.toString(episode.getSeason()), Integer.toString(episode.getEpisodeNum()), episode.getShowName(), episode.getBio(), episode.getBroadcastID())));
                            foundSeason = true;
                            //System.out.println("Found season for: " + episode.getTitle());
                        }
                    }

                    //If a matching season weren't found create a new one and add the episode
                    if (!foundSeason) {
                        root.getChildren().get(seriesIndex).getChildren().add(new TreeItem<>(new ProgramsData(episode.getSeason(), episode.getLaunchYear().toString())));

                        for (int j = 0; j < root.getChildren().get(seriesIndex).getChildren().size(); j++) {
                            if (root.getChildren().get(seriesIndex).getChildren().get(j).getValue().getSeason().equals(Integer.toString(episode.getSeason()))) {
                                root.getChildren().get(seriesIndex).getChildren().get(j).getChildren().add(new TreeItem<>(new ProgramsData(episode.getTitle(), episode.getLaunchYear().toString(),
                                        Integer.toString(episode.getSeason()), Integer.toString(episode.getEpisodeNum()), episode.getShowName(), episode.getBio(), episode.getBroadcastID())));
                                //System.out.println("Added new season and adding " + episode.getTitle());

                            }
                        }


                    }

                }
                //If a broadcast is not an Episode it's simply added.
            } else if (b instanceof IMovie) {
                root.getChildren().add(new TreeItem<>(new ProgramsData(b.getTitle(), b.getLaunchYear().toString(), b.getBio(), b.getBroadcastID())));

            } else if (b instanceof ILiveShow) {
                ILiveShow liveShow = ((ILiveShow) b);
                root.getChildren().add(new TreeItem<>(new ProgramsData(liveShow.getTitle(), liveShow.getLaunchYear().toString(), liveShow.getBio(), liveShow.getLocation(), b.getBroadcastID())));
            }

        }
        titleColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getTitle());
        yearMadeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProgramsData, String> param) -> param.getValue().getValue().getYearMade());

        programTreeTableView.setRoot(root);
        //Root should not be visible
        programTreeTableView.setShowRoot(false);

    }

}
