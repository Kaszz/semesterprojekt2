package Presentation;

import Domain.DomainFacade;
import Interfaces.ILogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class AppViewer extends Application {

    private static Scene scene;
    static DomainFacade domain = DomainFacade.getInstance();
    static ILogin loginClient = domain.getLogin();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Viewer"), 700, 339);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppViewer.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void load(String[] args) {
        launch();
    }

}