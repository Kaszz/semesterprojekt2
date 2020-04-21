package Presentation;

import Domain.Credit;
import Domain.CreditType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class CreditTable {
    private StringProperty fName;
    private StringProperty lName;
    private CreditType role;

    public CreditTable(String fName, String lName, String role) {
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
        this.role = CreditType.valueOf(role);
    }

    public String getfName() {
        return fName.get();
    }

    public StringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }

    public String getlName() {
        return lName.get();
    }

    public StringProperty lNameProperty() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName.set(lName);
    }

    public CreditType getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = CreditType.valueOf(role);
    }
}
