package Domain;

import Interfaces.IUser;
import Interfaces.IWriter;

import java.time.Year;
import java.util.Date;

public class User extends Account implements IUser {

    private boolean enabled = true;
    private IWriter write = main.getWriter();

    public User(String userID, String email, String password, String firstName, String lastName) {
        super(userID, email, password, firstName, lastName);
    }

    @Override
    public boolean addCredit(String title, String fName, String lName, CreditType role) {
        Notification.addNotification(new Date(), this.userID,"Tilføjet kreditering for " + fName + " " + lName + " til " + title + " som " + role);
        return super.addCredit(title, fName, lName, role);
    }

    @Override
    public void createMovie(String title, String bio, Year launchYear, String userID) {
        super.createMovie(title, bio, launchYear, userID);
        Notification.addNotification(new Date(), this.userID, "Oprettet filmen " + title + " fra året " + launchYear);
    }

    @Override
    public void createLiveShow(String title, String bio, Year launchYear, String location, String userID) {
        super.createLiveShow(title, bio, launchYear, location, userID);
        Notification.addNotification(new Date(), this.userID, "Oprettet liveshowet " + title + " fra året " + launchYear + " på " + location);
    }

    @Override
    public void createEpisode(String title, String bio, Year launchYear, String showName, int season, int episode, String userID) {
        super.createEpisode(title, bio, launchYear, showName, season, episode, userID);
        Notification.addNotification(new Date(), this.userID, "Oprettet episoden " + title + " fra tv-serien " + showName + " sæson " + season + " episode nummer " + episode);
    }

    @Override
    public String deleteCredit(String title, Credit credit) {
        Notification.addNotification(new Date(), this.userID, "Slettet kreditering for " + credit.getfName() + " " + credit.getlName() + "i  " + title);
        return super.deleteCredit(title, credit);
    }

    public String deleteBroadcast(String title)  {
        Notification.addNotification(new Date(), this.userID, "Slettet udsendelsen " + title);
        return super.deleteBroadcast(title);
    }

    public boolean getEnabled() {
        return enabled;
    }
}
