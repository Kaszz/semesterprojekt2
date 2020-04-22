package Domain;

import Interfaces.IBroadcast;
import Interfaces.ICredit;
import Interfaces.INotification;
import Interfaces.IReader;
import Presentation.Noti;

import java.net.URL;
import java.time.Year;
import java.util.ArrayList;

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

    public Episode createEpisode(String title, URL trailerURL, String bio, Year launchYear, String showName, int season, int episodeNum) {
        episode = new Episode(title, trailerURL, bio, launchYear, showName, season, episodeNum);
        return episode;
    }

    public LiveShow createLiveShow(String title, URL trailerURL, String bio, Year launchYear, String location) {
        liveShow = new LiveShow(title, trailerURL, bio, launchYear, location);
        return liveShow;
    }

    public Movie createMovie(String title, URL trailerURL, String bio, Year launchYear) {
        movie = new Movie(title, trailerURL, bio, launchYear);
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

    public ArrayList<String> getAllBroadcasts() {return read.getAllBroadcasts(); }

    public ArrayList<String> getBroadcastCredits(String title) {return read.getBroadcastCredits(title);}

    public ArrayList<ICredit> getCredits(IBroadcast broadcast) {return broadcast.getCredits();}


}
