package Interfaces;


import java.util.ArrayList;

public interface IReader {
    public ArrayList<String> getBroadcastCredits(int broadcastID);
    public String checkUser(String email, String password);
    public ArrayList<String> getAllUsers();
    public ArrayList<String> getAllPersons();
    public ArrayList<String> getNotifications();
    public ArrayList<String> getAllBroadcasts();
}
