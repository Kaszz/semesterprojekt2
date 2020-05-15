package Domain;

import Interfaces.IUser;
import Interfaces.IWriter;

import java.time.Year;
import java.util.Date;

public class User extends Account implements IUser {

    private boolean enabled = true;
    private IWriter write = main.getWriter();

    public User(int userID, String email, String password, String firstName, String lastName) {
        super(userID, email, password, firstName, lastName);
    }

    @Override
    public void addCredit(int broadcastID, String title, String fName, String lName, CreditType role) {
        Notification.addNotification(new Date(), this.getFirstName() + " " + this.getLastName(),"Tilføjet kreditering for " + fName + " " + lName + " til " + title + " som " + role);
        super.addCredit(broadcastID, title, fName, lName, role);
    }

    @Override
    public void createMovie(String title, String bio, Year launchYear, int userID) {
        super.createMovie(title, bio, launchYear, userID);
        Notification.addNotification(new Date(), this.getFirstName() + " " + this.getLastName(), "Oprettet filmen " + title + " fra året " + launchYear);
    }

    @Override
    public void createLiveShow(String title, String bio, Year launchYear, String location, int userID) {
        super.createLiveShow(title, bio, launchYear, location, userID);
        Notification.addNotification(new Date(), this.getFirstName() + " " + this.getLastName(), "Oprettet liveshowet " + title + " fra året " + launchYear + " på " + location);
    }

    @Override
    public void createEpisode(String title, String bio, Year launchYear, String showName, int season, int episode, int userID) {
        super.createEpisode(title, bio, launchYear, showName, season, episode, userID);
        Notification.addNotification(new Date(), this.getFirstName() + " " + this.getLastName(), "Oprettet episoden " + title + " fra tv-serien " + showName + " sæson " + season + " episode nummer " + episode);
    }

    @Override
    public void editMovie(int broadcast_id, String title, String bio, int launchYear, String oldTitle) {
        super.editMovie(broadcast_id, title, bio, launchYear, oldTitle);
        Notification.addNotification(new Date(), this.getFirstName() + " " + this.getLastName(), "Ændrede i filmen " + oldTitle + " til " + title + " fra året " + launchYear);
    }

    @Override
    public void editLiveShow(int broadcast_id, String title, String bio, int launchYear, String location, String oldTitle) {
        super.editLiveShow(broadcast_id, title, bio, launchYear, location, oldTitle);
        Notification.addNotification(new Date(), this.getFirstName() + " " + this.getLastName(), "Ændrede i liveshowet " + oldTitle + " til " + title + " fra året " + launchYear + " på " + location);
    }

    @Override
    public void editEpisode(int broadcast_id, String title, String bio, int launchYear, int seaNum, int epiNum, String oldTitle) {
        super.editEpisode(broadcast_id, title, bio, launchYear, seaNum, epiNum, oldTitle);
        Notification.addNotification(new Date(), this.getFirstName() + " " + this.getLastName(), "Ændrede i episoden " + oldTitle);
    }

    @Override
    public void deleteCredit(Credit credit, String title) {
        Notification.addNotification(new Date(), this.getFirstName() + " " + this.getLastName(), "Slettet kreditering for " + credit.getfName() + " " + credit.getlName() + "i  " + title);
        super.deleteCredit(credit.getCreditID(), credit);
    }

    public void deleteBroadcast(int broadcast_id, String title) {
        Notification.addNotification(new Date(), this.getFirstName() + " " + this.getLastName(), "Slettet udsendelsen " + title);
        super.deleteBroadcast(broadcast_id, title);
    }

    public void deleteEpisode(int episode_id, String title) {
        Notification.addNotification(new Date(), this.getFirstName() + " " + this.getLastName(), "Slettet udsendelsen " + title);
        super.deleteEpisode(episode_id, title);
    }

    public boolean getEnabled() {
        return enabled;
    }
}
