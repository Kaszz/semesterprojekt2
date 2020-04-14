package Domain;

import Interfaces.IEpisode;

import java.net.URL;
import java.time.Year;

public class Episode extends Broadcast implements IEpisode {
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


    public String getShowName() {
        return showName;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }
}
