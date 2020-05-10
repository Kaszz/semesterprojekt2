package Presentation;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public class Noti {
    private SimpleStringProperty date, change;
    private SimpleIntegerProperty user;
    private SimpleBooleanProperty seen;

    public Noti(String date, int user, String change) {
        this.date = new SimpleStringProperty(date);
        this.user = new SimpleIntegerProperty(user);
        this.change = new SimpleStringProperty(change);
    }

    public Noti(Boolean seen, String date, int user, String change) {
        this.seen = new SimpleBooleanProperty(seen);
        this.date = new SimpleStringProperty(date);
        this.user = new SimpleIntegerProperty(user);
        this.change = new SimpleStringProperty(change);
    }

    public boolean isSeen() {
        return seen.get();
    }

    public SimpleBooleanProperty seenProperty() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen.set(seen);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public int getUser() {
        return user.get();
    }

    public SimpleIntegerProperty userProperty() {
        return user;
    }

    public void setUser(int user) {
        this.user.set(user);
    }

    public String getChange() {
        return change.get();
    }

    public SimpleStringProperty changeProperty() {
        return change;
    }

    public void setChange(String change) {
        this.change.set(change);
    }

}
