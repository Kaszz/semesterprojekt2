package Domain;

import Interfaces.IBroadcast;
import Interfaces.ICredit;

import java.lang.reflect.Array;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Broadcast implements IBroadcast {
    //Attributes
    private String title;
    private ArrayList<Credit> credits;
    private URL trailerURL;
    private String bio;
    private Year launchYear;
    private UUID userID;

    //Constructors
    public Broadcast(String title, URL trailerURL, String bio, Year launchYear) {
        this.title = title;
        this.trailerURL = trailerURL;
        this.bio = bio;
        this.launchYear = launchYear;
        //TODO - Add userID so it dynamically grabs from the logged in user.

        credits = new ArrayList<>();
    }

    //Method
    public void addCredit(String title, String fName, String lName, CreditType role) {
        Credit tempCredit = new Credit(fName, lName, role);
        credits.add(tempCredit);
    }

    public void deleteAllCredits() {
        credits.clear();
    }

    public void addCredit(Credit credit) {
        credits.add(credit);
    }

    //TODO - This method should search the database for the title, return a string so that we can make a Broadcast object with it.
    /*public Broadcast getBroadcast(String title) {}

     */

    public String getTitle() {
        return title;
    }

    public ArrayList<ICredit> getCredits() {
        ArrayList<ICredit> iCredits;
        iCredits = new ArrayList<ICredit>(credits);
        return iCredits;
    }

    public URL getTrailerURL() {
        return trailerURL;
    }

    public String getBio() {
        return bio;
    }

    public Year getLaunchYear() {
        return launchYear;
    }

    @Override
    public String toString() {
        return title;
    }
}
