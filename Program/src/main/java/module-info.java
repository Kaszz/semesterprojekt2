module com.mycompany.search_and_replace {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.program01 to javafx.fxml;
    exports com.mycompany.program01;
}