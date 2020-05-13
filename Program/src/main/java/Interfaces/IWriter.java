package Interfaces;

import java.util.Date;

public interface IWriter {
    public boolean createBroadcast(String broadcast);
    public String deleteBroadcast(String title);
    public void addCredit(String title, String credit);
    public String deleteCredit(String title, String credit);
    public int createUser(String user);
    public void deleteUser(int userID);
    public boolean editUser(String user);
    public void addNotification(boolean seen, String date, String user, String change);
    public void unNotify(String notification);
}
