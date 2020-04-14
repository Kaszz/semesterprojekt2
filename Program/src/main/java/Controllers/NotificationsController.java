package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificationsController implements Initializable {


    @FXML
    private TreeTableColumn<?, ?> dateColumnNotifications;

    @FXML
    private TreeTableColumn<?, ?> userColumnNotifications;

    @FXML
    private TreeTableColumn<?, ?> detailsNotificationsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
