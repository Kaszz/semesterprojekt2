package Interfaces;

import java.util.ArrayList;

public interface IReader {
    public String getBroadcast(String title);
    public ArrayList<String> getBroadcastCredits(String title);
    public ArrayList<String> getPersonalCredits(String firstName, String lastName);
}