package Interfaces;

import Domain.Credit;
import Domain.CreditType;

import java.time.Year;

public interface IAccount {
    public boolean addCredit(String title, String fName, String lName, CreditType role);
    public void createMovie(String title, String bio, Year launchYear, String userID);
    public void createLiveShow(String title, String bio, Year launchYear, String location, String userID);
    public void createEpisode(String title, String bio, Year launchYear, String showName, int season, int episode, String userID);
    public String deleteCredit(String title, Credit credit);
    public String deleteBroadcast(String title);

    public String getFirstName();
    public String getLastName();
    public String getPassword();
    public String getEmail();
}
