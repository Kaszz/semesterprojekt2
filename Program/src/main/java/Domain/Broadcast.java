package Domain;

import Interfaces.IBroadcast;
import Interfaces.ICredit;

import java.lang.reflect.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Broadcast implements IBroadcast {
    //Attributes
    private int broadcastID;
    private String title;
    private ArrayList<Credit> credits;
    private String bio;
    private Year launchYear;
    private int userID;

    //Constructors
    public Broadcast(String title, String bio, Year launchYear, int userID) {
        this.title = title;
        this.bio = bio;
        this.launchYear = launchYear;
        this.userID = userID;
        credits = new ArrayList<>();
    }

    public Broadcast(int broadcastID, String title, String bio, Year launchYear, int userID) {
        this.title = title;
        this.bio = bio;
        this.launchYear = launchYear;
        this.userID = userID;
        this.broadcastID = broadcastID;
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

    public String getBio() {
        return bio;
    }

    public Year getLaunchYear() {
        return launchYear;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return title;
    }

    public int getBroadcastID() {
        return broadcastID;
    }
}
