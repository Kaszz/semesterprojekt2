package Domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProgramsData {

    StringProperty title = new SimpleStringProperty();
    StringProperty yearMade = new SimpleStringProperty();
    StringProperty season = new SimpleStringProperty();
    StringProperty episode = new SimpleStringProperty();

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setYearMade(String yearMade) {
        this.yearMade.set(yearMade);
    }

    public void setSeason(String season) {
        this.season.set(season);
    }

    public void setEpisode(String episode) {
        this.episode.set(episode);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getYearMade() {
        return yearMade.get();
    }

    public StringProperty yearMadeProperty() {
        return yearMade;
    }

    public String getSeason() {
        return season.get();
    }

    public StringProperty seasonProperty() {
        return season;
    }

    public String getEpisode() {
        return episode.get();
    }

    public StringProperty episodeProperty() {
        return episode;
    }
}
