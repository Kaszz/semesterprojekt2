package Domain;

import Data.DataFacade;
import Interfaces.IReader;
import Interfaces.IWriter;

import java.io.*;
import java.net.URL;
import java.time.Year;
import java.util.Scanner;

public abstract class Account { //TODO implements Login
    private String username;
    private String password;
    private String name;
    private String email;
    private IReader read;
    private IWriter write;

    public static DataFacade df;

    public void init() {
        df = new DataFacade();
        this.read = df.getReader();
        this.write = df.getWriter();
    }

    public Account(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void addCredit(String title, String fName, String lName, CreditType role) {


        /*
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

         */
    }


    public void createMovie(String title, URL trailerURL, String bio, Year launchYear) {
        Movie movie = new Movie(title, trailerURL, bio, launchYear);

        write.createBroadcast(title + bio);

        /*
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

         */
    }

    public void createLiveShow(String title, URL trailerURL, String bio, Year launchYear, String location) {
        LiveShow liveshow = new LiveShow(title, trailerURL, bio, launchYear, location);

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
                writer.write(title + ":" + trailerURL + ":" + bio + ":" + launchYear + ":" + location);
                writer.write("\r\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createEpisode(String title, URL trailerURL, String bio, Year launchYear, String showName, int season, int episode) {
        Episode ep = new Episode(title, trailerURL, bio, launchYear, showName, season, episode);

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
                writer.write(title + ":" + trailerURL + ":" + bio + ":" + launchYear + ":" + showName + ":" + season + ":" + episode);
                writer.write("\r\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCredit(String title, int creditID) throws IOException {

        // Vi har ikke assignet creditID til noget som helst

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




    public void deleteBroadcast(String title) throws IOException {
        //TODO
        /*
        //Query database om at slette den række som indeholder den specifikke titel
         */

        File file = new File("./src/txtfiles/broadcasts/" + title + ".txt");

        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }

    }

}