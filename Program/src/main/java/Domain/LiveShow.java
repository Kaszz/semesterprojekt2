package Domain;

import java.net.URL;
import java.time.Year;

public class LiveShow extends Broadcast {
    //Attributes
    String location;

    //Constructors
    public LiveShow(String title, URL trailerURL, String bio, Year launchYear, String location) {
        super(title, trailerURL, bio, launchYear);
        this.location = location;
    }

    //Methods
    public String getLocation() {
        return location;
    }

}
