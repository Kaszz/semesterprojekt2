package Domain;

import Interfaces.*;
import Presentation.Noti;
import javafx.scene.control.DatePicker;

import java.lang.reflect.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

public class DomainFacade {
    private static DomainFacade instance = new DomainFacade();
    private ILogin login;
    private IReader read = main.getReader();
    private IWriter write = main.getWriter();

    private DomainFacade() {
        login = Login.getInstance();
    }

    public static DomainFacade getInstance() {
        return instance;
    }

    public IUser createUser(String email, String password, String firstName, String lastName) {
        IUser user = main.admin.createUser(email, password, firstName, lastName);
        return user;
    }

    public ICredit createCredit(String fName, String lName, CreditType role) {
        ICredit credit = new Credit(fName, lName, role);
        return credit;
    }

    public void createEpisode(String title, String bio, Year launchYear, String showName, int season, int episodeNum, int userID) {
        login.getAccount().createEpisode(title, bio, launchYear, showName, season, episodeNum, userID);
    }

    public void createLiveShow(String title, String bio, Year launchYear, String location, int userID) {
        login.getAccount().createLiveShow(title, bio, launchYear, location, userID);
    }

    public void createMovie(String title, String bio, Year launchYear, int userID) {
        login.getAccount().createMovie(title, bio, launchYear, userID);
    }

    public ArrayList<INotification> getNotifications() {
        return Notification.getNotifications();
    }

    public ILogin getLogin() {
        return login;
    }

    public void unNotify(boolean seen, String date, String user, String change) {
        Notification.unNotify(seen, date, user, change);
    }

    public synchronized int notificationCount() {
        return Notification.notificationCount();
    }

    public ArrayList<String> getAllUsers() {
        return main.admin.getAllUsers();
    }

    public void deleteUser(int userID, String email, String password, String firstName, String lastName, String enabled) {
        main.admin.deleteUser(userID);
    }

    public ArrayList<IBroadcast> getAllBroadcasts() {
        ArrayList<String> strings = read.getAllBroadcasts();
        ArrayList<IBroadcast> broadcasts = new ArrayList<>();

        for (String s: strings) {
            String[] sSplit = s.split(":");

            if (sSplit.length == 5) {
                broadcasts.add(new Movie(Integer.parseInt(sSplit[0]), sSplit[1], sSplit[2], Year.of(Integer.parseInt(sSplit[3])), Integer.parseInt(sSplit[4])));
            } else if (sSplit.length == 6) {
                broadcasts.add(new LiveShow(Integer.parseInt(sSplit[0]), sSplit[1], sSplit[2], Year.of(Integer.parseInt(sSplit[3])), sSplit[4], Integer.parseInt(sSplit[5])));
            } else {
                broadcasts.add(new Episode(Integer.parseInt(sSplit[0]), sSplit[1], sSplit[6], Year.of(Integer.parseInt(sSplit[7])), sSplit[5], Integer.parseInt(sSplit[3]), Integer.parseInt(sSplit[2]), Integer.parseInt(sSplit[8])));
            }
        }

        return broadcasts; }

    public ArrayList<String> getBroadcastCredits(int broadcastID) {return read.getBroadcastCredits(broadcastID);}

    public ArrayList<ICredit> getCredits(IBroadcast broadcast) {return broadcast.getCredits();}

    public ArrayList<String> getAllPrograms() {
        return main.admin.getAllPrograms();
    }

    public void addCredit(String title, ICredit credit) {
        login.getAccount().addCredit(title, credit.getfName(), credit.getlName(), credit.getRole());
    }

    public String deleteCredit(String title, ICredit credit) {
        return login.getAccount().deleteCredit(title, (Credit) credit);
    }

    public void deleteBroadcast(int broadcast_id, String title) {
        login.getAccount().deleteBroadcast(broadcast_id, title);};

    public void deleteEpisode(int episode_id, String title) {
        login.getAccount().deleteEpisode(episode_id, title);};

    public boolean isAdmin() {
        return login.isAdmin();
    }

}
