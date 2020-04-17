package Presentation;

import Interfaces.INotification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NotificationsController implements Initializable {

    @FXML private TableView<Noti> tableView;
    @FXML private TableColumn<Noti, String> dateColumn;
    @FXML private TableColumn<Noti, String> userColumn;
    @FXML private TableColumn<Noti, String> changeColumn;

    ArrayList<INotification> notifications;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set up the columns in the table
        dateColumn.setCellValueFactory(new PropertyValueFactory<Noti, String>("date"));
        userColumn.setCellValueFactory(new PropertyValueFactory<Noti, String>("user"));
        changeColumn.setCellValueFactory(new PropertyValueFactory<Noti, String>("change"));

        updateNotifications();
    }

    //Method that changes the style of rows that have not been marked as seen yet.
    public void setUnreadStyle(TableColumn currentColumn) {
        currentColumn.setCellFactory(column -> {
            return new TableCell<Noti, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //If the cell is empty
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty
                        //Put the String data in the cell
                        setText(item);

                        //Gets to object of the row.
                        Noti notif = getTableView().getItems().get(getIndex());

                        //Change style of all lines that havent been seen yet.
                        if (!notif.isSeen()) {
                            setStyle("-fx-background-color: #EBEBEB ; -fx-font-weight: bold; "); //The background of the cell in yellow
                        }
                    }
                }
            };
        });
    }

    //Method that fills an observablelist with all the notifications and returns the observablelist.
    public ObservableList<Noti> getNotifications() {
        ObservableList<Noti> list = FXCollections.observableArrayList();
        notifications = App.domain.getNotifications();

        //Traverses the notifications backwards to get the most recent ones on top.
        for (int i = notifications.size() - 1; i >= 0; i--)
            list.add(new Noti(notifications.get(i).isSeen(), notifications.get(i).getDate(),
                    notifications.get(i).getUser(), notifications.get(i).getChange()));

        return list;
    }

    //Method that updates the notifications once a change has happened.
    public void updateNotifications() {
        int index = tableView.getSelectionModel().getSelectedIndex();

        //Load the data
        tableView.setItems(getNotifications());

        //Reselect the clicked row
        tableView.getSelectionModel().select(index);

        //Styles the rows
        setUnreadStyle(dateColumn);
        setUnreadStyle(userColumn);
        setUnreadStyle(changeColumn);
    }

    //Method that handles deselecting
    public void handleRowClicked(MouseEvent mouseEvent) {
        Noti temp = tableView.getSelectionModel().getSelectedItem();
        if (!temp.isSeen()) {
            App.domain.unNotify(temp.isSeen(), temp.getDate(), temp.getUser(), temp.getChange());
            updateNotifications();
        }
    }

}