package Interfaces;

import Domain.Credit;
import Domain.CreditType;

import java.time.Year;

public interface IAccount {
    public void addCredit(String title, String fName, String lName, CreditType role);
    public void createMovie(String title, String bio, Year launchYear, int userID);
    public void createLiveShow(String title, String bio, Year launchYear, String location, int userID);
    public void createEpisode(String title, String bio, Year launchYear, String showName, int season, int episode, int userID);
    public String deleteCredit(String title, Credit credit);
    public void deleteMovie(int broadcast_id, String title);
    public void deleteLiveShow(int broadcast_id, String title);

    public String getFirstName();
    public String getLastName();
    public String getPassword();
    public String getEmail();
}
