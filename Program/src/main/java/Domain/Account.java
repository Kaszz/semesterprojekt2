package Domain;

import Data.DataFacade;
import Interfaces.IReader;
import Interfaces.IWriter;

import java.io.*;
import java.net.URL;
import java.time.Year;
import java.util.Scanner;

public abstract class Account { //TODO implements Login
    private String email, password, firstName, lastName;



    public Account(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public void addCredit(String title, String fName, String lName, CreditType role) {
        String credit = fName + ":" + lName + ":" + role.toString();
        main.getWriter().addCredit(title, credit);
    }


    public void createMovie(String title, URL trailerURL, String bio, Year launchYear) {
        String broadcast = title + ":" + trailerURL.toString() + ":" + bio + ":" + launchYear.toString();
        main.getWriter().createBroadcast(broadcast);
    }

    public void createLiveShow(String title, URL trailerURL, String bio, Year launchYear, String location) {
        String liveShow = title + ":" + trailerURL.toString() + ":" + bio + ":" + launchYear.toString() + ":" + location;
        main.getWriter().createBroadcast(liveShow);

    }

    public void createEpisode(String title, URL trailerURL, String bio, Year launchYear, String showName, int season, int episode) {
        Episode ep = new Episode(title, trailerURL, bio, launchYear, showName, season, episode);
        String episodeString = title + ":" + trailerURL.toString() + ":" + bio + ":" + launchYear.toString() + ":" + showName + ":" + season + ":" + episode;
        main.getWriter().createBroadcast(episodeString);
    }

    public void deleteCredit(String title, Credit credit) {
        String creditToDelete = credit.getfName() + ":" + credit.getlName() + ":" + credit.getRole();
        main.getWriter().deleteCredit(title, creditToDelete);
        }

    public void deleteBroadcast(String title)  {
        main.getWriter().deleteBroadcast(title);
    }


    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}