package Domain;

import Data.DataFacade;
import Interfaces.IReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.Year;

public abstract class Account { //TODO implements Login
    private String username;
    private String password;
    private String name;
    private String email;


    public static DataFacade df;

    public void init() {
        df = new DataFacade();
        IReader read = df.getReader();
    }

    public Account(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void addCredit(String title, String fName, String lName, CreditType role) {
        Credit tempCredit = new Credit(fName, lName, role);

        //Writes the credit to a file
        File file = new File(title + ".txt");

        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            writer.write(fName + ":" + lName + ":" + role);
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createMovie(String title, URL trailerURL, String bio, Year launchYear) {
        Movie movie = new Movie(title, trailerURL, bio, launchYear);

        File file = new File(title + ".txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileWriter writer = null;
            try {
                writer = new FileWriter(file, true);
                writer.write(title + ":" + trailerURL + ":" + bio + ":" + launchYear);
                writer.write("\r\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createLiveShow(String title, URL trailerURL, String bio, Year launchYear, String location) {
        // TODO
        // LiveShow liveShow = new LiveShow(title, trailerURL, bio, launchYear, location);
    }

    public void createEpisode(String title, String trailerURL, String bio, Year launchYear, String showName, int season, int episode) {
        //TODO
        //Episode episode = new Episode(title, trailerURL, bio, launchYear, location, showName, season, episode);
    }

    public void addCredit(String title, String fname, String lname, String role) {
        //TODO
        //Broadcast broadcast;
        //broadcast = getBroadcast(title); //Skal kunne hente broadcasts fra datalaget... How?
        //broadcast.add(new Credit(fname, lname, role));
        //function.saveToFile
    }

    public void deleteCredit(String title, int creditID) {
        //TODO
        /*
        Broadcast broadcast;
        broadcast = getBroadcast(title); //Skal kunne hente broadcasts fra datalaget... How?
        if (broadcast.credits.indexOf(creditID) > 0) {
            broadcast.credits.remove(indexOf(creditID));
        }
        else {
            //Rapporter fejl til præsentationslaget
        }
        */
    }

    public void deleteBroadcast(String title) {
        //TODO
        /*
        //Query database om at slette den række som indeholder den specifikke titel
         */
    }

}