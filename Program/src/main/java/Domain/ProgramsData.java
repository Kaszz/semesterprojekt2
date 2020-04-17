package Domain;

import javafx.beans.property.SimpleStringProperty;

public class ProgramsData {

    SimpleStringProperty title;
    SimpleStringProperty yearMade;
    SimpleStringProperty season;
    SimpleStringProperty episode;

    public ProgramsData(String title, String yearMade) {
        this.title = new SimpleStringProperty(title);
        this.yearMade = new SimpleStringProperty(yearMade);
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

    public String getEpisode() {
        return episode.get();
    }

    public SimpleStringProperty episodeProperty() {
        return episode;
    }
}
