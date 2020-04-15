package Presentation;

import Domain.Notification;
import Interfaces.INotification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class NotificationsController implements Initializable {

    @FXML
    private ListView dateView;
    @FXML
    private ListView changeView;
    @FXML
    private ListView userView;

    private ObservableList<String> dateObserve;
    private ObservableList<String> userObserve;
    private ObservableList<String> changeObserve;

    ArrayList<INotification> notifications;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateObserve = FXCollections.observableArrayList();
        userObserve = FXCollections.observableArrayList();
        changeObserve = FXCollections.observableArrayList();

        updateNotifications();



        //App.domain.unNotify();

    }

    private void updateNotifications() {
        //Gets notifications from database.
        notifications = App.domain.getNotifications();

        //Clears lists
        dateObserve.clear();
        userObserve.clear();
        changeObserve.clear();

        //Loops through and adds to the observable lists.
        for (int i = 0; i < notifications.size(); i++) {
            //System.out.println(notifications.get(i).isSeen() + "  " + notifications.get(i).getChange());
            if (!notifications.get(i).isSeen())
                dateObserve.add("• " + notifications.get(i).getDate());
            else
                dateObserve.add(notifications.get(i).getDate());
            userObserve.add(notifications.get(i).getUser());
            changeObserve.add(notifications.get(i).getChange());
        }

        //Adds observable lists to listviews.
        dateView.setItems(dateObserve);
        userView.setItems(userObserve);
        changeView.setItems(changeObserve);

    }

    public void handleClickedDate(MouseEvent mouseEvent) {
        userView.getSelectionModel().select(dateView.getSelectionModel().getSelectedIndex());
        changeView.getSelectionModel().select(dateView.getSelectionModel().getSelectedIndex());

        int index = dateView.getSelectionModel().getSelectedIndex();
        boolean seen = notifications.get(index).isSeen();
        String date = notifications.get(index).getDate();
        String user = notifications.get(index).getUser();
        String change = notifications.get(index).getChange();
        App.domain.unNotify(seen, date, user, change);
        updateNotifications();

        dateView.getSelectionModel().select(index);
        userView.getSelectionModel().select(index);
        changeView.getSelectionModel().select(index);
    }
}
