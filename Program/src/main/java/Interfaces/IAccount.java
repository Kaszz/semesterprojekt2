package Interfaces;

import Domain.Credit;
import Domain.CreditType;

import java.net.URL;
import java.time.Year;

public interface IAccount {
    public void addCredit(String title, String fName, String lName, CreditType role);
    public void createMovie(String title, URL trailerURL, String bio, Year launchYear);
    public void createLiveShow(String title, URL trailerURL, String bio, Year launchYear, String location);
    public void createEpisode(String title, URL trailerURL, String bio, Year launchYear, String showName, int season, int episode);
    public void deleteCredit(String title, Credit credit);
    public void deleteBroadcast(String title);

    public String getFirstName();
    public String getLastName();
    public String getPassword();
    public String getEmail();
}
