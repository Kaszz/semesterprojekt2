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

    public ICredit createCredit(int creditID, String fName, String lName, CreditType role) {
        ICredit credit = new Credit(creditID, fName, lName, role);
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

    public void editMovie(int broadcast_id, String title, String bio, int launchYear, String oldTitle) {
        login.getAccount().editMovie(broadcast_id, title, bio, launchYear, oldTitle);
    }

    public void editLiveShow(int broadcast_id, String title, String bio, int launchYear, String location, String oldTitle) {
        login.getAccount().editLiveShow(broadcast_id, title, bio, launchYear, location, oldTitle);
    }

    public void editEpisode(int episodeID, String title, String bio, int launchYear, int seaNum, int epiNum, String oldTitle) {
        login.getAccount().editEpisode(episodeID, title, bio, launchYear, seaNum, epiNum, oldTitle);
    }

    public ArrayList<INotification> getNotifications() {
        return Notification.getNotifications();
    }

    public ILogin getLogin() {
        return login;
    }

    public void unNotify(int notificationID) {
        Notification.unNotify(notificationID);
    }

    public synchronized int notificationCount() {
        return Notification.notificationCount();
    }

    public ArrayList<String> getAllUsers() {
        return main.admin.getAllUsers();
    }

    public void deleteUser(int userID, String email, String password, String firstName, String lastName) {
        main.admin.deleteUser(userID);
    }

    public void editUser(int userID, String email, String password, String fName, String lName) {
        main.admin.editUser(userID, email, password, fName, lName);
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

    public ArrayList<IBroadcast> getAllBroadcastsCredit() {
        ArrayList<String> strings = read.getAllBroadcasts();
        ArrayList<IBroadcast> broadcasts = new ArrayList<>();

        for (String s: strings) {
            String[] sSplit = s.split(":");

            if (sSplit.length == 5) {
                broadcasts.add(new Movie(Integer.parseInt(sSplit[0]), sSplit[1], sSplit[2], Year.of(Integer.parseInt(sSplit[3])), Integer.parseInt(sSplit[4])));
            } else if (sSplit.length == 6) {
                broadcasts.add(new LiveShow(Integer.parseInt(sSplit[0]), sSplit[1], sSplit[2], Year.of(Integer.parseInt(sSplit[3])), sSplit[4], Integer.parseInt(sSplit[5])));
            } else {
                boolean duplicateSpotted = false;
                for (IBroadcast b : broadcasts){
                    if (b.getTitle().equals(sSplit[5])) {
                        duplicateSpotted = true;
                        break;
                    }
                }
                if (!duplicateSpotted)
                    broadcasts.add(new Movie(Integer.parseInt(sSplit[9]), sSplit[5], sSplit[6], Year.of(Integer.parseInt(sSplit[7])), Integer.parseInt(sSplit[8])));

            }
        }

        return broadcasts; }

    public ArrayList<String> getBroadcastCredits(int broadcastID) {return read.getBroadcastCredits(broadcastID);}

    public ArrayList<ICredit> getCredits(IBroadcast broadcast) {return broadcast.getCredits();}

    public void addCredit(int broadcastID, String title, ICredit credit) {
        login.getAccount().addCredit(broadcastID, title, credit.getfName(), credit.getlName(), credit.getRole());
    }

    public void deleteCredit(ICredit credit, String title) {
        login.getAccount().deleteCredit((Credit) credit, title);
    }

    public void deleteBroadcast(int broadcast_id, String title) {
        login.getAccount().deleteBroadcast(broadcast_id, title);};

    public void deleteEpisode(int episode_id, String title) {
        login.getAccount().deleteEpisode(episode_id, title);};

    public boolean isAdmin() {
        return login.isAdmin();
    }

}
