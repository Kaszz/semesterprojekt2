package Interfaces;

import java.util.Date;

public interface IWriter {
    public boolean createBroadcast(String broadcast);
    public void deleteBroadcast(int broadcast_id);
    public void deleteEpisode(int episode_id);
    public void addCredit(int broadcastID, String credit);
    public void deleteCredit(int creditID);
    public int createUser(String user);
    public void deleteUser(int userID);
    public boolean editUser(String user);
    public void addNotification(boolean seen, String date, String user, String change);
    public void unNotify(String notification);
}
