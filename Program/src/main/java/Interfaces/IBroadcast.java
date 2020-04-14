package Interfaces;

import Domain.Credit;
import Domain.CreditType;

import java.net.URL;
import java.time.Year;
import java.util.ArrayList;

public interface IBroadcast {
    public void addCredit(String title, String fName, String lName, CreditType role);
    public String getTitle();
    public ArrayList<Credit> getCredits();
    public URL getTrailerURL();
    public String getBio();
    public Year getLaunchYear();
}
