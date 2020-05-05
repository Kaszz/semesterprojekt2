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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import static javafx.scene.input.KeyCode.KP_UP;

public class ViewerController implements Initializable {

    public Image[] images;

    @FXML
    private ImageView spin1;

    @FXML
    private Label textOverlay;

    @FXML
    private TextArea textOverlayArea;

    private Thread t1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        images = new Image[4];
        String pre = "slide";
        String post = ".png";
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



            running = true;
            System.out.println("Thread started: " + Thread.currentThread());

            while (running) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        iw.setImage(images[i]);
                        if (i == 0) {
                            StringBuilder string = new StringBuilder();
                            for (int j = 0; j < read.getBroadcastCredits("Fathers").size(); j++) {
                                string.append(read.getBroadcastCredits("Fathers").get(j)+"\r\n");
                                String singleString = string.toString();
                                textOverlay.setText(singleString);
                            }
                        } else if (i == 1) {
                            StringBuilder string = new StringBuilder();
                            for (int j = 0; j < read.getBroadcastCredits("Geeks").size(); j++) {
                                string.append(read.getBroadcastCredits("Geeks").get(j)+"\r\n");
                                String singleString = string.toString();
                                textOverlay.setText(singleString);
                            }
                        } else if (i == 2) {
                            StringBuilder string = new StringBuilder();
                            for (int j = 0; j < read.getBroadcastCredits("homer").size(); j++) {
                                string.append(read.getBroadcastCredits("homer").get(j)+"\r\n");
                                String singleString = string.toString();
                                textOverlay.setText(singleString);
                            }
                        } else if (i == 3) {
                            StringBuilder string = new StringBuilder();
                            for (int j = 0; j < read.getBroadcastCredits("live").size(); j++) {
                                string.append(read.getBroadcastCredits("live").get(j)+"\r\n");
                                String singleString = string.toString();
                                textOverlay.setText(singleString);
                            }
                        }
                        i = (i + 1) % images.length;
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
