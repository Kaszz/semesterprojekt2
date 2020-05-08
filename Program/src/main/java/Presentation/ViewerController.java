package Presentation;

import Data.Reader;
import Interfaces.IReader;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;

import static javafx.scene.input.KeyCode.KP_UP;

public class ViewerController implements Initializable {

    public Image[] images;

    @FXML
    private ImageView spin1;

    @FXML
    private Label textOverlay;

    @FXML
    private Label titleLabel;

    @FXML
    private TextArea textOverlayArea;

    private Thread t1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        images = new Image[4];
        String pre = "slide";
        String post = ".png";

        textOverlay.setStyle("-fx-opacity 1; -fx-background-color: rgb(0, 0, 0, 0.5);");
        titleLabel.setStyle("-fx-opacity 1; -fx-background-color: rgb(0, 0, 0, 0.5);");

        for (int i = 0; i < images.length; i++) {
            images[i] = new Image(new File("src/main/resources/images/" + pre + i + post).toURI().toString());
        }
        SlideRunnable b1 = new SlideRunnable(1, 5000, spin1);
        t1 = new Thread(b1);
        t1.setDaemon(true);
        t1.start();



    }


    public class SlideRunnable implements Runnable {

        private int i;
        private int j;
        private long sleepTime;
        private boolean running;
        private ImageView iw;
        private IReader read = new Reader();

        public SlideRunnable(int i, long sleepTime, ImageView iw) {
            this.i = i;
            this.sleepTime = sleepTime;
            this.iw = iw;
        }



        @Override
        public void run() {

            File directory = new File("./src/txtfiles/broadcasts/");
            //Makes array of files in directory.
            File[] files = directory.listFiles();


            running = true;
            System.out.println("Thread started: " + Thread.currentThread());

            while (running) {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Scanner scan = null;

                        iw.setImage(images[i]);

                        try {
                            scan = new Scanner(files[j]);
                            String firstLine = "";

                            firstLine = scan.nextLine();
                            String[] splitString = firstLine.split(":");

                            titleLabel.setText(splitString[0] + " - " + splitString[2]);

                            if (read.getBroadcastCredits(splitString[0]).size() == 0)
                                textOverlay.setText("Der er ingen kreditering for denne udsendelse.");
                            else {
                                StringBuilder string = new StringBuilder();
                                for (int j = 0; j < read.getBroadcastCredits(splitString[0]).size(); j++) {
                                    String creditLine = read.getBroadcastCredits(splitString[0]).get(j);
                                    String[] creditSplit = creditLine.split(":");
                                    string.append(creditSplit[0] + " " + creditSplit[1] + "                        " + creditSplit[2] + "\r\n");

                                    String singleString = string.toString();
                                    textOverlay.setText(singleString);
                                }
                            }

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        i = (i + 1) % images.length;
                        j = (j + 1) % files.length;

                    }
                });
                synchronized (this) {
                    try {
                        //Thread.sleep(sleepTime);
                        wait(sleepTime);
                    } catch (InterruptedException ex) {
                        System.out.println("Interrupted: " + Thread.currentThread());
                        running = false;
                    }
                }
            }
        }
    }

}
