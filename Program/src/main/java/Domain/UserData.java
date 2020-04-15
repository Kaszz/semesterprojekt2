package Domain;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserData {

    StringProperty udEmail = new SimpleStringProperty();
    StringProperty udPassword = new SimpleStringProperty();
    StringProperty udFirstName = new SimpleStringProperty();
    StringProperty udLastName = new SimpleStringProperty();

    public void setUdEmail(String udEmail) {
        this.udEmail.set(udEmail);
    }

    public void setUdPassword(String udPassword) {
        this.udPassword.set(udPassword);
    }

    public void setUdFirstName(String udFirstName) {
        this.udFirstName.set(udFirstName);
    }

    public void setUdLastName(String udLastName) {
        this.udLastName.set(udLastName);
    }

    public String getUdEmail() {
        return udEmail.get();
    }

    public StringProperty udEmailProperty() {
        return udEmail;
    }

    public String getUdPassword() {
        return udPassword.get();
    }

    public StringProperty udPasswordProperty() {
        return udPassword;
    }

    public String getUdFirstName() {
        return udFirstName.get();
    }

    public StringProperty udFirstNameProperty() {
        return udFirstName;
    }

    public String getUdLastName() {
        return udLastName.get();
    }

    public StringProperty udLastNameProperty() {
        return udLastName;
    }
}
