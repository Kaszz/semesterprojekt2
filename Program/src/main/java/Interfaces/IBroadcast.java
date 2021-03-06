package Interfaces;

import Domain.Credit;
import Domain.CreditType;

import java.time.Year;
import java.util.ArrayList;

public interface IBroadcast {
    public void addCredit(int creditID, String title, String fName, String lName, CreditType role);
    public void addCredit(Credit credit);
    public String getTitle();
    public ArrayList<ICredit> getCredits();
    public String getBio();
    public Year getLaunchYear();
    public int getUserID();
    public String toString();
    public void deleteAllCredits();
    public int getBroadcastID();
}
