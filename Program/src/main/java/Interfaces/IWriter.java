package Interfaces;

import java.time.Year;
import java.util.Date;

public interface IWriter {
    public boolean createBroadcast(String broadcast);
    public void deleteBroadcast(int broadcast_id);
    public void deleteEpisode(int episode_id);
    public void editMovie(int broadcast_id, String title, String bio, int launchYear);
    public void editLiveShow(int broadcast_id, String title, String bio, int launchYear, String location);
    public void editEpisode(int episodeID, String title, String bio, int launchYear, int seaNum, int epiNum);
    public void addCredit(int broadcastID, String credit);
    public void deleteCredit(int creditID);
    public int createUser(String user);
    public void deleteUser(int userID);
    public void editUser(int userID, String email, String password, String fName, String lName);
    public void addNotification(boolean seen, String date, String user, String change);
    public void unNotify(int notificationID);
}
