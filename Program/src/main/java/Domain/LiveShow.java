package Domain;

import Interfaces.ILiveShow;

import java.time.Year;

public class LiveShow extends Broadcast implements ILiveShow {
    //Attributes
    String location;

    //Constructors
    public LiveShow(int broadcastID, String title, String bio, Year launchYear, String location, int userID) {
        super(broadcastID, title, bio, launchYear, userID);
        this.location = location;
    }

    //Methods
    public String getLocation() {
        return location;
    }

}
