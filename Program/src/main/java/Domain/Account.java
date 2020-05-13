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

    public void addCredit(String title, String fName, String lName, CreditType role) {
        String credit = fName + ":" + lName + ":" + role.name();
        write.addCredit(title, credit);
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
        //Switched around showName and title. Hasn't changed argument orders to save time
        String episodeString = showName + ":" + bio + ":" + launchYear.toString() + ":" + userID + ":" + title + ":" + season + ":" + episode;
        write.createBroadcast(episodeString);
    }

    public String deleteCredit(String title, Credit credit) {
        String creditToDelete = credit.getfName() + ":" + credit.getlName() + ":" + credit.getRole();
        return main.getWriter().deleteCredit(title, creditToDelete);
    }

    public void deleteMovie(int broadcast_id, String title) {write.deleteMovie(broadcast_id);};
    public void deleteLiveShow(int broadcast_id, String title) {write.deleteLiveShow(broadcast_id);};

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