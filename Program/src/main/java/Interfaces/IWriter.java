package Interfaces;

import java.util.Date;

public interface IWriter {
    public boolean createBroadcast(String broadcast);
    public String deleteBroadcast(String title);
    public boolean addCredit(String title, String credit);
    public String deleteCredit(String title, String credit);
    public boolean createUser(String user);
    public boolean deleteUser(String user);
    public boolean editUser(String user);
    public void addNotification(boolean seen, String date, String user, String change);
}
