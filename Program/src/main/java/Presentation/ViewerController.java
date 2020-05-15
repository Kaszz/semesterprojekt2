package Presentation;

import Data.Reader;
import Domain.Episode;
import Domain.LiveShow;
import Domain.Movie;
import Domain.main;
import Interfaces.IBroadcast;
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
import java.time.Year;
import java.util.ArrayList;
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
    private Label textName;

    @FXML
    private Label textRole;

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

        textName.setStyle("-fx-opacity 1; -fx-background-color: rgb(0, 0, 0, 0.5);");
        textRole.setStyle("-fx-opacity 1; -fx-background-color: rgb(0, 0, 0, 0.5);");
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
            ArrayList<String> broadcasts = read.getAllBroadcasts();
            running = true;
            System.out.println("Thread started: " + Thread.currentThread());

            while (running) {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        iw.setImage(images[i]);
                        String[] splitString = broadcasts.get(j).split(":");

                        titleLabel.setText(splitString[1] + " - " + splitString[3]);

                        ArrayList<String> credits = read.getBroadcastCredits(Integer.parseInt(splitString[0]));


                        if (credits.isEmpty()) {
                            textName.setText("Der er ingen kreditering for denne udsendelse.");
                            textRole.setText("");
                        }
                        else {
                            StringBuilder nameString = new StringBuilder();
                            StringBuilder roleString = new StringBuilder();
                            for (String s : credits) {
                                String[] creditSplit = s.split(":");
                                nameString.append(creditSplit[1] + " " + creditSplit[2] + "\r\n");
                                roleString.append(creditSplit[3] + "\r\n");
                                String tempName = nameString.toString();
                                String tempRole = roleString.toString();
                                textName.setText(tempName);
                                textRole.setText(tempRole);
                            }
                        }


                        i = (i + 1) % images.length;
                        j = (j + 1) % broadcasts.size();

                    }
                });
                synchronized (this) {
                    try {
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
