module Domain.creDB {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens Domain to javafx.fxml;
    exports Domain;
}