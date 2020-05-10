module Domain.creDB {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens Presentation to javafx.fxml;
    exports Presentation;
}