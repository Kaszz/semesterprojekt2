package Domain;

import java.io.*;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Broadcast implements Login {
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

        /*
        //Writes the credit to a file
        File file = new File(title + ".txt");

        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            writer.write(fName + ":" + lName + ":" + role);
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    //TODO - This method should search the database for the title, make a temporary broadcast object and return it.
    /*public Broadcast getBroadcast(String title) {

    }*/

    public String getTitle() {
        return title;
    }

    public ArrayList<Credit> getCredits() {
        return credits;
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
}
