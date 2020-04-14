package Interfaces;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface IReader {
    public String getBroadcast(String title);
    public ArrayList<String> getBroadcastCredits(String title);
    public ArrayList<String> getPersonalCredits(String firstName, String lastName);
    public String checkUser(String email, String password);
    public ObservableList<String> getUsers (String email, String password, String firstName, String lastName);
}
