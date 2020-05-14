package Domain;

import Interfaces.IAccount;
import Interfaces.IWriter;

import java.time.Year;

public abstract class Account implements IAccount {
    private String email, password, firstName, lastName;
    public int userID;
    private IWriter write = main.getWriter();

    public Account(int userID, String email, String password, String firstName, String lastName) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addCredit(int broadcastID, String title, String fName, String lName, CreditType role) {
        String credit = fName + ":" + lName + ":" + role.name();
        write.addCredit(broadcastID, credit);
    }

    public void createMovie(String title, String bio, Year launchYear, int userID) {
        String broadcast = title + ":" +  bio + ":" + launchYear.toString() + ":" + userID;
        write.createBroadcast(broadcast);
    }

    public void createLiveShow(String title, String bio, Year launchYear, String location, int userID) {
        String liveShow = title + ":" + bio + ":" + launchYear.toString() + ":" + userID + ":" + location;
        write.createBroadcast(liveShow);
    }

    public void createEpisode(String title, String bio, Year launchYear, String showName, int season, int episode, int userID) {
        String episodeString = showName + ":" + bio + ":" + launchYear.toString() + ":" + userID + ":" + title + ":" + season + ":" + episode;
        write.createBroadcast(episodeString);
    }

    public void deleteCredit(int creditID, Credit credit) {
        main.getWriter().deleteCredit(creditID);
    }

    public void deleteBroadcast(int broadcast_id, String title) {
        write.deleteBroadcast(broadcast_id);
    };

    public void deleteEpisode(int episode_id, String title) {
        write.deleteEpisode(episode_id);
    };

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getUserID() {
        return userID;
    }

}