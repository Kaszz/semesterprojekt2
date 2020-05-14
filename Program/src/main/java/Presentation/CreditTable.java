package Presentation;

import Domain.Credit;
import Domain.CreditType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class CreditTable {
    private SimpleIntegerProperty creditID;
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private CreditType role;

    public CreditTable(String fName, String lName, String role) {
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
        this.role = CreditType.valueOf(role);
    }

    public CreditTable(String fName, String lName) {
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
    }

    //Used to create movies
    public CreditTable(int creditID, String fName, String lName, String role) {
        this.creditID = new SimpleIntegerProperty(creditID);
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
        this.role = CreditType.valueOf(role);
    }

    public int getCreditID() {
        return creditID.get();
    }

    public SimpleIntegerProperty creditIDProperty() {
        return creditID;
    }

    public void setCreditID(int creditID) {
        this.creditID.set(creditID);
    }

    public String getfName() {
        return fName.get();
    }

    public SimpleStringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }

    public String getlName() {
        return lName.get();
    }

    public SimpleStringProperty lNameProperty() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName.set(lName);
    }

    public CreditType getRole() {
        return role;
    }

    public void setRole(CreditType role) {
        this.role = role;
    }
}
