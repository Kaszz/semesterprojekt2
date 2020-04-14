module Domain.creDB {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens Controllers to javafx.fxml;
    exports Controllers;
}