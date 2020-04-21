package Presentation;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
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

    StringProperty udID;
    StringProperty udEmail;
    StringProperty udPassword;
    StringProperty udFirstName;
    StringProperty udLastName;
    StringProperty udEnabled;

    public UserData(String udID, String udEmail, String udPassword, String udFirstName, String udLastName, String udEnabled) {
        this.udID = new SimpleStringProperty(udID);
        this.udEmail = new SimpleStringProperty(udEmail);
        this.udPassword = new SimpleStringProperty(udPassword);
        this.udFirstName = new SimpleStringProperty(udFirstName);
        this.udLastName = new SimpleStringProperty(udLastName);
        this.udEnabled = new SimpleStringProperty(udEnabled);
    }

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

    public String getUdID() {
        return udID.get();
    }

    public StringProperty udIDProperty() {
        return udID;
    }

    public void setUdID(String udID) {
        this.udID.set(udID);
    }

    public String getUdEnabled() {
        return udEnabled.get();
    }

    public StringProperty udEnabledProperty() {
        return udEnabled;
    }

    public void setUdEnabled(String udEnabled) {
        this.udEnabled.set(udEnabled);
    }
}
