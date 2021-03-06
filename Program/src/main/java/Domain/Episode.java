package Domain;

import Interfaces.IEpisode;

import java.time.Year;

public class Episode extends Broadcast implements IEpisode {
    //Attributes
    private String showName;
    private int season;
    private int episodeNum;


    //Constructors
    public Episode(int broadcastID, String title, String bio, Year launchYear, String showName, int season, int episodeNum, int userID) {
        super(broadcastID, title, bio, launchYear, userID);
        this.showName = showName;
        this.season = season;
        this.episodeNum = episodeNum;
    }

    //Methods


    public String getShowName() {
        return showName;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisodeNum() {
        return episodeNum;
    }
}
