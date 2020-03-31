package com.mycompany.program01;

import java.net.URL;
import java.time.Year;

public class Episode extends Broadcast {
    //Attributes
    private String showName;
    private int season;
    private int episode;

    //Constructors
    public Episode(String title, URL trailerURL, String bio, Year launchYear, String showName, int season, int episode) {
        super(title, trailerURL, bio, launchYear);
        this.showName = showName;
        this.season = season;
        this.episode = episode;
    }

    //Methods


}
