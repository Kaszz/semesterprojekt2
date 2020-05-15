package Interfaces;

import Domain.Credit;
import Domain.CreditType;

import java.time.Year;

public interface IAccount {
    public void addCredit(int broadcastID, String title, String fName, String lName, CreditType role);
    public void createMovie(String title, String bio, Year launchYear, int userID);
    public void createLiveShow(String title, String bio, Year launchYear, String location, int userID);
    public void createEpisode(String title, String bio, Year launchYear, String showName, int season, int episode, int userID);
    public void editMovie(int broadcast_id, String title, String bio, int launchYear, String oldTitle);
    public void editLiveShow(int broadcast_id, String title, String bio, int launchYear, String location, String oldTitle);
    public void editEpisode(int broadcast_id, String title, String bio, int launchYear, int seaNum, int epiNum, String oldTitle);
    public void deleteCredit(Credit credit, String title);
    public void deleteBroadcast(int broadcast_id, String title);
    public void deleteEpisode(int episode_id, String title);

    public String getFirstName();
    public String getLastName();
    public String getPassword();
    public String getEmail();
}
