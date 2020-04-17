package Domain;

import Interfaces.IUser;
import Interfaces.IWriter;

import java.net.URL;
import java.time.Year;
import java.util.Date;

public class User extends Account implements IUser {
    private String userID;
    private boolean enabled = true;
    private IWriter write = main.getWriter();

    public User(String userID, String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
        this.userID = userID;
    }

    @Override
    public void addCredit(String title, String fName, String lName, CreditType role) {
        super.addCredit(title, fName, lName, role);
        Notification.addNotification(new Date(), this.userID,"Tilføjet kreditering for " + fName + " " + lName + " til " + title + " som " + role);
    }

    @Override
    public void createMovie(String title, URL trailerURL, String bio, Year launchYear) {
        super.createMovie(title, trailerURL, bio, launchYear);
        Notification.addNotification(new Date(), this.userID, "Oprettet filmen " + title + " fra året " + launchYear);
    }

    @Override
    public void createLiveShow(String title, URL trailerURL, String bio, Year launchYear, String location) {
        super.createLiveShow(title, trailerURL, bio, launchYear, location);
        Notification.addNotification(new Date(), this.userID, "Oprettet liveshowet " + title + " fra året " + launchYear + " på " + location);
    }

    @Override
    public void createEpisode(String title, URL trailerURL, String bio, Year launchYear, String showName, int season, int episode) {
        super.createEpisode(title, trailerURL, bio, launchYear, showName, season, episode);
        Notification.addNotification(new Date(), this.userID, "Oprettet episoden " + title + " fra tv-serien " + showName + " sæson " + season + " episode nummer " + episode);
    }

    @Override
    public void deleteCredit(String title, Credit credit) {
        super.deleteCredit(title, credit);
        Notification.addNotification(new Date(), this.userID, "Slettet kreditering for " + credit.getfName() + " " + credit.getlName() + "i  " + title);
    }

    public void deleteBroadcast(String title)  {
        super.deleteBroadcast(title);
        Notification.addNotification(new Date(), this.userID, "Slettet udsendelsen " + title);
    }





    public String getUserID() {
        return userID;
    }

    public boolean getEnabled() {
        return enabled;
    }
}
