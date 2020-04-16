package Presentation;


import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EditProgramsController implements Initializable {

    public ArrayList<String> getBroadcastData() {
        File directory = new File("./src/txtfiles/broadcasts/");
        //Makes array of files in directory.
        File[] files = directory.listFiles();
        String[] text;
        Scanner scan = null;
        ArrayList<String> returnList = new ArrayList<String>();

        //Iterate through the files in the directory.
        for (File f: files) {
            try {
                scan = new Scanner(f);

                //Get the title name of current file.
                String firstLine = scan.nextLine();

                text = firstLine.split(":");

                String titleNew = text[0];
                String yearMade = text[3];
                String season = text[5];
                String episode = text[6];

                String returnString = titleNew + " " + yearMade + " " + season + " " + episode;
                returnList.add(returnString);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                scan.close();
            }
        }

        return returnList;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
