package Presentation;

import javafx.beans.property.SimpleStringProperty;

public class ProgramsData {

    SimpleStringProperty title;
    SimpleStringProperty yearMade;
    SimpleStringProperty season;
    SimpleStringProperty episodeNum;
    SimpleStringProperty showName;
    String bio;
    String location;
    boolean episode = false;
    boolean liveShow = false;
    boolean movie = false;

    //Used to create series
    public ProgramsData(String title, String yearMade) {
        this.title = new SimpleStringProperty(title);
        this.yearMade = new SimpleStringProperty(yearMade);
    }

    //Used to create seasons
    public ProgramsData(int season, String yearMade) {
        this.title = new SimpleStringProperty("Sæson " + season);
        this.season = new SimpleStringProperty("" + season);
    }

    //Used to create episodes
    public ProgramsData(String title, String yearMade, String season, String episodeNum, String showName, String bio) {
        this.title = new SimpleStringProperty(title);
        this.yearMade = new SimpleStringProperty(yearMade);
        this.season = new SimpleStringProperty(season);
        this.episodeNum = new SimpleStringProperty(episodeNum);
        this.showName = new SimpleStringProperty(showName);
        this.bio = bio;
        this.episode = true;
    }

    //Used to create liveShows
    public ProgramsData(String title, String yearMade, String bio, String location) {
        this.title = new SimpleStringProperty(title);
        this.yearMade = new SimpleStringProperty(yearMade);
        this.bio = bio;
        this.location = location;
        this.liveShow = true;
    }

    //Used to create movies
    public ProgramsData(String title, String yearMade, String bio) {
        this.title = new SimpleStringProperty(title);
        this.yearMade = new SimpleStringProperty(yearMade);
        this.bio = bio;
        this.movie = true;
    }


    public ProgramsData() {}

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setYearMade(String yearMade) {
        this.yearMade.set(yearMade);
    }

    public void setSeason(String season) {
        this.season.set(season);
    }

    public void setEpisodeNum(String episodeNum) {
        this.episodeNum.set(episodeNum);
    }

    public SimpleStringProperty getTitle() {
        return title;
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public SimpleStringProperty getYearMade() {
        return yearMade;
    }

    public SimpleStringProperty yearMadeProperty() {
        return yearMade;
    }

    public String getSeason() {
        return season.get();
    }

    public SimpleStringProperty seasonProperty() {
        return season;
    }

    public String getEpisodeNum() {
        return episodeNum.get();
    }

    public SimpleStringProperty episodeNumProperty() {
        return episodeNum;
    }

    public SimpleStringProperty showNameProperty() {return showName;}

    public String getShowName() {return showName.get();}

    public boolean isEpisode() {
        return episode;
    }

    public boolean isLiveShow() {
        return liveShow;
    }

    public boolean isMovie() {
        return movie;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }
}
