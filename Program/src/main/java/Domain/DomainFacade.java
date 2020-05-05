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
    private Admin admin;
    private User user;
    private Credit credit;
    private Episode episode;
    private LiveShow liveShow;
    private Movie movie;
    private Login login;
    private IReader read = main.getReader();

    private DomainFacade() {
        login = Login.getInstance();
    }

    public static DomainFacade getInstance() {
        return instance;
    }

    public User createUser(String userID, String email, String password, String firstName, String lastName) {
        main.admin.createUser(userID, email, password, firstName, lastName);
        user = new User(userID, email, password, firstName, lastName);
        return user;
    }

    public Admin createAdmin(String userID, String email, String password, String firstName, String lastName) {
        admin = new Admin(userID, email, password, firstName, lastName);
        return admin;
    }

    public Credit createCredit(String fName, String lName, CreditType role) {
        credit = new Credit(fName, lName, role);
        return credit;
    }

    public Episode createEpisode(String title,  String bio, Year launchYear, String showName, int season, int episodeNum) {
        episode = new Episode(title, bio, launchYear, showName, season, episodeNum);
        return episode;
    }

    public LiveShow createLiveShow(String title, String bio, Year launchYear, String location) {
        liveShow = new LiveShow(title, bio, launchYear, location);
        return liveShow;
    }


    public Movie createMovie(String title, String bio, Year launchYear) {
        main.admin.createMovie(title, bio, launchYear);
        movie = new Movie(title, bio, launchYear);
        return movie;
    }

    public ArrayList<INotification> getNotifications() {
        return Notification.getNotifications();
    }

    public Login getLogin() {
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

    public void deleteUser(String userID, String email, String password, String firstName, String lastName, String enabled) {
        main.admin.deleteUser(userID, email, password, firstName, lastName, Boolean.valueOf(enabled));
    }

    public ArrayList<IBroadcast> getAllBroadcasts() {
        ArrayList<String> strings = read.getAllBroadcasts();
        ArrayList<IBroadcast> broadcasts = new ArrayList<>();

        for (String s: strings) {
            String[] sSplit = s.split(":");

            if (sSplit.length <= 4) {
                broadcasts.add(new Movie(sSplit[0], sSplit[1], Year.of(Integer.parseInt(sSplit[2]))));
            } else if (sSplit.length == 5) {
                broadcasts.add(new LiveShow(sSplit[1], sSplit[2], Year.of(Integer.parseInt(sSplit[3])), sSplit[4]));
            } else if (sSplit.length >= 6) {
                broadcasts.add(new Episode(sSplit[0], sSplit[1], Year.of(Integer.parseInt(sSplit[2])), sSplit[3], Integer.parseInt(sSplit[4]), Integer.parseInt(sSplit[5])));
            }
        }

        return broadcasts; }

    public ArrayList<String> getBroadcastCredits(String title) {return read.getBroadcastCredits(title);}

    public ArrayList<ICredit> getCredits(IBroadcast broadcast) {return broadcast.getCredits();}

    public ArrayList<String> getAllPrograms() {
        return main.admin.getAllPrograms();
    }

    public String getBroadcast(String title) {return main.read.getBroadcast(title); } //TODO maybe delete me later

}
