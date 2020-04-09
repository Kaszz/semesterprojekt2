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
        init();
    }

    public void addCredit(String title, String fName, String lName, CreditType role) {
        String credit = fName + ":" + lName + ":" + role.toString();
        write.addCredit(title, credit);
    }


    public void createMovie(String title, URL trailerURL, String bio, Year launchYear) {
        String broadcast = title + ":" + trailerURL.toString() + ":" + bio + ":" + launchYear.toString();
        write.createBroadcast(broadcast);
    }

    public void createLiveShow(String title, URL trailerURL, String bio, Year launchYear, String location) {
        String liveShow = title + ":" + trailerURL.toString() + ":" + bio + ":" + launchYear.toString() + ":" + location;
        write.createBroadcast(liveShow);

    }

    public void createEpisode(String title, URL trailerURL, String bio, Year launchYear, String showName, int season, int episode) {
        Episode ep = new Episode(title, trailerURL, bio, launchYear, showName, season, episode);
        String episodeString = title + ":" + trailerURL.toString() + ":" + bio + ":" + launchYear.toString() + ":" + showName + ":" + season + ":" + episode;
        write.createBroadcast(episodeString);
    }

    public void deleteCredit(String title, Credit credit) {
        String creditToDelete = credit.getfName() + ":" + credit.getlName() + ":" + credit.getRole();
        write.deleteCredit(title, creditToDelete);
        }

    public void deleteBroadcast(String title)  {
        write.deleteBroadcast(title);
    }
}