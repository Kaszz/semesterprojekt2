package com.mycompany.program01;

import java.net.URL;
import java.time.Year;

public abstract class Account { //TODO implements Login
    private String username;
    private String password;
    private String name;
    private String email;

    public Account(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void createMovie(String title, String trailerURL, String bio, Year launchYear) {
        // TODO
        //  Movie movie = new Movie(title, trailerURL, bio, launchYear);
    }

    public void createLiveShow(String title, String trailerURL, String bio, Year launchYear, String location) {
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