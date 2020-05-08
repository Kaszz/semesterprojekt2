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

    public IUser createUser(String userID, String email, String password, String firstName, String lastName) {
        main.admin.createUser(userID, email, password, firstName, lastName);
        IUser user = new User(userID, email, password, firstName, lastName);
        return user;
    }

    public ICredit createCredit(String fName, String lName, CreditType role) {
        ICredit credit = new Credit(fName, lName, role);
        return credit;
    }

    public IEpisode createEpisode(String title,  String bio, Year launchYear, String showName, int season, int episodeNum) {
        login.getAccount().createEpisode(title, bio, launchYear, showName, season, episodeNum);
        IEpisode episode = new Episode(title, bio, launchYear, showName, season, episodeNum);
        return episode;
    }

    public ILiveShow createLiveShow(String title, String bio, Year launchYear, String location) {
        login.getAccount().createLiveShow(title, bio, launchYear, location);
        ILiveShow liveShow = new LiveShow(title, bio, launchYear, location);
        return liveShow;
    }

    public IMovie createMovie(String title, String bio, Year launchYear) {
        login.getAccount().createMovie(title, bio, launchYear);
        IMovie movie = new Movie(title, bio, launchYear);
        return movie;
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

    public void deleteUser(String userID, String email, String password, String firstName, String lastName, String enabled) {
        main.admin.deleteUser(userID, email, password, firstName, lastName, Boolean.valueOf(enabled));
    }

    public ArrayList<IBroadcast> getAllBroadcasts() {
        ArrayList<String> strings = read.getAllBroadcasts();
        ArrayList<IBroadcast> broadcasts = new ArrayList<>();

        for (String s: strings) {
            String[] sSplit = s.split(":");

            if (sSplit.length == 4) {
                broadcasts.add(new Movie(sSplit[0], sSplit[1], Year.of(Integer.parseInt(sSplit[2]))));
            } else if (sSplit.length == 5) {
                broadcasts.add(new LiveShow(sSplit[0], sSplit[1], Year.of(Integer.parseInt(sSplit[2])), sSplit[3]));
            } else if (sSplit.length == 7) {
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

    public String deleteBroadcast(String title) {
        return login.getAccount().deleteBroadcast(title);
    }

    public boolean addCredit(String title, ICredit credit) {
        return login.getAccount().addCredit(title, credit.getfName(), credit.getlName(), credit.getRole());
    }

    public String deleteCredit(String title, ICredit credit) {
        return login.getAccount().deleteCredit(title, (Credit) credit);
    }

    public boolean isAdmin() {
        return login.isAdmin();
    }

}
