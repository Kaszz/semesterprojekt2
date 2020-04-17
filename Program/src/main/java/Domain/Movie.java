package Domain;

import Interfaces.IMovie;

import java.net.URL;
import java.time.Year;

public class Movie extends Broadcast implements IMovie {
    //Attributes

    //Constructors
    public Movie(String title, URL trailerURL, String bio, Year launchYear) {
        super(title, trailerURL, bio, launchYear);
    }

    //Methods

}
