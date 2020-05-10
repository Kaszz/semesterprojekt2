package Domain;

import Interfaces.IMovie;

import java.net.URL;
import java.time.Year;

public class Movie extends Broadcast implements IMovie {
    //Attributes

    //Constructors
    public Movie(String title, String bio, Year launchYear, int userID) {
        super(title, bio, launchYear, userID);
    }

    //Methods

}
